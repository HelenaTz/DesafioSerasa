package com.serasa.baseb.dto;

import java.util.List;

public class BaseBDto {
    private int idade;
    private List<String> bens;
    private String endereco;
    private String fonteRenda;

    public BaseBDto(int idade, List<String> bens, String endereco, String fonteRenda) {
        this.idade = idade;
        this.bens = bens;
        this.endereco = endereco;
        this.fonteRenda = fonteRenda;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<String> getBens() {
        return bens;
    }

    public void setBens(List<String> bens) {
        this.bens = bens;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFonteRenda() {
        return fonteRenda;
    }

    public void setFonteRenda(String fonteRenda) {
        this.fonteRenda = fonteRenda;
    }
}
