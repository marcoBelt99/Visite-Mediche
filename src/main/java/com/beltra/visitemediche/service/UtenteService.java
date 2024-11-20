package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Ruolo;
import com.beltra.visitemediche.domain.Utente;

public interface UtenteService {


    /** TODO: Salva l'utente a DB con la password criptata */
    Utente salvaUtente(String id, String username, String password);



}
