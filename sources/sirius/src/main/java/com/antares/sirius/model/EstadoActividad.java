package com.antares.sirius.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class EstadoActividad extends PersistentObject {

	private String descripcion;

	@ManyToMany
	@JoinTable(name = "proximoEstadoActividad", joinColumns = @JoinColumn(name="idEstadoActividad"), inverseJoinColumns = @JoinColumn(name = "idProximoEstadoActividad"))
	private Set<EstadoActividad> proximosEstadosPosibles;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<EstadoActividad> getProximosEstadosPosibles() {
		return proximosEstadosPosibles;
	}

	public void setProximosEstadosPosibles(Set<EstadoActividad> proximosEstadosPosibles) {
		this.proximosEstadosPosibles = proximosEstadosPosibles;
	}

}
