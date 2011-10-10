package com.antares.sirius.dao;

import com.antares.commons.dao.GenericDAO;
import com.antares.sirius.model.Archivo;

public interface ArchivoDAO extends GenericDAO<Archivo> {

	/**
	 * Retorna un archivo a partir de un hash
	 * 
	 * @return Archivo correspondiente al hash
	 */
	Archivo findByHash(String hash);

}
