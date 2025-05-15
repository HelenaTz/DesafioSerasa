package com.serasa.basea.service;

import com.serasa.basea.usuario.UsuarioBaseA;
import com.serasa.basea.repository.UsuarioBaseARepositorio;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseAService {

    private final UsuarioBaseARepositorio repository;
    private final StringEncryptor encryptor;

    @Autowired
    public BaseAService(UsuarioBaseARepositorio repository, StringEncryptor encryptor) {
        this.repository = repository;
        this.encryptor = encryptor;
    }

    public UsuarioBaseA getPeloCpf(String cpf) throws Exception {

        if(isCpfValido(cpf)){
            cpf = encryptor.encrypt(cpf);
            return repository.findByCpf(cpf)
                    .orElseThrow(() -> new IllegalArgumentException("CPF não encontrado"));
        }else{
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public boolean isCpfValido(String cpf){

        if (cpf.matches("^(\\d)\\1{10}$")) {
            return false;
        } else if (cpf.length() != 11) {
            return false;
        }else{
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;

            return (cpf.charAt(9) - '0' == primeiroDigito) && (cpf.charAt(10) - '0' == segundoDigito);

        }
    }

}