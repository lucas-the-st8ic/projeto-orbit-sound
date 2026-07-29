package com.orbitsound.main;

import com.orbitsound.model.Artista;
import com.orbitsound.model.Musica;
import com.orbitsound.model.TipoArtista;
import com.orbitsound.repository.ArtistaRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
public class Main {
    private final Scanner input = new Scanner(System.in);

    private final ArtistaRepository repository;

    public Main(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {

        var option = -1;

        while(option != 0) {
            var menu = """
                    *** Orbit Sound Musics ***
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                    ======================================
                    0 - Sair
                    --------------------------------------
                    Digite sua opção:      """;
            System.out.print(menu+ " ");
            option = input.nextInt();
            input.nextLine();
            switch(option) {
                case 1:
                    cadastrarArtistas();
                break;

                case 2:
                    cadastrarMusicas();
                break;

                case 3:
                    listarMusicas();
                break;

                case 4:
                    buscarMusicasPorArtista();
                break;

                case 5:
                    pesquisarSobreUmArtista();
                break;

                case 0:

                break;
            }
        }
    }

    private void cadastrarArtistas() {

        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("=== Cadastro de Artista ===");
            System.out.print("Informe o nome desse artista: ");
            var nome = input.nextLine();
            System.out.println("-----------------------");
            System.out.println("Informe o tipo desse artista");
            System.out.print("solo, dupla ou banda: ");
            var tipo = input.nextLine();
            System.out.println("-----------------------");
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repository.save(artista);

            System.out.print("Cadastrar novo artista? (S/N):");
            cadastrarNovo = input.nextLine();
        }
    }

    private void cadastrarMusicas() {
        var continuarCadastrandoMusicas = "S";

        System.out.print("Cadastrar música de qual artista? ");
        var nome = input.nextLine();
        Optional<Artista> artista = repository.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()) {
            System.out.print("Informe o nome da música:");
            var nomeMusica = input.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicasDoArtista().add(musica);
            repository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado!!");
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = repository.findAll();
        artistas.forEach(System.out::println);
        
    }

    private void buscarMusicasPorArtista() {
    }

    private void pesquisarSobreUmArtista() {
    }
}
