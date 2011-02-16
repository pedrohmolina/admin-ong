package com.antares.sirius.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class Usuario extends BusinessObject {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns(@JoinColumn(name = "idPersona"))
	private Persona persona;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idPerfil"))
	private Perfil perfil;

	private String username;
	private String password;
	private Boolean bloqueado = Boolean.FALSE;

    public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

}
