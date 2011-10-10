package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.Regla;

public interface ReglaDAO extends BusinessEntityDAO<Regla> {

	/**
	 * Trae todas las reglas creadas sobre la entidad para el usuario
	 * 
	 * @param username usuario al que aplican las reglas
	 * @param entidad entidad sobre la que estan creadas las reglas
	 * @return
	 */
	Collection<Regla> findByUsernameAndEntidad(String username, Entidad entidad);

}
