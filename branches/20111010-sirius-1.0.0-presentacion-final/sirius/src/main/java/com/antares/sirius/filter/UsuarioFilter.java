package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Perfil;
import com.antares.sirius.model.Usuario;

/**
 * Filter para la endidad Persona.
 *
 * @version 1.0.0 Created 14/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class UsuarioFilter extends Filter<Usuario> {

	private String username;
	private Perfil perfil;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
