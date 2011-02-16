package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Proyecto;

public interface ActividadDAO extends BusinessEntityDAO<Actividad> {

	/**
	 * Devuelve una actividad a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Actividad findByNombre(String nombre);

	/**
	 * Devuelve todas las actividades pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Actividad> findAllByProyecto(Proyecto proyecto);

}
