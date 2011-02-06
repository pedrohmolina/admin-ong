package com.antares.sirius.service;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Rol;

/**
 * Servicio que contiene la lógica de negocio de la entidad Rol.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface RolService extends BusinessEntityService<Rol> {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

}
