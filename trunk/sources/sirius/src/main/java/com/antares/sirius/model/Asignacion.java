package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Asignacion extends BusinessObject {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idTipoAsignacion"))
	private TipoAsignacion tipoAsignacion;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idPersona"))
	private Persona persona;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idActividad"))
	private Actividad actividad;

	private Integer cantidad;

	public TipoAsignacion getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(TipoAsignacion tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
