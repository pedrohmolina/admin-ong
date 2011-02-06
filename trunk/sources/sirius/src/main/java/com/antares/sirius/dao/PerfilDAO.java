package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Perfil;

/**
 * DAO para administrar la persistencia de la entidad Perfil
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface PerfilDAO extends BusinessEntityDAO<Perfil> {

	/**
	 * Devuelve un perfil a partir de su nombre (valor unico)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Perfil findByNombre(String nombre);

}
