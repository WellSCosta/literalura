package com.wellscosta.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer nascimento;
    private Integer falecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livro = new ArrayList<>();

    public Autor(){}

    public Autor(String nome, Integer nascimento, Integer falecimento) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.falecimento = falecimento;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNascimento() {
        return nascimento;
    }

    public void setNascimento(Integer nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getFalecimento() {
        return falecimento;
    }

    public void setFalecimento(Integer falecimento) {
        this.falecimento = falecimento;
    }

    @Override
    public String toString() {
        return "\nAutor: " + nome +
                "\nAno de nascimento: " + nascimento +
                "\nAno de falecimento: " + falecimento +
                "\nLivros: " + livro.stream().map(Livro::getTitulo).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor autor)) return false;
        return Objects.equals(getNome(), autor.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }
}
