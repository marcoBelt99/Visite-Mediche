package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "articoli")
@Getter // TODO: uso del Lombok
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Articolo {

    @Id
    @Column(name="codart")
    private String codArt;

    @Column(name="descrizione")
    private String descrizione;

    @Column(name="um")
    private String um;

    @Column(name="codstat")
    private String codStat;

    @Column(name="pzcart")
    private String pzCart;

    @Column(name="pesonetto") // nel db si chiama "pesonetto"
    private Double pesoNetto; // in Java lo chiamo "pesoNetto"

    @Column(name="idstatoart")
    private String idStatoArt;

    @Temporal(TemporalType.DATE)
    @Column(name="datacreazione")
    private Date dataCreazione;

    @Column(name = "idfamass")
    private Integer idFamAss;

    @Column(name = "idiva")
    private Integer idIva;

    // TODO: mapping 1:1 con tabella "ingredienti"
    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "articolo", // "articolo" è il nome dell'attributo (Java) nella classe Ingrediente
            orphanRemoval = true
    )
    private Ingrediente ingrediente;


}
