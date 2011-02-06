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
	 * Devuelve un rubro a partir de su nombre (valor único)
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
	 * Devuelve todos los rubros que tienen como rubro padre al rubro del id pasado por parámetro
	 *  
	 * @param id id del rubro padre
	 * @return
	 */
	Collection<Rubro> findHijos(Integer id);

}
