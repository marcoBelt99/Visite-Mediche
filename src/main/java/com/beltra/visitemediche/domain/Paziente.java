package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pazienti")
@Getter // TODO: uso del Lombok
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paziente {

    @Id // chiave primaria
    @Column(name = "codice_fiscale") // la colonna tabella a DB ha nome "codice_fiscale"
    private String codiceFiscale;

    @Column(name = "cognome", nullable = false) // la colonna tabella a DB ha nome "cognome"
    private String cognome;

    @Column(name = "nome", nullable = false)
    private String nome;

    // TODO:
    //   Relazione N:1 con Medico  (molti-ad-uno)
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
    private Anagrafica anagrafica;

}