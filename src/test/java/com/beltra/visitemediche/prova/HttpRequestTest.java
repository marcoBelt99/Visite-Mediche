package com.beltra.visitemediche.prova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat; // TODO: per usare assertThat()

// TODO: i seguenti import servono per mockare la richieta con il MockMvc
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;



/** Testo il comportamento della mia webapp attendendo una connessione (come farei in produzione) e quindi
 *  invio una richiesta HTTP e affermo la risposta.
 * <br>
 * WebEnvironment=RANDOM_PORT mi avvia il server con una porta casuale (utile per evitare conflitti negli ambienti di test).
 * <br>
 * Ho poi fatto il code injection della porta con @LocalServerPort.
 * <br>
 * Notare anche che Spring Boot mi ha automaticamente fornito un <b>TestRestTemplate</b> per me. Devo solo metterlo in @Autowired.
 * */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // necessario per usare il MockMvc
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getSalutiShouldReturnDefaultMessageStartingServer() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/saluto",
                String.class)).contains("Allenamento");
    }



/**
 * Un altro approccio utile è di non avviare affatto il server, ma di testare solo il livello sottostante, dove spring gestisce la richiesta
 *  HTTP in arrivo e la passa al mio controller. In questo modo, viene usato quasi tutto lo stack completo e il mio codice sarà chiamato
 *  esattamente nello stesso modo in cui sta elaborando una richiesta HTTP reale, ma senza il costo dell'avvio del server.
 *  <br>
 *  Per farlo, Spring uso il <b>MockMvc</b> e chiedo che venga iniettato per me tramite l'@AutoConfigureMockMvc
     * */
    @Test
    void getSalutiShouldReturnMessageNotStartingServer() throws Exception {
        this.mockMvc
                .perform( get("/saluto") )
                .andDo( print() )
                .andExpect( status().isOk() )
                .andExpect( content().string( containsString("Allenamento") ) );
    }



}
