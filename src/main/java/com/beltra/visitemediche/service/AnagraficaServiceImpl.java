package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Anagrafica;
import com.beltra.visitemediche.repository.AnagraficaRepository;
import org.springframework.stereotype.Service;

@Service
public class AnagraficaServiceImpl implements AnagraficaService {

    // Code Injection del repository
    private AnagraficaRepository anagraficaRepository;

    public AnagraficaServiceImpl(AnagraficaRepository anagraficaRepository) {
        this.anagraficaRepository = anagraficaRepository;
    }

    @Override
    public Anagrafica getAnagraficaByCodiceFiscale(String codiceFiscale) {
        return anagraficaRepository.findByPazienteCodiceFiscale(codiceFiscale) ;
    }
}
