package com.daniel.cjavapaciente.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "empresa")
public class Empresa {
	@Id	 
	private Long idEmpresa;
	private String nombre;
	private String telefono;
	private String direccion;

	@Transient
	private Integer port;
	@Transient
	private String instanciaId;
	
	 
	public Empresa(Long idEmpresa, String nombre, String telefono, String direccion) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idSignos) {
		this.idEmpresa = idSignos;
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