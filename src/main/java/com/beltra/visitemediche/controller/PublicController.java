package com.beltra.visitemediche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/** Mi serve per poter fare un mapping esplicito per tutte quelle pagine (view) che sono pubbliche,
 *  cioè che sono accessibili da qualsiasi utente (anche non loggato).   */
@Controller
public class PublicController {

    @GetMapping("/benvenuto_utente_anonimo")
    public String benvenutoUtenteAnonimo(Model model) {

        model.addAttribute("intestazione", "Benvenuto Utente Anonimo!");
        model.addAttribute("saluti", "Grazie per aver visitato la nostra applicazione.");
        return "benvenuto_utente_anonimo"; // Nome del file HTML senza estensione
    }
}
