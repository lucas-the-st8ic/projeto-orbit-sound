package com.orbitsound.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "Musicas")
@Getter
@Setter
public class Musica {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String nomeDaMusica;

    @ManyToOne
    private Artista artista;
    public Musica() {

    }

    public Musica(String nomeMusica) {
        this.nomeDaMusica = nomeMusica;
    }


    @Override
    public String toString(){
        return
                "Música: " + nomeDaMusica + "\n" +
                "Artista: " + artista;
    }
}
