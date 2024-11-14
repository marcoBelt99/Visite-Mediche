package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "anagrafiche")

@Getter // TODO: uso del Lombok
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anagrafica {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "email")
    private String email;


// TODO: La relazione che intercorre tra anagrafiche e pazienti e'
//      1:1 quindi uso la @OneToOne
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "codice_fiscale")
    private Paziente paziente;

}
