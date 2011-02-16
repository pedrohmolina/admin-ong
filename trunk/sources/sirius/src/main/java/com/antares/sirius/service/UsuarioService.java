package com.antares.sirius.service;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Usuario;

/**
 * Servicio que contiene la lógica de negocio de la entidad Usuario.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface UsuarioService extends BusinessEntityService<Usuario> {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Bloquea al usuario en el sistema, impidiendo que se pueda loguear
	 * 
	 * @param entity usuario a bloquear
	 */
	void ejecutarBloqueo(Usuario entity);
	
	/**
	 * Desbloquea al usuario en el sistema, permitiendo que se pueda loguear
	 * 
	 * @param entity usuario a desbloquear
	 */
	void ejecutarDesbloqueo(Usuario entity);

}
