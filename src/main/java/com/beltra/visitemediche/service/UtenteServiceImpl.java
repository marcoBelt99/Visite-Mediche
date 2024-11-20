package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Ruolo;
import com.beltra.visitemediche.domain.Utente;
import com.beltra.visitemediche.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService {

    // Code Injection
    private UtenteRepository utenteRepository;

    private PasswordEncoder passwordEncoder;


    public UtenteServiceImpl(UtenteRepository utenteRepository,
                             PasswordEncoder passwordEncoder) {
    }


    @Override
    public Utente salvaUtente(String id, String username, String password) {
        // Cripto la password
        String passwordCriptata = passwordEncoder.encode(password);

        // TODO: gestione id utente del tipo "UT00000"+"3"
        // ...

        // Creo l'oggetto Utente
        Utente nuovoUtente = new Utente();
        // nuovoUtente.setId(id);
        nuovoUtente.setUsername(username);
        nuovoUtente.setPassword(passwordCriptata); // password criptata!
        nuovoUtente.setAttivo(true);

        // Salvo il nuovo utente a DB
        return utenteRepository.save(nuovoUtente);
    }



}
