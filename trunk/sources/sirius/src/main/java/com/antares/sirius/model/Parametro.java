package com.antares.sirius.model;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Parametro extends PersistentObject {

	private String nombre;
	private String valor;
	private String descripcion;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
