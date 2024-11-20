package com.beltra.visitemediche.provasicurezza;

import com.beltra.visitemediche.domain.Ruolo;
import com.beltra.visitemediche.domain.Utente;
import com.beltra.visitemediche.repository.UtenteRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // TODO: per dare un ordine di esecuzione ai miei metodi
public class UtenteRuoliInsertTest {

    // Code Injection
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Parametri per la gestione dei ruoli
    public static final int ADMIN = 0;
    public static final int USER = 1;
    public static final int NUM_RUOLI = 2;



    // @Disabled
    @Test
    void insertUtenteWithEncryptedPasswordWithoutRuolo() {

        String username = "adamo";
        String passwordInChiaro = "ciaopwdciao";

        // Cripto la password
        String passwordCriptata = passwordEncoder.encode( passwordInChiaro );

        // Costruisco un nuovo id univoco da assegnare all'utente
        String nuovoId = creaIdUtenteUnivoco();

        // Creo l'oggetto Utente
        Utente nuovoUtente = new Utente();
        nuovoUtente.setId( nuovoId );
        nuovoUtente.setUsername(username);
        nuovoUtente.setPassword(passwordCriptata); // password criptata!
        nuovoUtente.setAttivo(true);

        utenteRepository.save( nuovoUtente );

        // TODO: testo se effettivamente si è salvato il mio utente
        Utente utenteSalvato = utenteRepository.findById( nuovoId ).orElse(null);

        assertNotNull( utenteSalvato );
    }




    /** TODO: Procedura "più veritiera"
     *  Metodo che assegna automatcamente il ruolo di "USER" all'utente che si sta creando.
     * <br>
     *  Che username viene assegnato ad ogni utente? Ne viene preso uno casualmente da un elenco di dati finti*/
    @Test
    public void insertUtenteWithEncryptedPasswordWithAutomaticallyRuoloUSER() {

        String username = "";

        String passwordInChiaro = "";

        List<List<String>> datiFinti = getDatiFinti();

        int indiceCasuale = ThreadLocalRandom.current().nextInt(0, datiFinti.get(0).size());
        username = datiFinti.get(0).get( indiceCasuale );
        passwordInChiaro = datiFinti.get(1).get( indiceCasuale );


        // TODO: Cripto la password
        String passwordCriptata = passwordEncoder.encode( passwordInChiaro );

        // TODO: Generazione id univoco
        String nuovoId = creaIdUtenteUnivoco();


        // Creo l'oggetto Utente
        Utente nuovoUtente = new Utente();
        nuovoUtente.setId( nuovoId );
        nuovoUtente.setUsername( username );
        nuovoUtente.setPassword( passwordCriptata ); // password criptata!
        nuovoUtente.setAttivo( true );

        Ruolo ruolo = new Ruolo("USER", null);

        // TODO: USO DEL METODO helper per salvare automaticamente anche il ruolo (nota che l'oggetto di classe Ruolo che
        //       ho appena creato, ha l'attributo 'utente' a null
        //       ==> ci pensa in automatico Hibernate ad assegnare il giusto utente all'oggetto di classe Ruolo, nel momento in cui vado a salvare l'utente
        nuovoUtente.addRuolo( ruolo );

        // TODO: Persistenza Cascade: Grazie a cascade = CascadeType.ALL nella relazione tra Utente e Ruolo,
        //       ==> Hibernate salva automaticamente i ruoli quando salvi l'utente.
        // Salvo l'utente (cascade = ALL si occupa dei ruoli) ===> figo! Mi basta salvare l'utente !!!
        utenteRepository.save( nuovoUtente );

        // TODO: testo se effettivamente si è salvato il mio utente
        Utente utenteSalvato = utenteRepository.findById( nuovoId ).orElse(null);

        // TODO: assert
        assertNotNull( utenteSalvato );

        System.out.println("\n\nUTENTE INSERITO: " + username + "\n");

    }


    /** TODO: Per seguire la "Procedura più veritiera",
     *   devo eseguire questo metodo DOPO il metodo insertUtenteWithAutomaticallyRuoloUSER()  */
    @Test
    public void addRuoloADMINForUserById() {

        // Chiave di ricerca
        String idUtente = "UT00000"+utenteRepository.count(); // visto che l'id utente è formato da una parte fissa + parte variabile

        Optional<Utente> utenteFind = utenteRepository.findById( idUtente );

        Ruolo ruoloAdmin = new Ruolo("ADMIN", null);

        // TODO: aggiungere il ruolo di ADMIN ad uno specifico utente dato lo username

        Utente utenteDaRendereAdmin = utenteFind.orElseThrow();
        utenteDaRendereAdmin.addRuolo( ruoloAdmin );

        // TODO: AGGIORNA questo utente
        utenteRepository.save( utenteDaRendereAdmin );

        // TODO: assert
        assertEquals("ADMIN",

                utenteDaRendereAdmin
                .getRuoli()
                .stream()
                .filter( r -> r.getRuolo().equals("ADMIN") )
                .findFirst()
                .get()
                .getRuolo()
        );
    }




