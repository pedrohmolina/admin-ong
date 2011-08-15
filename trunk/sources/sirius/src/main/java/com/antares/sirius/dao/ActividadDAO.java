package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.EstadoActividad;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
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
	 * Devuelve todas las actividades de los proyectos que no se encuentren en los estados dados
	 * 
	 * @param estadoProyecto
	 * @return
	 */
	Collection<Actividad> findAllExceptEstados(EstadoProyecto ... estadoProyecto);

	/**
	 * Devuelve todas las actividades pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Actividad> findAllByProyecto(Proyecto proyecto);

	/**
	 * Devuelve la cantidad de actividades pertenecientes al proyecto y que se encuentran en los estados indicados
	 * 
	 * @param proyecto proyecto de las actividades deseadas
	 * @param estadoActividad estado de las actividades deseadas
	 * @return
	 */
	Integer countByProyectoEstados(Proyecto proyecto, EstadoActividad ... estadoActividad);

	/**
	 * Devuelve todas las actividades pertenecientes al proyecto y que se encuentran en los estados indicados
	 * 
	 * @param proyecto proyecto de las actividades deseadas
	 * @param estadoActividad estado de las actividades deseadas
	 * @return
	 */
	Collection<Actividad> findAllByProyectoEstados(Proyecto proyecto, EstadoActividad ... estadoActividad);
	
	/**
	 * Devuelve todas las actividades pertenecientes al proyecto excepto las que se encuentran en los estados indicados
	 * 
	 * @param proyecto proyecto de las actividades deseadas
	 * @param estadoActividad estado de las actividades no deseadas 
	 * @return
	 */
	Collection<Actividad> findAllByProyectoExceptEstados(Proyecto proyecto, EstadoActividad ... estadoActividad);

	/**
	 * Devuelve todas las actividades pertenecientes al objetivo general
	 * 
	 * @param objetivoGeneral objetivo general
	 * @return
	 */
	Collection<Actividad> findAllByObjetivoGeneral(ObjetivoGeneral objetivoGeneral);

	/**
	 * Devuelve todas las actividades pertenecientes al objetivo especifico
	 * 
	 * @param objetivoEspecifico objetivo especifico
	 * @return
	 */
	Collection<Actividad> findAllByObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico);

	/**
	 * Devuelve todas las actividades pertenecientes a la meta
	 * 
	 * @param meta meta
	 * @return
	 */
	Collection<Actividad> findAllByMeta(Meta meta);

}
