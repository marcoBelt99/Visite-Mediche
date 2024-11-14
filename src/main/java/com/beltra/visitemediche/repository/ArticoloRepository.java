package com.beltra.visitemediche.repository;

import com.beltra.visitemediche.domain.Articolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, String> {



}
