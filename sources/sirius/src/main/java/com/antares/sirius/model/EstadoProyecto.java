package com.antares.sirius.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class EstadoProyecto extends PersistentObject {

	private String descripcion;

	@OneToMany
	@JoinColumn(name="idEstadoAnterior")
	private List<EstadoProyecto> proximosEstadosPosibles;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EstadoProyecto> getProximosEstadosPosibles() {
		return proximosEstadosPosibles;
	}

	public void setProximosEstadosPosibles(List<EstadoProyecto> proximosEstadosPosibles) {
		this.proximosEstadosPosibles = proximosEstadosPosibles;
	}

}
