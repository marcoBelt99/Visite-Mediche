package com.beltra.visitemediche.provacontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class PazienteControllerTest {

    /** Va a simulare le specifiche MVC della mia app */
    private MockMvc mockMvc;

    /** Code Injection del wac */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** Prima di ciascun Unit Test sarà eseguito questo metodo setup che andrà a creare il contest del mio unit test
     * In pratica è come se avviasse la mia applicazione */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    /** Verifica cosa l'endpoint mi restituisce */
//    @Test
//    public void testGetPazienti() throws Exception {
//
//        mockMvc
//            // Grazie al perform() posso simulare la chiamata all'endpoint desiderato secondo uno specifico metodo HTTP
//            .perform(
//                    get("/pazienti/cerca/all")
//                            .contentType(MediaType.TEXT_HTML) // Verifico che cosa mi restituisce il metodo
//            )
//            // Mi aspetto di ottenere come status code "ok" (tutto è andato per il meglio
//            .andExpect( status().isOk() )
//
//            // Mi aspetto che l'app mi restutuisca una stringa pari a quella che mi restituitsce il metodo
//            // jsonPath identifica la root di riferimento del file json. Con il "$" identifico la radice del json
//            // anche se è un html, con "$" verifico la radice
//            .andExpect( jsonPath("$").value("pazienti") )
//
//            // Alla fine, stampo il risultato: riguarderà qualsasi risultato sia positivo che negativo
//            .andDo( print() );
//
//    }

    @Test
    public void testGetPazientiOk() throws Exception {
        mockMvc
                // Simula la chiamata GET all'endpoint
                .perform(get("/pazienti/cerca/all")

                        // Aspettati HTML come risposta
                        .contentType(MediaType.TEXT_HTML))
                // Verifica che lo status sia "200 OK"
                .andExpect( status().isOk() )

                // Verifica che il contenuto sia di tipo HTML
                .andExpect( content().contentTypeCompatibleWith(MediaType.TEXT_HTML) )

                // Verifica che nell'HTML restituito ci sia un frammento atteso
                /* Questa verifica è utile quando voglio:
                    - Accertarti che il contenuto restituito dal controller contenga parti specifiche (come un titolo, un messaggio o un dato chiave).
                    - Validare che il rendering del template Thymeleaf includa determinati elementi previsti
                .*/
                .andExpect( content().string(org.hamcrest.Matchers.containsString("Pazienti")))
                // Stampa il risultato
                .andDo(print());
    }




}
