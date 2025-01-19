package com.wellscosta.literalura.service.principal;

import com.wellscosta.literalura.model.Autor;
import com.wellscosta.literalura.model.DadosLivros;
import com.wellscosta.literalura.model.Livro;
import com.wellscosta.literalura.model.Results;
import com.wellscosta.literalura.repository.LivroRepository;
import com.wellscosta.literalura.service.ConsumoApi;
import com.wellscosta.literalura.service.converte.ConverteDados;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/";
    private LivroRepository repository;

    public Principal(LivroRepository repository) {
        this.repository = repository;
    }

    public void exibirMenu() {
        var opcao = 0;
        do {
            var menu = """
                    =-=-=-=-=-=-=-=-=-=-=-=-=-=
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    
                    0 - Sair
                    =-=-=-=-=-=-=-=-=-=-=-=-=-=
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEmAno();
                    break;
                case 5:
                    listarLivroIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }

        } while (opcao != 0);

    }

    private void listarLivroIdioma() {

    }

    private void listarAutoresVivosEmAno() {

    }

    private void listarAutores() {

    }

    private void listarLivros() {

    }

    private void buscarLivro() {
        Optional<DadosLivros> dados = getDadosLivros();

        if (dados.isPresent()) {
            Livro livro = new Livro(dados.get());

            Autor autor = livro.getAutor();
            autor.getLivro().add(livro);

            repository.save(livro);
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private Optional<DadosLivros> getDadosLivros() {
        System.out.print("Digite o nome do livro para buscar: ");
        var nomeLivro = sc.nextLine();

        var json = consumoApi.consumirApi(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        Results results = converteDados.obterDados(json, Results.class);

        return results.livros().stream().findFirst();
    }
}