    /** TODO: TEST INSERIMENTO CONTEMPORANEO UTENTE E RUOLI IN MODO RANDOM.
     *  <br>
     * Con questa configurazione, ogni volta che aggiungi un ruolo a un utente e salvi l'utente, i ruoli vengono automaticamente persistiti insieme.
     * Il tutto e' fatto in automatico da Hibernate.
     * */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void insertRandomUtenteWithEncryptedPasswordWithRuolo() {

        String username = "";
        String passwordInChiaro = "";

        List<List<String>> datiFinti = getDatiFinti();

        int indiceCasuale = ThreadLocalRandom.current().nextInt(0, datiFinti.get(0).size());

        username = datiFinti.get(0).get( indiceCasuale );
        passwordInChiaro = datiFinti.get(1).get( indiceCasuale );

        // TODO: se sceltaRuolo vale 0 allora admin, se vale 1 allora user
        int sceltaRuolo = -1;
        int fineRuoli = -1;
        Set<Ruolo> ruoliSet = new HashSet<>();


        while( (fineRuoli != NUM_RUOLI) /*|| (ruoliSet.size() <= NUM_RUOLI) */) {

            System.out.println("\nInserisci una scelta valido per'utente. [0=ADMIN, 1=USER]: ");
            sceltaRuolo = ThreadLocalRandom.current().nextInt(0, 2); // il bound è escluso ==> questo va da 0 a 1

            if( (sceltaRuolo == ADMIN) && ( ruoliSet.stream().noneMatch(r -> r.getRuolo().equals("ADMIN"))) )
                ruoliSet.add( new Ruolo("ADMIN", null) ); // TODO: l'utente lo assegno dopo
            else if( (sceltaRuolo == USER) && ( ruoliSet.stream().noneMatch(r -> r.getRuolo().equals("USER"))) )
                ruoliSet.add( new Ruolo("USER", null ) ); // TODO: l'utente lo assegno dopo

            System.out.println("\nFinire con l'inserimento dei ruoliSet? [2 per terminare]: ");
            fineRuoli = ThreadLocalRandom.current().nextInt(0, 2+1); // il buond è escluso ==> questo va da 0 a 2
        }


        // TODO: Cripto la password
        String passwordCriptata = passwordEncoder.encode( passwordInChiaro );

        // TODO: Generazione id univoco
        String nuovoId = creaIdUtenteUnivoco();


        // TODO:  Creo l'oggetto Utente
        Utente nuovoUtente = new Utente();
        nuovoUtente.setId( nuovoId );
        nuovoUtente.setUsername( username );
        nuovoUtente.setPassword( passwordCriptata ); // password criptata!
        nuovoUtente.setAttivo( true );

        // TODO: assegna l'utente ad ogni oggetto Ruolo dentro ruoliSet
        ruoliSet.forEach(nuovoUtente::addRuolo); // N.B.: rimpiazzo la lambda expression con la method inference...


        // TODO: Persistenza Cascade: Grazie a cascade = CascadeType.ALL nella relazione tra Utente e Ruolo,
        //  Hibernate salva automaticamente i ruoli quando salvi l'utente.
        // Salvo l'utente (cascade = ALL si occupa dei ruoli) ===> figo! Mi basta salvare l'utente !!!
        utenteRepository.save( nuovoUtente );


        // TODO: testo se effettivamente si è salvato il mio utente
        Utente utenteSalvato = utenteRepository.findById( nuovoId ).orElse(null);

        // TODO: assert
        assertNotNull( utenteSalvato );

        username = utenteSalvato.getUsername();

        String elencoRuoli = "[ ";
        for (Ruolo r : ruoliSet) {
            elencoRuoli = elencoRuoli + r.getRuolo() + " , ";
        }
        elencoRuoli = elencoRuoli + " ]";
        System.out.println( String.format("\nUtente %s e ruoli %s salvati con successo.\nFine test.\n", username, elencoRuoli) );

    }





    protected String creaIdUtenteUnivoco() {

        final String PRIMAPARTEID = "UT00000";
        String nuovoIdUtente = "";
        Long numUtenti = -1l;

        numUtenti = utenteRepository.count();
        numUtenti++;

        nuovoIdUtente = PRIMAPARTEID + numUtenti;

        return nuovoIdUtente;
    }




    /** Ritorna un insieme di dati finti */
    public List<List<String>> getDatiFinti() {

        /** lista di usernames */
        List<String> listaUsernames = new ArrayList<>();

        /** lista di passwords */
        List<String> listaPasswords = new ArrayList<>();

        /** primo elemento: lista di usernames
         * <br>
         * secondo elemento: lista di passwords */
        List<List<String>> listaDiListe = new ArrayList<>();

        listaUsernames.add("pippo64");
        listaUsernames.add("pluto11");
        listaUsernames.add("paperino");
        listaUsernames.add("gogeta99");
        listaUsernames.add("amedeo");
        listaUsernames.add("perugino");
        listaUsernames.add("bigtester_A11");
        listaUsernames.add("bigtester_B12");
        listaUsernames.add("bigtester_C13");
        listaUsernames.add("betatester_D14");

        listaPasswords.add("bibidi");
        listaPasswords.add("babidi");
        listaPasswords.add("buuu");
        listaPasswords.add("dragonball");
        listaPasswords.add("pratoverde");
        listaPasswords.add("adriaportoviro");
        listaPasswords.add("doppiocomputer");
        listaPasswords.add("gattinopipino");
        listaPasswords.add("elmeputinbeo");
        listaPasswords.add("vuolsicosicola");

        listaDiListe.add( listaUsernames );
        listaDiListe.add( listaPasswords );

        return listaDiListe;
    }


}
