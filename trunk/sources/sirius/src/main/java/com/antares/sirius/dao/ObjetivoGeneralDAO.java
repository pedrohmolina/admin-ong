package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.ObjetivoGeneral;

/**
 * DAO para administrar la persistencia de la entidad ObjetivoGeneral
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ObjetivoGeneralDAO extends BusinessEntityDAO<ObjetivoGeneral> {

	/**
	 * Devuelve un objetivo especifico a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	ObjetivoGeneral findByNombre(String nombre);

}
