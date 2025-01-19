package com.wellscosta.literalura.model;

import jakarta.persistence.*;

import java.util.NoSuchElementException;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Livro(){}

    public Livro(DadosLivros dadosLivros) {
        this.titulo = dadosLivros.titulo();

        this.autor = dadosLivros.autor().stream()
                .map(a -> new Autor(a.nome(), a.nascimento(), a.falecimento())).findFirst()
                .orElseThrow(NoSuchElementException::new);

        this.idioma = dadosLivros.idioma().stream().findFirst().orElse("N/A");
        this.downloads = dadosLivros.downloads();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "----- Livro -----" +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor.getNome() +
                "\nIdioma: " + idioma +
                "\nDownloads: " + downloads +
                "\n----------------";
    }
}
