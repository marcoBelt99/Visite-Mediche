package com.beltra.visitemediche.dto;

import lombok.AllArgsConstructor;
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

