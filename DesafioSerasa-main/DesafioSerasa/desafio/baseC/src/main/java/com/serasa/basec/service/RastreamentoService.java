package com.serasa.basec.service;

import com.serasa.basec.model.HistoricoEvento;
import com.serasa.basec.repository.RastreamentoRepositorio;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RastreamentoService {

    private final RastreamentoRepositorio repository;
    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    public RastreamentoService(RastreamentoRepositorio repository) {
        this.repository = repository;
    }

    public void rastreioBureau(String cpf) {
        HistoricoEvento event = new HistoricoEvento(
                "CONSULTA_BUREAU",
                LocalDateTime.now(),
                "Consulta realizada no Bureau de Crédito",
                null,
                null
        );
        repository.adicionarEvento(cpf, event, DEFAULT_TTL);
    }

    public void rastreioMovimentacaoFinanceira(String cpf, Double valor, String origem) {
        HistoricoEvento event = new HistoricoEvento(
                "MOVIMENTACAO_FINANCEIRA",
                LocalDateTime.now(),
                "Consulta movimentação financeira",
                valor,
                origem
        );
        repository.adicionarEvento(cpf, event, DEFAULT_TTL);
    }

    public void rastreioCompraCartao(String cpf, Double valor, String estabelecimento) {
        HistoricoEvento event = new HistoricoEvento(
                "COMPRA_CARTAO",
                LocalDateTime.now(),
                "Consulta última compra no cartão de crédito",
                valor,
                estabelecimento
        );
        repository.adicionarEvento(cpf, event, DEFAULT_TTL);
    }

    public HistoricoEvento getUltimaConsultaBureau(String cpf) {
        return repository.getUltimoEvento(cpf, "CONSULTA_BUREAU");
    }

    public HistoricoEvento getUltimaMovimentacaoFinanceira(String cpf) {
        rastreioBureau(cpf);
        return repository.getUltimoEvento(cpf, "MOVIMENTACAO_FINANCEIRA");
    }

    public HistoricoEvento getUltimaCompraCartao(String cpf) {
        rastreioBureau(cpf);
        return repository.getUltimoEvento(cpf, "COMPRA_CARTAO");
    }

}