package com.beltra.visitemediche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private String saluti = "Saluti, sono la tua prima applicazione web creata in Spring Boot 3 e Thymeleaf";;

    /** Metodo che gestisce la root page della mia app
     * @param model: permette di passare alcuni attributi
     * */
    //@RequestMapping(method = RequestMethod.GET, value = "/")
    @GetMapping("/")
    public String getWelcome(Model model) {

        model.addAttribute("intestazione", "Benvenuto/a nella root page dello Studio Medico Associato");
        model.addAttribute("saluti", saluti);

        // TODO
        return "index";
    }
}
