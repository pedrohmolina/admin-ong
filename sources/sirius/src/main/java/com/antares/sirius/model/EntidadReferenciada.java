package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@SuppressWarnings("serial")
public class EntidadReferenciada extends PersistentObject {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idEntidad"))
	private Entidad entidad;
	
	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idEntidadReferenciada"))
	private Entidad entidadReferenciada;
	
	private String nombreEntidad;
	private Boolean opcional;

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Entidad getEntidadReferenciada() {
		return entidadReferenciada;
	}

	public void setEntidadReferenciada(Entidad entidadReferenciada) {
		this.entidadReferenciada = entidadReferenciada;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public Boolean getOpcional() {
		return opcional;
	}

	public void setOpcional(Boolean opcional) {
		this.opcional = opcional;
	}

}
