package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Rol;

/**
 * DAO para administrar la persistencia de la entidad Rol
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface RolDAO extends BusinessEntityDAO<Rol> {

	/**
	 * Devuelve una rol a partir de su nombre (valor unico)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Rol findByNombre(String nombre);

}
