package com.antares.sirius.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Recurso extends PersistentObject {

    @Column(nullable = false)
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
