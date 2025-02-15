package com.wellscosta.literalura.repository;

import com.wellscosta.literalura.model.Autor;
import com.wellscosta.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.falecimento > :ano AND a.nascimento < :ano")
    List<Autor> obterAutorPorAno(Integer ano);

    Optional<List<Livro>> findByIdiomaContainingIgnoreCase(String idioma);
}
