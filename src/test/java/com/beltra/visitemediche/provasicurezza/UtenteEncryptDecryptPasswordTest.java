package com.beltra.visitemediche.provasicurezza;

import com.beltra.visitemediche.domain.Ruolo;
import com.beltra.visitemediche.domain.Utente;
import com.beltra.visitemediche.repository.UtenteRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // TODO: per dare un ordine di esecuzione ai miei metodi
public class UtenteEncryptDecryptPasswordTest {

    // Code Injection
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String nuovoId; // viene inizializzato una volta sola grazie ad un metodo annotato @BeforeAll

    private final String username = "ebemarco";
    private final String passwordInChiaro = "banana44";


    /** Devo inizializzare il valore dell'id utente da analizzare, che per tutti i test non deve mai cambiare.
     *  <br>
     *  Potrebbe cambiare visto che sto generando l'id utente tramite il numero di record sulla tabella utenti.
     *  */
    @BeforeAll
    static void init(@Autowired UtenteRepository utenteRepository) { // TODO: necessario utenteRepository al fine del funzionamento
        nuovoId = creaIdUtenteUnivoco( utenteRepository ); // Inizializza l'ID una sola volta
    }


    /** Prima inserisco un utente con una password criptata */
    @Test
    @Order(1)
    void testInsertUtenteWithEncryptedPassword () {


        // TODO: Cripto la password
        String passwordCriptata = passwordEncoder.encode(passwordInChiaro);

        // Creo l'oggetto Utente
        Utente nuovoUtente = new Utente();
        nuovoUtente.setId(nuovoId);
        nuovoUtente.setUsername(username);
        nuovoUtente.setPassword(passwordCriptata); // password criptata!
        nuovoUtente.setAttivo(true);

        Ruolo ruolo = new Ruolo("USER", null);

        // USO DEL METODO helper per salvare automaticamente anche il ruolo (nota che l'oggetto di classe Ruolo che
        // ho appena creato, ha l'attributo 'utente' a null
        // ==> ci pensa in automatico Hibernate ad assegnare il giusto utente all'oggetto di classe Ruolo, nel momento in cui vado a salvare l'utente
        nuovoUtente.addRuolo(ruolo);

        // Persistenza Cascade: Grazie a cascade = CascadeType.ALL nella relazione tra Utente e Ruolo,
        // ==> Hibernate salva automaticamente i ruoli quando salvi l'utente.
        // Salvo l'utente (cascade = ALL si occupa dei ruoli) ===> figo! Mi basta salvare l'utente !!!
        utenteRepository.save(nuovoUtente);

        // TODO: testo se effettivamente si è salvato il mio utente
        Utente utenteSalvato = utenteRepository.findById(nuovoId).orElse(null);

        // TODO: assert
        assertNotNull(utenteSalvato);

        System.out.println("\n\nUTENTE INSERITO: " + username + "\n");

    }


    /** Poi voglio verificare che la password sia pari a quella salvata
     *  andando a decriptarla
     *  */
    @Test
    @Order(2)
    void testDecryptPassword () {

        // Prendi l'utente appena inserito
        Utente utenteFind = utenteRepository.findById( nuovoId ).orElse(null);

        String passwordCriptata = utenteFind.getPassword();

        boolean passwordMatches = passwordEncoder.matches("banana44", passwordCriptata);

        // TODO: assert
        assertTrue( passwordMatches );

    }


    @Disabled
    /** TODO: Per seguire la "Procedura più veritiera",
     *   devo eseguire questo metodo DOPO il metodo insertUtenteWithAutomaticallyRuoloUSER()  */
    @Test
    @Order(3)
    public void addRuoloADMINForUserById () {


        // Prendi l'utente appena inserito
        Utente utenteFind = utenteRepository.findById( nuovoId ).orElse(null);

        Ruolo ruoloAdmin = new Ruolo("ADMIN", null);

        // TODO: aggiungere il ruolo di ADMIN ad uno specifico utente dato lo username

        utenteFind.addRuolo( ruoloAdmin );

        // TODO: AGGIORNA questo utente
        utenteRepository.save( utenteFind );

        // TODO: assert
        assertEquals("ADMIN",

                utenteFind
                        .getRuoli()
                        .stream()
                        .filter(r -> r.getRuolo().equals("ADMIN"))
                        .findFirst()
                        .get()
                        .getRuolo()
        );

        if(utenteFind == null)
            System.out.println("UTENTE NON TROVATO!");
    }

    /** Questo metodo deve essere statico in quanto viene usato dal @BeforeAll */
    protected static String creaIdUtenteUnivoco (UtenteRepository utenteRepository) { // necessario il parametro del repository per il funzionamento

        final String PRIMAPARTEID = "UT00000";
        String nuovoIdUtente = "";
        Long numUtenti = -1l;

        numUtenti = utenteRepository.count();
        numUtenti++;

        nuovoIdUtente = PRIMAPARTEID + numUtenti;

        return nuovoIdUtente;
    }

}
