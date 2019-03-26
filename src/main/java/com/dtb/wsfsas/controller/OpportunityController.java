package com.dtb.wsfsas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dtb.wsfsas.model.entities.Opportunity;
import com.dtb.wsfsas.model.repositories.OpportunityRepository;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {
	@Autowired
	private OpportunityRepository repository;

	@GetMapping
	public List<Opportunity> list() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Opportunity> findById(@PathVariable("id") Long id) {
		Optional<Opportunity> opportunity = repository.findById(id);

		return opportunity.isPresent() ? ResponseEntity.ok(opportunity.get()) : ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<Opportunity> save(@Validated @RequestBody Opportunity opportunity) {
		if(repository
				.findByDescriptionAndNameProspect(opportunity
						.getDescription(), opportunity
						.getNameProspect())
				.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists a opportunity with this description and prospect");
		return new ResponseEntity<Opportunity>(repository.save(opportunity),HttpStatus.CREATED);
	}
}
