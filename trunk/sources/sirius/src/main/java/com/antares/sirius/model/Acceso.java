package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@SuppressWarnings("serial")
public class Acceso extends PersistentObject implements Comparable<Acceso>{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idRecurso"))
	private Recurso recurso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idAccion"))
	private Accion accion;

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	@Override
	public int compareTo(Acceso arg0) {
		return (((Acceso)arg0).toString()).compareTo(this.toString());
	}

	@Override
	public String toString() {
		return this.recurso.getDescripcion() + "-" + this.accion.getDescripcion();
	}
	
}
