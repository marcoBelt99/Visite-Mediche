package com.beltra.visitemediche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    /** Variabile che posso passare alla vista tramite Model e metodo addAttribute() */
    private String saluti = "Saluti, sono la tua prima applicazione web creata in Spring Boot 3 e Thymeleaf";;

    /** Metodo che gestisce la root page della mia app
     * @param model: permette di passare alcuni attributi
     * */
    //@RequestMapping(method = RequestMethod.GET, value = "/")
    @GetMapping("/")
    public String getWelcome(Model model) {

        model.addAttribute("intestazione", "Benvenuto/a nella root page dello Studio Medico Associato");
        model.addAttribute("titolo", "HomePage");
        model.addAttribute("saluti", saluti);

        // TODO
        return "index";
    }

    /** Ho scritto questo metodo per poter esercitarmi con gli unit test
     * <br>
     *  Se, come in questo esempio, al RequestMapping non specifico il tipo di metodo,
     *  per impostazione predefinita mi mappa tutte le operazioni HTTP.
     * */
    @RequestMapping("/saluto")
    public @ResponseBody String getSaluto() {
        return "Allenamento per Unit Test !!";
    }


    /** TODO: metodo necessario per l'autenticazione.
     *  <br>
     *  Senza questo metodo, una volta inserito userame e password nel form di login,
     *  non sarei in grado di vedere la pagina di index
     * */
    @GetMapping(value = "/index/{name}")
    public String getWelcome(@PathVariable String name,
                             Model model) {
        model.addAttribute("intestazione", String.format("Benvenuto %s nella index page della webapp Studio Medico Associato", name) );
        model.addAttribute("saluti", saluti);

        return "index";
    }
}
