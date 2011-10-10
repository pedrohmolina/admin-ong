package com.antares.commons.service.impl;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.commons.filter.Filter;
import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.BusinessObject;


/**
 * Implementacion genérica de la interfaz BaseService.
 *
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 * @param <D> clase DAO para manejar la persistencia de la entidad
 */
public class BusinessEntityServiceImpl<T extends BusinessObject, D extends BusinessEntityDAO<T>> extends BaseServiceImpl<T, D> implements BusinessEntityService<T> {

	/**
	 * @see com.antares.commons.util.service.BaseService#findByFilter(Filter<T>)
	 * {@inheritDoc}
	 */
	public Collection<T> findByFilter(Filter<T> filter) {
		return dao.findByFilter(filter);
	}

}
