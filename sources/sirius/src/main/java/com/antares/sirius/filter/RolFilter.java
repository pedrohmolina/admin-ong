package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Rol;

/**
 * Filter para la endidad Rol.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class RolFilter extends Filter<Rol> {

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
