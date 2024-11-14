package com.beltra.visitemediche.prova;

import com.beltra.visitemediche.controller.ArticoloController;
import com.beltra.visitemediche.domain.Articolo;
import com.beltra.visitemediche.service.ArticoloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

// TODO: import necessari per mockito
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/** Questa classe, che fa uso della @WebMvcTest, anziche' (come invece le precedenti facevano) istanziare
 *  il contesto completo dell'applicazione, istanzia solo il livello web.
 *  <br>
 *  Suppongo di voler testare il controller {@link com.beltra.visitemediche.controller.ArticoloController}, allora
 *  devo usare l'annotazione @WebMvcTest(ArticoloController.class)
 *
 * */

//@SpringBootTest
@WebMvcTest(ArticoloController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;


    /** Con la @MockBean creo e inietto un mock per il mio service
     * (se non lo faccio, il contesto dell'applicazione non può essere avviato)
     * e ne imposto il comportamento usando Mockito.
     * */
    @MockBean
    private ArticoloService articoloService;

    /** Uso di Mockito */
    @Test
    void getArticoliMustReturnListOfAllArticoli() throws Exception {

        when( articoloService.getAllArticoli() ).thenReturn( new ArrayList<>() );

        this.mockMvc
                .perform(get("/articoli"))
                .andDo(print())
                .andExpect( status().isOk() );

        // con la verify() verifico se c'e' stata effettivamente un'iterazione con il metodo dell'oggetto che sto mockando
        // cioè, verifico che il metodo del service sia stato chiamato
        verify(articoloService).getAllArticoli();
    }


}
