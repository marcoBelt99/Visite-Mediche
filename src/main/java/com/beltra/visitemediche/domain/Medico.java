package com.beltra.visitemediche.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


// TODO: specifico questa come una classe di entita'
@Entity
//TODO:  specifico il nome della tabella (normalmente, se nome della classe = nome tabella allora non mi serve la @Table)
@Table(name = "medici")

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



    // COSTRUTTORI
    public Medico() {}

    public Medico(String cognome, String nome, String citta, Set<Paziente> pazienti) {
        this.cognome = cognome;
        this.nome = nome;
        this.citta = citta;
        this.pazienti = pazienti;
    }


    // GETTERS & SETTERS
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Set<Paziente> getPazienti() {
        return pazienti;
    }

    public void setPazienti(Set<Paziente> pazienti) {
        this.pazienti = pazienti;
    }
}
