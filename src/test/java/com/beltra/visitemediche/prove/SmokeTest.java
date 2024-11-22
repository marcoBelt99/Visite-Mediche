package com.beltra.visitemediche.prove;

import com.beltra.visitemediche.controller.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat; // TODO: serve importare il tool assertj

@SpringBootTest
public class SmokeTest {


    @Autowired
    private IndexController indexController;


    /** Test che fa uso di AssertJ (il quale mi fornisce assertThat() e altri metodi).
     *  per esprimere asserzioni di test.
     *  Voglio controllare se il contesto mi sta creando il controller
     * */
    @Test
    void esisteIndexController() throws Exception {
        assertThat( indexController )
        .isNotNull();
    }
}
