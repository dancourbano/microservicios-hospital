package com.daniel.cjavasignos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daniel.cjavasignos.model.Empleado;
 
@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer>{

}
