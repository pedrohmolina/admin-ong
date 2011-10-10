package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Perfil;
import com.antares.sirius.model.Usuario;

/**
 * Representacion en la capa de vista de la entidad de modelo Perfil.
 *
 * @version 1.0.0 Created 14/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class UsuarioPersonaForm extends AbstractForm<Usuario> {

	private String username;
	private String password;
	private String password2;
	private String idPerfil;
	private Integer idPersona;
	private Collection<Perfil> perfiles;

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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Collection<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Collection<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.idPersona = null;
		this.username = "";
		this.password = "";
		this.password2 = "";
		this.idPerfil = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.idPersona = null;
		this.username = "";
		this.password = "";
		this.password2 = "";
		this.idPerfil = "";
	}

	@Override
	public void initializeForm(Usuario entity) {
		this.id = entity.getId();
		this.idPersona = entity.getPersona().getId();
		this.username = entity.getUsername();
		this.idPerfil = entity.getPerfil().getId().toString();
	}

}
