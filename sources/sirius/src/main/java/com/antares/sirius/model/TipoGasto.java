package com.antares.sirius.model;

import javax.persistence.Entity;



@Entity
@SuppressWarnings("serial")
public class TipoGasto extends PersistentObject {

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
