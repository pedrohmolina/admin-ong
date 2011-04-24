package com.antares.sirius.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@SuppressWarnings("serial")
public class RelacionContractual extends BusinessObject {

    @Column(nullable = false)
	private String nombre;

    @Column(nullable = false)
    private String descripcion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
