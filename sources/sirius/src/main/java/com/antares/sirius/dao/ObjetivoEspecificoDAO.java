package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.ObjetivoEspecifico;

/**
 * DAO para administrar la persistencia de la entidad ObjetivoEspecifico
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ObjetivoEspecificoDAO extends BusinessEntityDAO<ObjetivoEspecifico> {

	/**
	 * Devuelve un objetivo específico a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	ObjetivoEspecifico findByNombre(String nombre);

}
