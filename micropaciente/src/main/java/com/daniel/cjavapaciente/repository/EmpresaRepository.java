package com.daniel.cjavapaciente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daniel.cjavapaciente.model.Empresa;

 
@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, Integer> {
	  
}
