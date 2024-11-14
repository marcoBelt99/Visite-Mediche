package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;



@Entity // TODO: specifico questa come una classe di entita'
@Table(name = "medici") //TODO:  specifico il nome della tabella,
// (normalmente, se nome della classe = nome tabella allora non mi serve la @Table)
@Getter // TODO: uso del Lombok
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    // TODO: indico che "codice" e' la mia chiave primaria
    @Id
    // TODO: specifico l'esatto nome della colonna nella tabella. Non puo' essere null
    @Column(name = "codice", nullable = false)
    private String codice;

    // TODO: specifico l'esatto nome della colonna nella tabella. Non puo' essere null
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "citta", nullable = false)
    private String citta;


    // TODO: gestione relazione 1 ----> N
    //       MEDICO 1  ---- <visita> ---> N PAZIENTI
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "medico",
            orphanRemoval = true)
    private Set<Paziente> pazienti = new HashSet<>();

}