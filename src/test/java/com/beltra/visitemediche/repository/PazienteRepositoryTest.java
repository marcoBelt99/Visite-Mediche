package com.beltra.visitemediche.repository;

import com.beltra.visitemediche.domain.Medico;
import com.beltra.visitemediche.domain.Paziente;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

// @DataJpaTest // attivo il testing dei dati gestiti con Spring Data Jpa
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // uso un db fasullo anzichè quello origina
@SpringBootTest
public class PazienteRepositoryTest {

    @Autowired
    private PazienteRepository pazienteRepository;

    @Disabled
    @Test
    public void PazienteRepository_SaveAll_ReturnSavedPaziente() {

//        Medico m = new Medico(m.setCodice("abcd6"), m.setCognome(""););
        // 1) Arrange
//        Paziente p = new Paziente("TTTMMM99M30A059W", "Sartore", "Matteo", "abcd1", );

        // 2) Act


        // 3) Assert

    }


}
