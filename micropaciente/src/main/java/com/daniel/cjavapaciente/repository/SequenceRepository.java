package com.daniel.cjavapaciente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daniel.cjavapaciente.model.Sequence;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String> {
	Sequence findTopByOrderByIdDesc();
	
	
}
