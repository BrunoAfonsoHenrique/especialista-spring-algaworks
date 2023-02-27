package com.algaworks.clinica.domain.repository;

import com.algaworks.clinica.domain.model.AnimalDomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalDomestico, Long> {

}
