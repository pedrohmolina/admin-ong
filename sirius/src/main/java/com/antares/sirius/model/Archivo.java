package com.antares.sirius.model;

import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;



@Entity
@SuppressWarnings("serial")
public class Archivo extends PersistentObject {

    private String nombre;
    private String hash;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 100000000)
    private Blob contenido;

	public Blob getContenido() {
		return contenido;
	}

	public void setContenido(Blob contenido) {
		this.contenido = contenido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
