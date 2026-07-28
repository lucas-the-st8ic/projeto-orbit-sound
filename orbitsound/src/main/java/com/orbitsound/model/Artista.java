package com.orbitsound.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Artistas")
@Getter
@Setter
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

    @OneToMany(mappedBy = "artista")
    private List<Musica> musicasDoArtista = new ArrayList<>();

    public Artista() {}

    public Artista(String nome, TipoArtista tipo) {
        this.nome = nome;
        this.tipoArtista = tipo;
    }

    @Override
    public String toString() {
        return  "\n===========================\n"+
                "Artista: " + this.nome+"\n" +
                "Tipo: " + this.tipoArtista+"\n" +
                "Musicas: " + musicasDoArtista;
    }

}
