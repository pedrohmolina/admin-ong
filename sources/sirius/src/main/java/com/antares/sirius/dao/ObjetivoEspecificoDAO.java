package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.Proyecto;

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

	/**
	 * Devuelve todos los objetivos especificos pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<ObjetivoEspecifico> findAllByProyecto(Proyecto proyecto);

	/**
	 * Devuelve todos los objetivos especificos de los proyectos que no se encuentren en los estados dados
	 * 
	 * @param estadoProyecto
	 * @return
	 */
	Collection<ObjetivoEspecifico> findAllExceptEstados(EstadoProyecto ... estadoProyecto);

}
