package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Paziente;
import com.beltra.visitemediche.dto.PazienteDTO;

import java.util.List;

public interface PazienteService {

    /** Recupera l'intera tabella dei Pazienti sul database */
    List<Paziente> getAllPazienti();


    Paziente getPazienteByCodiceFiscale(String  codiceFiscale);


    /** TODO:
     * @param cognome e' il filtro di ricerca vero e proprio
     * @param page e' il numero della pagina (in ambito di paginazione)
     * @param numrec e' il numero di record per ogni pagina (in ambito di paginazione)
     * */
    List<Paziente> getPazienteByCognome(String cognome, int page, int numrec);


    Paziente getPazienteByCognomeAndNome(String cognome, String nome);


    /** Aggiorna il medico assegnato al paziente. */
    void updateMedicoPazienteByCodiceFiscale(String codiceFiscale, String codiceMedico);


    /** Crea il DTO per scambiare dati strettamente necessari che contiene solo i dati che voglio inviare come risposta al client mediante AJAX.
     *  Questo approccio è molto utile per evitare problemi di serializzazione con entità complesse o con relazioni a cascata. */
    PazienteDTO getPazienteDTOByCodiceFiscale(String codiceFiscale);

}
