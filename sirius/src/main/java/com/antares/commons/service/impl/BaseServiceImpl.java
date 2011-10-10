package com.antares.commons.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.antares.commons.dao.GenericDAO;
import com.antares.commons.service.BaseService;
import com.antares.sirius.model.PersistentObject;


/**
 * Implementacion genérica de la interfaz BaseService.
 *
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 * @param <D> clase DAO para manejar la persistencia de la entidad
 */
public class BaseServiceImpl<T extends PersistentObject, D extends GenericDAO<T>> implements BaseService<T> {

	protected D dao;

	/**
	 * @see com.antares.commons.service.BaseService#findById(Serializable)
	 * {@inheritDoc}
	 */
	public T findById(Serializable id) {
		return dao.findById(id);
	}

	/**
	 * @see com.antares.commons.service.BaseService#findAll()
	 * {@inheritDoc}
	 */
	public Collection<T> findAll() {
		return dao.findAll();
	}

	/**
	 * @see com.antares.commons.service.BaseService #save(T)
	 * {@inheritDoc}
	 */
	public void save (T entity) {
		dao.save(entity);
	}

	/**
	 * @see com.antares.commons.service.BaseService #update(T)
	 * {@inheritDoc}
	 */
	public void update(T entity) {
		dao.update(entity);
	}

	/**
	 * @see com.antares.commons.service.BaseService #delete(T)
	 * {@inheritDoc}
	 */
	public void delete (T entity) {
		dao.delete(entity);
	}

	public void setDao (D dao) {
		this.dao = dao;
	}

}
