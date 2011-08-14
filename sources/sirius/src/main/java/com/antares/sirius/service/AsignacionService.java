package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAsignacion;

/**
 * Servicio que contiene la lógica de negocio de la entidad Asignacion.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface AsignacionService extends BusinessEntityService<Asignacion> {

	/**
	 * Se fija si ya existe en el sistema una asignacion para para la misma combinacion de actividad, persona y tipo de asignacion 
	 * 
	 * @param id
	 * @param actividad
	 * @param persona
	 * @param tipoAsignacion
	 * @return
	 */
	boolean isAsignacionRepetida(Integer id, Actividad actividad, Persona persona, TipoAsignacion tipoAsignacion);

	/**
	 * Devuelve los proyectos que contienen actividades asignadas a la persona que no se encuentren finalizados
	 * 
	 * @param persona persona
	 * @return
	 */
	Collection<Proyecto> findProyectosAsignados(Persona persona);

	/**
	 * Devuelve las actividades asignadas a la persona.
	 * 
	 * @param persona persona
	 * @return
	 */
	Collection<Actividad> findActividadesAsignadas(Persona persona);

	/**
	 * Devuelve las actividades asignadas a la persona para un proyecto puntual
	 * 
	 * @param persona persona
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Actividad> findActividadesAsignadas(Persona persona, Proyecto proyecto);

}
