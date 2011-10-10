package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Financiador;

/**
 * DAO para administrar la persistencia de la entidad Financiador
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface FinanciadorDAO extends BusinessEntityDAO<Financiador> {

	/**
	 * Devuelve un financiador a partir de su nombre (valor unico)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Financiador findByNombre(String nombre);

}
