package com.beltra.visitemediche.dto;

import com.beltra.visitemediche.domain.Ingrediente;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

/** In una classe DTO ho la possibiltà di togliere dei campi:
 *  Es. se la classe di entità è enorme, ma al ciente serve solo un sottoinsieme
 *  allora nella classe DTO avrò un numero di campi inferiori rispetto alla classe originaria.
 * <br>
 * Altre volte invece il numero di attributi è lo stesso, però ho la necessità di modificare la
 * denominazione Es. invece di avere "CodArt" con la maiuscola, posso avere "codice".
 * Oppure Es. "DataCreaz" potrei farla diventare "datacreazione".
 * Inoltre, posso aggiungere anche campi aggiunti come Es. il campo del prezzo, che nella
 * classe di entità Articolo non è presente.
 * */
@Getter
@Setter
public class ArticoloDTO {


    private String codiceArticolo;
    private String descrizione;
    private String unitaMisura;
    private String codiceStatistico;
    private String pezziPerCartone;
    private Double pesoNetto;
    private String idStatoArticolo;

    private Date dataCreazione;


    private Integer idFamigliaAssortimento;

    private Integer idIva;

    private Ingrediente ingrediente;

    private Double prezzo = 0.0d;
}
