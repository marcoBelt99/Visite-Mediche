package com.beltra.visitemediche.service;


import com.beltra.visitemediche.domain.Articolo;

import java.util.List;

public interface ArticoloService {

    /** Ritorna l'elenco completo di tutti gli articoli */
    public List<Articolo> getAllArticoli();
}
