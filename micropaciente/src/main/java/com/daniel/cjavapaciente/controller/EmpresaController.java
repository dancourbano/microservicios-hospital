package com.daniel.cjavapaciente.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.cjavapaciente.model.Empresa; 
import com.daniel.cjavapaciente.repository.EmpresaRepository;
import com.daniel.cjavapaciente.service.SequenceService;

@RestController
@CrossOrigin(origins = "*")
public class EmpresaController {
	@Autowired
	private Environment env;
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Autowired
	SequenceService sequenceService;
	
	@Value("${server.port}")
	private Integer port;
	@Value("${eureka.instance.instance-id}")
	private String instanceId;
	
	@GetMapping("/empresas")
	public List<Empresa> getPacientes()
	{
		return empresaRepository.findAll().stream().map(paciente->{
			paciente.setPort(Integer.valueOf(env.getProperty("local.server.port")));
			return paciente;
		}).collect(Collectors.toList());
		 
	}

	@GetMapping("/empresa/{codigo}")
	public Optional<Empresa> getPaciente(@PathVariable Integer codigo) throws Exception
	{
		Optional<Empresa> paciente = empresaRepository.findById(codigo);
		paciente.get().setPort(Integer.valueOf(env.getProperty("local.server.port")));
		paciente.get().setInstanciaId(instanceId);
		boolean ok=true;
		if(ok==false) {
			throw new Exception("No se pudo cargar empresa");
		}
		return paciente;
	}

	@PutMapping("/empresa/{codigo}")
	public Optional<Empresa> updateEmployee(@RequestBody Empresa newEmpresa, @PathVariable Integer codigo)
	{
		Optional<Empresa> optionalPaciente = empresaRepository.findById(codigo);
		if (optionalPaciente.isPresent()) {
			Empresa paciente = optionalPaciente.get();
			paciente.setNombre(newEmpresa.getNombre());			
			paciente.setDireccion(newEmpresa.getDireccion());
			paciente.setTelefono(newEmpresa.getTelefono());
			empresaRepository.save(paciente);
		}
		return optionalPaciente;
	}
	 
	 

	@PostMapping("/empresa")
	public Empresa addEmployee(@RequestBody Empresa newEmpresa)
	{
		Empresa prod = new Empresa(sequenceService.generateSequence("empresa"),newEmpresa.getNombre(),  newEmpresa.getTelefono(),newEmpresa.getDireccion());
		empresaRepository.save(prod);
		return prod;
	}
}
