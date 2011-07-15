package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Rubro;

/**
 * Servicio que contiene la l�gica de negocio de la entidad Rubro.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface RubroService extends BusinessEntityService<Rubro> {

	/**
	 * Devuelve los rubros a partir de sus ids. Si el array de ids es null, devuelve todos los rubros.
	 * 
	 * @param ids
	 * @return
	 */
	Collection<Rubro> findByIds(Integer[] ids);

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Devuelve todos los rubros de primer nivel
	 * 
	 * @return
	 */
	Collection<Rubro> findPrimerNivel();

	/**
	 * Devuelve todos los rubros que tienen como rubro padre al rubro del id pasado por par�metro
	 *  
	 * @param id id del rubro padre
	 * @return
	 */
	Collection<Rubro> findHijos(Integer id);
}
