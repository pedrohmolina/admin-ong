package com.antares.sirius.model;

import javax.persistence.Entity;



@Entity
@SuppressWarnings("serial")
public class TipoPresupuesto extends PersistentObject {

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
