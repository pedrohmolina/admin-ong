package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.RelacionContractual;

/**
 * DAO para administrar la persistencia de la entidad RelacionContractual
 * 
 * @version 1.0.0 Created 22/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface RelacionContractualDAO extends BusinessEntityDAO<RelacionContractual> {

	/**
	 * Devuelve una relacion contractual a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	RelacionContractual findByNombre(String nombre);

}
