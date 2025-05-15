package com.serasa.basea.dto;

import java.util.List;

public class BaseADto {
    private String cpf;
    private String nome;
    private String endereco;
    private List<String> dividas;

    public BaseADto(String cpf, String nome, String endereco, List<String> dividas) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.dividas = dividas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<String> getDividas() {
        return dividas;
    }

    public void setDividas(List<String> dividas) {
        this.dividas = dividas;
    }
}
