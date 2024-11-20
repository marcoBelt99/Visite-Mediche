package com.beltra.visitemediche.controller;

import com.beltra.visitemediche.domain.Anagrafica;
import com.beltra.visitemediche.service.AnagraficaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anagrafiche")
public class AnagraficaController {

    // Code Injection dei service
    AnagraficaService anagraficaService;

    public AnagraficaController(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
    }


    @GetMapping("/{codiceFiscale}")
    public String getAnagrafica(@PathVariable String codiceFiscale,
                                Model model) {

        Anagrafica anag = this.anagraficaService.getAnagraficaByCodiceFiscale(codiceFiscale);
        model.addAttribute("anagrafica", anag);

        return "anagrafiche";
    }


}
