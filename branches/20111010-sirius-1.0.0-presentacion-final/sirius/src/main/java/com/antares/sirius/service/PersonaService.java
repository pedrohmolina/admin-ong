package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Persona;

/**
 * Servicio que contiene la lógica de negocio de la entidad Persona.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface PersonaService extends BusinessEntityService<Persona> {

	/**
	 * Devuelve todas las personas del sistema excepto la que tiene un id igual al parametro. Si el parametro es null, se comporta como findAll
	 * 
	 * @param id id de Persona
	 * @return
	 */
	Collection<Persona> findAllOthers(Integer id);

	/**
	 * Valida que el nombre y apellido no esten repetidos en otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param apellido apellido a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreApellidoRepetido(String nombre, String apellido, Integer id);

	/**
	 * Devuelve la persona actualmente logueada en el sistema.
	 * 
	 * @return persona actualmente logueada en el sistema.
	 */
	Persona findPersonaLogueada();
}
