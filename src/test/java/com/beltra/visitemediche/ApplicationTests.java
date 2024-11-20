package com.beltra.visitemediche;

import com.beltra.visitemediche.domain.Paziente;
import com.beltra.visitemediche.domain.Utente;
import com.beltra.visitemediche.service.PazienteService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ApplicationTests {

    /** @SpringBootTest dice a Spring di cercare una classe di configurazione principale (che abbia
     * @SpringBootApplication) e di usarla per avviare un contesto applicativo Spring.
     * Posso eseguire questo test nel mio IDE o da linea di comando, eseguendo: ./mvnw test e
     * dovrebbe passare
     * */

//    private static PazienteService pazienteService;
//    ApplicationTests(PazienteService pazienteService) {
//        this.pazienteService = pazienteService;
//    }



//    @BeforeAll
//    public static void execBeforeAll() {
//        System.out.println("Before all");
//    }


//    @Test
//    void ricercaPazienteTramiteCodiceFiscaleOk() {
//
//        // TEST DI RICERCA PAZIENTE TRAMITE CODICE FISCALE
//        Paziente p = pazienteService.getPazienteByCodiceFiscale("AAAGGG66F66F666F");
//
//
//        if (p != null)
//            System.out.println("\n\nPAZIENTE CERCATO:" + p.getNome() + "\t" + p.getCognome() + "\n");
//        else
//            System.out.println("\n\nPAZIENTE NON TROVATO:\n");
//
//
//    }




    @Test
    void contextLoads() {
        /** Test di controllo integrita' (gia' presente di default in Spring Boot).
         * Fallirà se il contesto dell'applicazione non può essere avviato
         *
         * */
    }

}
