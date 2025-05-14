package com.serasa.basea.usuario;

import com.serasa.basea.securanca.CriptografiaGeral;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario_base_a")
public class UsuarioBaseA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CriptografiaGeral.class)
    @Column(nullable = false, unique = true)
    private String cpf;

    @Convert(converter = CriptografiaGeral.class)
    @Column(nullable = false)
    private String nome;

    @Convert(converter = CriptografiaGeral.class)
    @Column(nullable = false)
    private String endereco;

    @ElementCollection
    @CollectionTable(name = "dividas", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "valor")
    private List<String> dividas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
