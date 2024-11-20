package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // per gestione auto increment
    private Long id;

    private String ruolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente", referencedColumnName = "id")
    private Utente utente;


    public Ruolo(String ruolo, Utente utente) {
        this.ruolo = ruolo;
        this.utente = utente;
    }
}
