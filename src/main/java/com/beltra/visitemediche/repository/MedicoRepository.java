package com.beltra.visitemediche.repository;

import com.beltra.visitemediche.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {

    Medico findByCodice(String codiceMedico );
}
