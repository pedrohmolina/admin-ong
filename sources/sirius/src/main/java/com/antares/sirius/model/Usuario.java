package com.antares.sirius.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

@Entity
@SuppressWarnings("serial")
public class Usuario extends BusinessObject implements UserDetails {

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

    @Override
	public GrantedAuthority[] getAuthorities() {
    	Set<Acceso> accesos = new HashSet<Acceso>();
    	accesos.add(Acceso.getAccesoBase());
    	for (Rol rol : this.getPerfil().getRoles()) {
    		if (rol.isActivo()) {
    			accesos.addAll(rol.getAccesos());
    		}
    	}

    	int i = 0;
    	GrantedAuthority[] authorities = new GrantedAuthority[accesos.size()];
    	for (Acceso acceso : accesos) {
    		authorities[i++] = acceso; 
		}
    	return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !getBloqueado();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !getBloqueado();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !getBloqueado();
	}

	@Override
	public boolean isEnabled() {
		return !getBloqueado();
	}

}
