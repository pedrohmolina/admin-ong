package com.antares.sirius.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@SuppressWarnings("serial")
public class Rol extends BusinessObject {

    @Column(nullable = false)
	private String nombre;

    @Column(nullable = false)
    private String descripcion;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "rolacceso", joinColumns = @JoinColumn(name="idRol"), inverseJoinColumns = @JoinColumn(name = "idAcceso"))
	private Set<Acceso> accesos;

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

	public Set<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(Set<Acceso> accesos) {
		this.accesos = accesos;
	}

}
