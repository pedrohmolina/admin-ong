package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Proyecto;

/**
 * DAO para administrar la persistencia de la entidad Proyecto
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ProyectoDAO extends BusinessEntityDAO<Proyecto> {

	/**
	 * Devuelve un proyecto a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Proyecto findByNombre(String nombre);

}
