package com.beltra.visitemediche.dto;

import lombok.AllArgsConstructor; // non funziona bene in questo caso perchè decide lui l'ordine con cui mettere i
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PazienteDTO {

        private String codiceFiscale;
        private String cognome;
        private String nome;

        public PazienteDTO(String codiceFiscale, String cognome, String nome) {
                this.codiceFiscale = codiceFiscale;
                this.cognome = cognome;
                this.nome = nome;
        }
}

