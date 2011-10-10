package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Proyecto;

/**
 * DAO para administrar la persistencia de la entidad Proyecto
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ProyectoDAO extends BusinessEntityDAO<Proyecto> {

	/**
	 * Devuelve todos los proyectos que se encuentran en el estado indicado
	 * 
	 * @param estadoProyecto estado del proyecto
	 * @return
	 */
	Collection<Proyecto> findAllByEstado(EstadoProyecto estadoProyecto);

	/**
	 * Devuelve todos los proyectos excepto los que se encuentran en el estado indicado
	 * 
	 * @param estadoProyecto estado del proyecto
	 * @return
	 */
	Collection<Proyecto> findAllExceptEstados(EstadoProyecto ... estadoProyecto);

	/**
	 * Devuelve un proyecto a partir de su nombre (valor único)
	 * 
	 * @param nombre nombre a buscar
	 * @return
	 */
	Proyecto findByNombre(String nombre);

}
