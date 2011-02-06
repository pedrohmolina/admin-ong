package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Presupuesto extends PersistentObject {

	@ManyToOne
	private Actividad actividad;

	@ManyToOne
	private TipoGasto tipoGasto;

	private Double monto;

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}
