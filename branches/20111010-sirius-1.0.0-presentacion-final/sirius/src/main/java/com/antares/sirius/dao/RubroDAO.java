package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Rubro;

/**
 * DAO para administrar la persistencia de la entidad Rubro
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface RubroDAO extends BusinessEntityDAO<Rubro> {

	/**
	 * Devuelve los rubros a partir de sus ids
	 * 
	 * @param ids array de enteros con los ids
	 * @return
	 */
	Collection<Rubro> findByIds(Integer[] ids);

	/**
	 * Devuelve un rubro a partir de su nombre (valor �nico)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Rubro findByNombre(String nombre);

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
