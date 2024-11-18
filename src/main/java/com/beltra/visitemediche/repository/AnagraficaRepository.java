package com.beltra.visitemediche.repository;

import com.beltra.visitemediche.domain.Anagrafica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long> {

    Anagrafica findByPazienteCodiceFiscale(String codiceFiscale);
}
