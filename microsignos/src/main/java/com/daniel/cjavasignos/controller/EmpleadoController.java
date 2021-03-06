package com.daniel.cjavasignos.controller;


import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.daniel.cjavasignos.client.EurekaClient;
import com.daniel.cjavasignos.dto.EmpleadoDto;
import com.daniel.cjavasignos.model.Empleado;
import com.daniel.cjavasignos.model.Empresa;
import com.daniel.cjavasignos.repository.EmpleadoRepository;
 
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@RestController
@RequestMapping("*")
public class EmpleadoController {
	@Autowired
	private Environment env;
	@Autowired
	private EmpleadoRepository service;
	@Autowired
	private EurekaClient eureka;
	@Autowired
	private RestTemplate restTemplate;
	 
	 
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@GetMapping("/empleado/{codigo}")
	public EmpleadoDto getEmpleado(@PathVariable Integer codigo)
	{	
		 
		Empleado empleado = service.findById(codigo).get();
		Empresa empresa = restTemplate.getForObject("http://SERVICIO.EMPRESA/empresa/"+empleado.getEmpresaId(), Empresa.class);
		EmpleadoDto empleadoDto=convertirDtoAModelo(empleado);
		empleadoDto.setEmpresa(empresa);
		return empleadoDto;
	} 
	@PostMapping("/empleado")
	public Empleado addEmployee(@RequestBody Empleado newEmpresa)
	{
		Empleado prod = new Empleado(newEmpresa.getNombres(),newEmpresa.getApellidos(),newEmpresa.getTelefono(),newEmpresa.getDni(),newEmpresa.getDireccion(),newEmpresa.getEmpresaId());
		
		return service.save(prod);
	} 
	@HystrixCommand(fallbackMethod = "fallbackMethod1")
	@GetMapping("/empresa/{codigo}")
	public Empresa getEmpresa(@PathVariable Integer codigo)
	{
		URI catalogoURI = eureka.getUri("SERVICIO.EMPRESA");
		String url="http://SERVICIO.EMPRESA/empresa/"+codigo;
		Empresa empresa = restTemplate.getForObject(url, Empresa.class);
		
		return empresa;
	}
	
	 
	private EmpleadoDto fallbackMethod(Integer codigo, Throwable exception) {
		Empresa empresa=new Empresa("Empresa Default SA","0000000","direccion default");
		System.out.println("real exception : {}"+ exception.getMessage());
		return new EmpleadoDto(0,"Usuario ","Default", "00000","44444444","Trujillo",empresa);
	}
	private Empresa fallbackMethod1(Integer codigo, Throwable exception) {
		Empresa empresa=new Empresa("Empresa Default SA","0000000","direccion default");
		System.out.println("real exception : {}"+ exception.getMessage());
		return empresa;
	}
	private EmpleadoDto convertirDtoAModelo(Empleado empleado) {
		EmpleadoDto empleadoDto = new EmpleadoDto();
		empleadoDto.setNombres(empleado.getNombres());
		empleadoDto.setApellidos(empleado.getApellidos());
		empleadoDto.setDireccion(empleado.getDireccion());
		empleadoDto.setDni(empleado.getDni());
		empleadoDto.setTelefono(empleado.getTelefono());
		empleadoDto.setIdEmpleado(empleado.getIdEmpleado());
		return empleadoDto;
	}
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}