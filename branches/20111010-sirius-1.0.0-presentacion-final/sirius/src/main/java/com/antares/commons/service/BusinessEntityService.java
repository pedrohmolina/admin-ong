package com.antares.commons.service;

import java.util.Collection;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.BusinessObject;

/**
 * Servicio genérico para administrar las distintas entidades del sistema.
 * 
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 * @param <T> clase de la entidad de modelo
 */
public interface BusinessEntityService<T extends BusinessObject> extends BaseService<T> {
 
	/**
	 * Devuelve todos los objetos de tipo T del sistema filtrados con el objeto filter
	 * @param filter objecto con los datos para filtrar la busqueda
	 * @return Collection<T> object
	 */
	Collection<T> findByFilter(Filter<T> filter);
	
}
