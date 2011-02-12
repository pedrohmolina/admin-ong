package com.antares.sirius.service;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
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

}
