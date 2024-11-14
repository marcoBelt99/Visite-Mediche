package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredienti")
@Getter // TODO: uso del Lombok
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {

    @Id
    @Column(name = "codart")
    private String codArt;

    @Column(name = "info")
    private String info;

    // TODO: mapping 1:1 con tabella "ingredienti" tramite la chiave primaria (senza chiave esterna)
    @OneToOne
    @PrimaryKeyJoinColumn
    private Articolo articolo;

}
