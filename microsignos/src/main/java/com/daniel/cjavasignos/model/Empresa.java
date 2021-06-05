package com.daniel.cjavasignos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

 


public class Empresa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEmpresa;
	private String nombre;
	private String telefono;
	private String direccion;

	 
	private Integer port;
	 
	private String instanciaId;
	
	public Empresa() {
		 
	}

	public Empresa(String nombre, String telefono, String direccion) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		 
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getInstanciaId() {
		return instanciaId;
	}

	public void setInstanciaId(String instanciaId) {
		this.instanciaId = instanciaId;
	}

	
}
