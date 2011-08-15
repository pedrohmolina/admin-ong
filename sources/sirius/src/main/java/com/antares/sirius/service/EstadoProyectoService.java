package com.antares.sirius.service;

import com.antares.commons.service.BaseService;
import com.antares.sirius.model.EstadoProyecto;

/**
 * Servicio que contiene la lógica de negocio de la entidad EstadoProyecto.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface EstadoProyectoService extends BaseService<EstadoProyecto> {

	/**
	 * Devuelve el estado por defecto del proyecto
	 * 
	 * @return
	 */
	EstadoProyecto findDefault();

	/**
	 * Evalua si el estado cuyo id recibe por parametro es el estado de cierre
	 * 
	 * @param idEstado id del estado que se quiere evaluar
	 * @return
	 */
	boolean isCierre(Integer idEstado);

}
