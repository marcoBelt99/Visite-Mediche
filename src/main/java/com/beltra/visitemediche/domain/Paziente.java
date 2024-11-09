package com.beltra.visitemediche.domain;

import jakarta.persistence.*;

import com.beltra.visitemediche.domain.*;

@Entity
@Table(name = "Paziente")
public class Paziente {

    @Id // chiave primaria
    @Column(name = "codice_fiscale") // la colonna tabella a DB ha nome "codice_fiscale"
    private String codiceFiscale;

    @Column(name = "cognome", nullable = false) // la colonna tabella a DB ha nome "cognome"
    private String cognome;

    @Column(name = "nome", nullable = false)
    private String nome;

    // TODO:
    //   Relazione molti-a-uno con Medico
    @ManyToOne //
    @JoinColumn(name = "medico", // 'medico' è la chiave esterna in Paziente che si riferisce a 'Medico.codice'
            nullable = false,
            referencedColumnName = "codice")
    private Medico medico;



    // TODO: gestione relazione 1:1
    //      MEDICO  1--------1 ANAGRAFICA
    // TODO: La relazione che intercorre tra anagrafiche e medici e' 1:1 quindi uso la @OneToOne
    @OneToOne(
            mappedBy = "paziente"
    )
    //@PrimaryKeyJoinColumn
    private Anagrafica anagrafica;


    // COSTRUTTORI
    public Paziente() {}

    public Paziente(String codiceFiscale, String cognome, String nome, Medico medico) {
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
        this.nome = nome;
        this.medico = medico;
    }


    // GETTERS & SETTERS
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
