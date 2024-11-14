package com.beltra.visitemediche.controller;

import com.beltra.visitemediche.domain.Articolo;

import com.beltra.visitemediche.service.ArticoloService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log // serve per accedere al log usando il lombok
@Controller
@RequestMapping("/articoli") // tutti gli endpoint avranno sempre "/articoli" nel loro indirizzo
public class ArticoloController {

    // Code Injection dei Service necessari
    ArticoloService articoloService;

    // Code Injection fatto tramite costruttore
    public ArticoloController(ArticoloService articoloService) {
        this.articoloService = articoloService;
    }


    @GetMapping()
    @ResponseBody // necessario, altrimenti il test fallisce
    public List<Articolo> getArticoli() {
        return articoloService.getAllArticoli();
    }



}
