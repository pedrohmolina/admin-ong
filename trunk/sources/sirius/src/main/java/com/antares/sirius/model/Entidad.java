package com.antares.sirius.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@SuppressWarnings("serial")
public class Entidad extends PersistentObject {

	private String descripcion; 
	private String nombreEntidad;

	@OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
	private Set<Atributo> atributos;


	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public Set<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(Set<Atributo> atributos) {
		this.atributos = atributos;
	}

}
