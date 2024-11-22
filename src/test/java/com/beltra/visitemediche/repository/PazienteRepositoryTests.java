package com.beltra.visitemediche.repository;

import com.beltra.visitemediche.domain.Medico;
import com.beltra.visitemediche.domain.Paziente;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.assertj.core.api.Assertions; // TODO: faccio uso di AssertJ
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test") // Dico a Spring di usare il profilo di configurazione di test per questa classe
@DataJpaTest // attivo il testing dei dati gestiti con Spring Data Jpa
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // uso un db fasullo in memoryanzichè quello origina
public class PazienteRepositoryTests {



    @Autowired
    private PazienteRepository pazienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;



    @BeforeEach
    public void setUp() {
        // 1) Arrange
        Medico m1 = new Medico(); // Prima e' obbligatorio creare il medico, e dopo i pazienti
        m1.setCodice("m000112");
        m1.setNome("Maria");
        m1.setCognome("De Filippi");
        m1.setCitta("");
        medicoRepository.save(m1);

        Paziente p1 = new Paziente();
        Paziente p2 = new Paziente();
        Paziente p3 = new Paziente();

        p1.setCodiceFiscale("ABCDEFG11111XXXX");
        p1.setNome("Lucio");
        p1.setCognome("Battisti");
        p1.setMedico(m1);

        p2.setCodiceFiscale("ABCDEFG22222XXXX");
        p2.setNome("Elisa");
        p2.setCognome("Manfredi");
        p2.setMedico(m1);

        p3.setCodiceFiscale("ABCDEFG33333XXXX");
        p3.setNome("Julia");
        p3.setCognome("Rossi");
        p3.setMedico(m1);

        // 2) Act
        pazienteRepository.save(p1);
        pazienteRepository.save(p2);
        pazienteRepository.save(p3);


    }


    /** Nota sul nome del metodo di test: lo divido in 3 parti:
     *  PazienteRepository ==> nome del Repository (se avessi avuto un service mettevo PazienteService, se invece un controller PazienteController)
     *  SaveAll ==> nome della funzionalità (della funzione)
     *  ReturnsSavedPaziente ==> comportamento che voglio testare.
     *  In questo metodo di esempio non uso i dati comuni (inizializzati nel BeforeEach, ma dei dati che creo al momento)
     *
     * */

    @Test
    public void PazienteRepository_SaveAll_ReturnSavedPaziente() {

        // 1) Arrange
        Paziente paziente = new Paziente();
        paziente.setCodiceFiscale("ABCDEFG11111XXXX");
        paziente.setNome("Lucio");
        paziente.setCognome("Dalla");

        // 2) Act
        Paziente savedPaziente = pazienteRepository.save(paziente);

        // 3) Assert
        Assertions.assertThat( savedPaziente ).isNotNull();
        Assertions.assertThat( savedPaziente.getCodiceFiscale().length() ).isEqualTo( 16 );
    }



    /** Qui recupero i dati dal BeforeEach */
    @Test
    public void PazienteRepository_GetAll_ReturnMoreThanOnePaziente() {

        // Assert
        List<Paziente> listaPazienti = pazienteRepository.findAll();

        Assertions.assertThat( listaPazienti ).isNotNull();
        Assertions.assertThat( listaPazienti.size() ).isEqualTo( 3 );
    }



    /** Anche in questo metodo faccio uso dei dati comuni */
    @Test
    public void PazienteRepository_FindByCodiceFiscale_ReturnPaziente() {

        // Assert
         Paziente pazienteTrovato = pazienteRepository.findByCodiceFiscale( "ABCDEFG11111XXXX" );

       Assertions.assertThat( pazienteTrovato ).isNotNull();
    }


    /** Qui provo a testare il funzionamento di un metodo custom */
    @Test
    public void PazienteRepository_CountPazientiWithThisCognome_IsNotZero() {

        // 1) Arrange
        Medico medico = medicoRepository.findByCodice("m000112"); // recupero il codice di un medico
        Paziente paziente = new Paziente();

        paziente.setCodiceFiscale("ABCCCCC11111XXXX");
        paziente.setNome("Diletta");
        paziente.setCognome("Battisti");
        paziente.setMedico(medico);
        pazienteRepository.save(paziente);

        // 2) Act
        int numPersoneWithThisCognomeExpected = 2;
        int numPersoneWithThisCognomeActual = pazienteRepository.countPazientiWithThisCognome( paziente.getCognome() );

        // 3) Assert
        Assertions.assertThat( paziente ).isNotNull();
        assertEquals( numPersoneWithThisCognomeExpected, numPersoneWithThisCognomeActual );
    }


    /** Qui provo a testare il funzionamento di un metodo custom: testo la condizione duale */
    @Test
    public void PazienteRepository_CountPazientiWithThisCognome_IsZero() {

        // 1) Arrange
        Medico medico = medicoRepository.findByCodice("m000112"); // recupero il codice di un medico
        Paziente paziente = new Paziente();

        paziente.setCodiceFiscale("ABCCCCC11111XXXX");
        paziente.setNome("Diletta");
        paziente.setCognome("De Filippi");
        paziente.setMedico(medico);
        pazienteRepository.save(paziente);

        // 2) Act
        int numPersoneWithThisCognomeExpected = 0;
        int numPersoneWithThisCognomeActual = pazienteRepository.countPazientiWithThisCognome( "XXXXDe FilippiXXXX" );

        // 3) Assert
        Assertions.assertThat(paziente).isNotNull();
        assertEquals( numPersoneWithThisCognomeExpected, numPersoneWithThisCognomeActual );
    }



}
