package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.Proyecto;

/**
 * Servicio que contiene la lógica de negocio de la entidad Meta.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface MetaService extends BusinessEntityService<Meta> {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Devuelve todas las metas pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Meta> findAllByProyecto(Proyecto proyecto);

}
