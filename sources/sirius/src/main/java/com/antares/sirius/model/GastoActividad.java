package com.antares.sirius.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;



@Entity
@DiscriminatorValue("3")
@SuppressWarnings("serial")
public class GastoActividad extends Gasto {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idActividad"))
	private Actividad actividad;

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}
