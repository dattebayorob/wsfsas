package com.dtb.wsfsas.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.wsfsas.model.entities.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{

	Optional<Opportunity> findByDescriptionAndNameProspect(String description, String nameProspect);
	
}
