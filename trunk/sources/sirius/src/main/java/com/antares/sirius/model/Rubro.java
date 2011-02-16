package com.antares.sirius.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class Rubro extends BusinessObject {

	private String nombre;
	private String descripcion;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idRubroPadre"))
	private Rubro rubroPadre;

	@OneToMany(mappedBy = "rubroPadre", cascade = CascadeType.ALL)
	private Set<Rubro> subRubros;

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

	public Rubro getRubroPadre() {
		return rubroPadre;
	}

	public void setRubroPadre(Rubro rubroPadre) {
		this.rubroPadre = rubroPadre;
	}

	public Set<Rubro> getSubRubros() {
		return subRubros;
	}

	public void setSubRubros(Set<Rubro> subRubros) {
		this.subRubros = subRubros;
	}

	@Override
	public void setActivo(Boolean activo) {
		super.setActivo(activo);
		if (!activo) {
			for (Rubro rubro : this.subRubros) {
				rubro.setActivo(activo);
			}
		}
	}

}
