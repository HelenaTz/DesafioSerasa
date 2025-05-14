package com.serasa.baseb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serasa.baseb.dto.ScoreResponse;
import com.serasa.baseb.usuario.UsuarioBaseB;
import com.serasa.baseb.repository.UsuarioBaseBRepositorio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class BaseBService {

    private final UsuarioBaseBRepositorio repositorio;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.ml-script-path}")
    private String caminhoArquivoPython;

    public BaseBService(
            UsuarioBaseBRepositorio repositorio,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.repositorio = repositorio;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ScoreResponse calculaScore(String cpf) {
        UsuarioBaseB usuario = repositorio.buscaPeloCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado na Base B"));

        try {

            Map<String, Object> inputData = new HashMap<>();
            inputData.put("idade", usuario.getIdade());
            inputData.put("bens", usuario.getBens());
            inputData.put("rendaMensal", usuario.getRendaMensal());

            Process process = Runtime.getRuntime().exec(
                    new String[]{"python3", caminhoArquivoPython, new ObjectMapper().writeValueAsString(inputData)}
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            double score = Double.parseDouble(reader.readLine());

            usuario.setScore(score);
            repositorio.save(usuario);

            kafkaTemplate.send("score-updates", String.format("{\"cpf\": \"%s\", \"score\": %.2f}", cpf, score));

            return new ScoreResponse(cpf, score);
        } catch (Exception e) {
            throw new RuntimeException("Falha no cálculo do score: " + e.getMessage());
        }
    }
}