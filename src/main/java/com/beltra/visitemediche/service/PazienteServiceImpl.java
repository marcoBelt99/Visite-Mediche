package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Paziente;
import com.beltra.visitemediche.dto.PazienteDTO;
import com.beltra.visitemediche.repository.MedicoRepository;
import com.beltra.visitemediche.repository.PazienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PazienteServiceImpl implements PazienteService {

    // TODO: code injection del repository associato
    // TODO: faccio il code injection tramite il costruttore e non tramite autowired
    private final PazienteRepository pazienteRepository;
    private final MedicoRepository medicoRepository;

    public PazienteServiceImpl(PazienteRepository pazienteRepository,
                               MedicoRepository medicoRepository) {
        this.pazienteRepository = pazienteRepository;
        this.medicoRepository = medicoRepository;
    }


    @Override
    public List<Paziente> getAllPazienti() {
        return pazienteRepository.findAll();
    }


    @Override
    public Paziente getPazienteByCodiceFiscale(String codiceFiscale) {
        return pazienteRepository.findByCodiceFiscale(codiceFiscale);
    }


    @Override
    public List<Paziente> getPazienteByCognome(String cognome, int page, int numrec) {
        // TODO: siccome sto usando dei filtri, devo aggiungere anche i cosiddetti caratteri jolly
        //       pertanto devo concatenare alla stringa di filtro originaria
        //       Quindi, cercami tutti gli elementi che hanno qualcosa di variabile, più il filtro che ho inserito, più
        //       qualcos altro.

        //cognome = "%".concat(cognome.toUpperCase().concat("%"));
        cognome = "%".concat(cognome.concat("%"));


        // TODO: predispongo il pageable, per gestire il numero della pagina e il numero di record
        Pageable pageable = PageRequest.of(page, numrec);

        return pazienteRepository.findByCognomeLike(cognome, pageable);

    }

    @Override
    public Paziente getPazienteByCognomeAndNome(String cognome, String nome) {

        return pazienteRepository.selPazienteByCognomeAndNome(cognome, nome);
    }


    @Override
    @Transactional // necessaria per le operazioni di modifica o di delete // TODO: @Transactional la si mette solo nello strato di servizio
    public void updateMedicoPazienteByCodiceFiscale(String codiceFiscale, String codiceMedico) {
        pazienteRepository.updateMedicoByCodiceFiscale( codiceFiscale, codiceMedico );
    }


    // TODO: metodo per convertire Paziente in PazienteDTO,
    //  utile per serializzare dati e scambiarli con il cliente mediante AJAX
    public PazienteDTO getPazienteDTOByCodiceFiscale(String codiceFiscale) {
        Paziente paziente = getPazienteByCodiceFiscale(codiceFiscale);
        return new PazienteDTO(paziente.getCodiceFiscale(), paziente.getCognome(), paziente.getNome());
    }


    @Override
    public boolean existsMedicoToAssign(String codiceMedico) {
        return medicoRepository.findByCodice(codiceMedico) != null;
    }
}
