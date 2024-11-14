package com.beltra.visitemediche.controller;

import com.beltra.visitemediche.domain.Paziente;
import com.beltra.visitemediche.dto.PazienteDTO;
import com.beltra.visitemediche.service.PazienteService;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@Log
@Controller
@RequestMapping("/pazienti")
public class PazienteController {



    // Dichiarazione dei service di cui ho bisogno
    private PazienteService pazienteService;

    // Codice Injection (tramite costruttore)
    public PazienteController(PazienteService pazienteService) {
        this.pazienteService = pazienteService;
    }


    /** Recupera l'intera lista di pazienti e passa questa informazione alla view */
    // vedere bene se è corretta la classe ModelMap o se invece mi serve la classe Model
    @GetMapping("/cerca/all")
    public String getPazienti(Model model) { // vedere bene se è corretta la classe ModelMap o se invece mi serve la classe Model


        List<Paziente> listaPazienti = pazienteService.getAllPazienti();

        model.addAttribute("titolo", "Elenco dei pazienti con il relativo medico");
        model.addAttribute("pazienti", listaPazienti);

        return "pazienti";
    }


    /** TODO: uso del @PathVariable per implementare una funzionalità di filtro, abilitando la paginazione.<br>
     * Questo metodo si occupa di cercare l'insieme dei soli pazienti che hanno un determinato cognome
     * preso come parametro sull'URL.
     * */
    @GetMapping(value = "/cerca/cognome/{cognome}")
    public String getPazientiByCognome(
            @PathVariable String cognome,
            Model model) {

        // partendo dalla prima pagina (si parte da 0), mostrami i 10 record per pagina
        List<Paziente> listaPazienti = pazienteService.getPazienteByCognome(cognome, 0, 10);

        // Aggiungo al model la lista trovata, così nella view posso recuperarla
        model.addAttribute("pazienti", listaPazienti);

        // ritorno la vista "pazienti"
        return "pazienti";
    }



    /**
     * #######################################################
     * Modifica il medico assegnato ad uno specifico paziente
     * #######################################################
     *  TODO: per poter lavorare sullo stesso form in lettura e in
     *        scrittura, per uno stesso endpoint devo prevedere due
     *        metodi, GET per recuperare i dati, POST per aggiornarli
     *        Quindi per "/pazienti/modifica/medico" uso sia @GetMapping
     *        che @PostMapping
     * */

    @GetMapping("/modifica/medico")
    public String mostraFormAggiornaMedicoPaziente(Model model) {

        List<Paziente> listaPazienti = pazienteService.getAllPazienti();
        model.addAttribute("listaPazienti",  listaPazienti );

        return "pazienti_modifica_medico";
    }


    @PostMapping("/modifica/medico")
    public String modificaMedicoPaziente(
            @RequestParam("codiceFiscaleSelect") String codiceFiscale, // "codiceFiscaleSelect" è il name del codice fiscale dentro la select
            @RequestParam("codiceMedico") String codiceMedico
    ) {

        // Aggiorna il paziente
        pazienteService.updateMedicoPazienteByCodiceFiscale(codiceFiscale, codiceMedico);

        // Reindirizzo alla pagina aggiornata
        return "redirect:/pazienti/cerca/all";
    }



    // TODO: Endpoint per ottenere i dettagli del paziente via AJAX
    @GetMapping("/pazienteDettagli")
    @ResponseBody // Indica a Spring di restituire JSON direttamente
    public ResponseEntity<PazienteDTO> getPazienteDetails(@RequestParam("codiceFiscale") String codiceFiscale) {

        return ResponseEntity.ok( pazienteService.getPazienteDTOByCodiceFiscale( codiceFiscale ) );

    }

}
