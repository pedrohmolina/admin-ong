package com.antares.sirius.view.form;

import com.antares.commons.view.form.AbstractForm;
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
public class UsuarioPasswordForm extends AbstractForm<Usuario> {

	private String passwordActual;
	private String password;
	private String password2;
	private boolean usuarioPropio;

	public String getPasswordActual() {
		return passwordActual;
	}

	public void setPasswordActual(String passwordActual) {
		this.passwordActual = passwordActual;
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

	@Override
	public void initialize() {
		this.id = null;
		this.passwordActual = "";
		this.password = "";
		this.password2 = "";
		this.usuarioPropio = true;
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.passwordActual = "";
		this.password = "";
		this.password2 = "";
		this.usuarioPropio = true;
	}

	@Override
	public void initializeForm(Usuario entity) {
		this.id = entity.getId();
	}

	public boolean isUsuarioPropio() {
		return usuarioPropio;
	}

	public void setUsuarioPropio(boolean usuarioPropio) {
		this.usuarioPropio = usuarioPropio;
	}

}
