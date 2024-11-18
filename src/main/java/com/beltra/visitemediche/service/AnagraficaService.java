package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Anagrafica;

public interface AnagraficaService {

    /** Recupera l'anagrafica tramite il codice fiscale */
    Anagrafica getAnagraficaByCodiceFiscale(String codiceFiscale);
}
