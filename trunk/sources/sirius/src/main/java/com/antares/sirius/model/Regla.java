package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@SuppressWarnings("serial")
public class Regla extends BusinessObject {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idUsuario"))
	private Usuario usuario;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idEntidad"))
	private Entidad entidad;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idAtributo"))
	private Atributo atributo;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idOperador"))
	private Operador operador;

	private String valor;

	public Entidad getEntidad() {
		return entidad;
	}
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	public Atributo getAtributo() {
		return atributo;
	}
	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	public Operador getOperador() {
		return operador;
	}
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

}
