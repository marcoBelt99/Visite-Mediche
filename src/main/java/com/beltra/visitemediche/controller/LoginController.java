package com.beltra.visitemediche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    private String titolo = "Accesso & Autenticazione";
    private String sottotitolo = "Procedi ad inserire lo username e la password";
    private String errMsg = "Spiacente, lo usernamen o la password non sono corretti!";


    @GetMapping(value = "/login")
    public String getLogin(Model model) {
        model.addAttribute("titolo", titolo);
        model.addAttribute("sottotitolo", sottotitolo);
        model.addAttribute("errmsg", errMsg);

        // ritorno il nome della vista di login
        return "login";
    }




}
