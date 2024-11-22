package com.beltra.visitemediche.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.util.*;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DomainTest {



    @Test
    public void entitiesCreationOk() {
        List<Object> listaEntita = new ArrayList<>();

        Paziente paziente = new Paziente();
        Medico medico = new Medico();
        Anagrafica anagrafica = new Anagrafica();
        Articolo articolo = new Articolo();
        Ingrediente ingrediente = new Ingrediente();
        Utente utente = new Utente();
        Ruolo ruolo = new Ruolo();

        listaEntita.add(paziente);
        listaEntita.add(medico);
        listaEntita.add(anagrafica);
        listaEntita.add(articolo);
        listaEntita.add(ingrediente);
        listaEntita.add(utente);
        listaEntita.add(ruolo);

        listaEntita.forEach( Assertions::assertNotNull );

    }


    @Test
    public void entitiesPopulationOk() {

        // TODO: ARRANGE
        List<Object> listaEntita = new ArrayList<>();

        Medico medico = new Medico();
        Anagrafica anagrafica = new Anagrafica();
        Paziente paziente = new Paziente();

        Articolo articolo = new Articolo();
        Ingrediente ingrediente = new Ingrediente();

        Utente utente = new Utente();
        Ruolo ruolo = new Ruolo();



        // TODO: ACT in effetti vado a richiamare i metodi setter()

        // Prima deve esistere il medico
        medico.setCodice("abcd10");
        medico.setNome("Fabrizio");
        medico.setCognome("Azzurri");
        medico.setCitta("Roma");

        // Dopo deve esistere il paziente
        paziente.setCodiceFiscale("BLTMMM88M21A834B");
        paziente.setNome("Leonardo");
        paziente.setCognome("Di Caprio");
        paziente.setMedico( medico );

        // Dopo aver inserito il paziente deve esistere l'anagrafica
        anagrafica.setId(12L);
        anagrafica.setEmail("leonardo@gmail.com");
        anagrafica.setTelefono(3498771232L);
        anagrafica.setPaziente(paziente);

        // Prima deve esistere l'articolo
        articolo.setCodArt("a00001");
        articolo.setUm("Wsd");
        articolo.setDataCreazione( new GregorianCalendar(2024, Calendar.NOVEMBER, 20).getTime() );
        articolo.setDescrizione("blablablablabla");
        articolo.setCodStat("xcdfsdf");
        articolo.setIdIva(2);
        articolo.setIdFamAss(3);
        articolo.setPesoNetto(100.90);
        articolo.setIdStatoArt("cccccc");
        articolo.setPzCart("hohoho");

        // Dopo aver inserito l'articolo deve esistere l'ingrediente
        ingrediente.setCodArt("a00001");
        ingrediente.setArticolo( articolo );

        // Prima deve esistere l'utente
        utente.setId("UT0001");
        utente.setUsername("leonecapr99");
        utente.setPassword("ppaasswwoorrdd");
        utente.setAttivo(true);

        // Dopo deve esistere i ruoli
        ruolo.setId(1L);
        ruolo.setRuolo("USER");
        ruolo.setUtente( utente );


        listaEntita.add(paziente);
        listaEntita.add(medico);
        listaEntita.add(anagrafica);
        listaEntita.add(articolo);
        listaEntita.add(ingrediente);
        listaEntita.add(utente);
        listaEntita.add(ruolo);


        // TODO: Assert

        // Verifico per ogni entita' che non sia null
        listaEntita.forEach(Assertions::assertNotNull);

        // Verifico che ogni entita' abbia i valori aspettati (inseriti correttamente)
        assertEquals( medico.getNome(),  "Fabrizio");
        assertEquals( medico.getCognome() , "Azzurri");
        assertEquals( medico.getCodice() , "abcd10");
        assertEquals( medico.getCitta() , "Roma");

        assertEquals(paziente.getCodiceFiscale(), "BLTMMM88M21A834B");
        assertEquals(paziente.getNome(), "Leonardo");
        assertEquals(paziente.getCognome(), "Di Caprio");

        assertEquals(anagrafica.getId(), 12L);
        assertEquals(anagrafica.getEmail(), "leonardo@gmail.com");
        assertEquals(anagrafica.getTelefono(), 3498771232L);

        assertEquals(articolo.getCodArt(), "a00001");
        assertEquals(articolo.getUm(), "Wsd");
        assertEquals(articolo.getDescrizione(), "blablablablabla");

        assertEquals(utente.getId(), "UT0001");
        assertEquals(utente.getUsername(), "leonecapr99");
        assertEquals(utente.isAttivo(), true);

        assertEquals(ruolo.getId(), 1L);
        assertEquals(ruolo.getRuolo(), "USER");



    }

}
