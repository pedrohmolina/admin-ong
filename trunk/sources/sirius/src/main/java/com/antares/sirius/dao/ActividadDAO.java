package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Actividad;

public interface ActividadDAO extends BusinessEntityDAO<Actividad> {

	/**
	 * Devuelve una actividad a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Actividad findByNombre(String nombre);

}
