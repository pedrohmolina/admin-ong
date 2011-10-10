package com.antares.sirius.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@SuppressWarnings("serial")
public class TipoAtributo extends PersistentObject {

	private String descripcion;

	@OneToMany(mappedBy = "tipoAtributo", cascade = CascadeType.ALL)
	private Set<Operador> operadores;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(Set<Operador> operadores) {
		this.operadores = operadores;
	} 

}
