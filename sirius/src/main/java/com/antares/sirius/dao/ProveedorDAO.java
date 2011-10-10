package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Proveedor;

/**
 * DAO para administrar la persistencia de la entidad Proveedor
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ProveedorDAO extends BusinessEntityDAO<Proveedor> {

	/**
	 * Devuelve un proveedor a partir de su nombre (valor unico)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Proveedor findByNombre(String nombre);

}
