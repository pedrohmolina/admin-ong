package com.antares.commons.dao;

import java.io.Serializable;
import java.util.Collection;

/**
 * DAO genérico para administrar las distintas entidades del sistema.
 * 
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 * @param <T> clase de la entidad de modelo
 */
public interface GenericDAO<T> {
 
	/**
	 * Devuelve un objeto de tipo T a partir de un Id especifico
	 * @return T object
	 */
	T findById(Serializable id);
	
	/**
	 * Devuelve todos los objetos de tipo T del sistema
	 * @return Collection<T>
	 */
	Collection<T> findAll();
	
	/**
	 * Registrar una nueva entidad.
	 * @param entity objecto con los datos de la entidad a registrar
	 */
	void save(T entity);
	
	/**
	 * Actualizar la entidad.
	 * @param entity objeto con los datos de la entidad a ser actualizados
	 */
	void update(T entity);
	
	/**
	 * Elimina una entidad.
	 * @param entity entidad a eliminar
	 */
	void delete(T entity);
	
}

