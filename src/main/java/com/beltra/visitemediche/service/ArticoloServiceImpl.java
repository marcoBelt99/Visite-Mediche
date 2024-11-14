package com.beltra.visitemediche.service;

import com.beltra.visitemediche.domain.Articolo;
import com.beltra.visitemediche.dto.ArticoloDTO;
import com.beltra.visitemediche.repository.ArticoloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoloServiceImpl implements ArticoloService {

    // Code Injection dei repository
    ArticoloRepository articoloRepository;

    // Code Injection del ModelMapper
    public ModelMapper modelMapper;

    // Code Injection tramite costruttore
    public ArticoloServiceImpl(ArticoloRepository articoloRepository,
                               ModelMapper modelMapper) {
        this.articoloRepository = articoloRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Articolo> getAllArticoli() {
        return articoloRepository.findAll();
    }



    private ArticoloDTO convertToDto(Articolo articolo) {

        ArticoloDTO articoloDto = null;

        // Se articolo ha dei dati (!= null) utilizza allora il model mapper
        if(articolo != null) {
            articoloDto = modelMapper.map(articolo, ArticoloDTO.class);
        }

        // ritorno la riconversione dei miei dati dal formato Articolo al formato ArticoloDTO
        return articoloDto;
    }

}
