package com.antares.sirius.service;

import com.antares.commons.service.BaseService;
import com.antares.sirius.model.TipoPresupuesto;

/**
 * Servicio que contiene la lógica de negocio de la entidad TipoPresupuesto.
 * 
 * @version 1.0.0 Created 20/06/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface TipoPresupuestoService extends BaseService<TipoPresupuesto> {

	/**
	 * Devuelve el tipo de presupuesto correspondiente a actividades
	 * 
	 * @return
	 */
	TipoPresupuesto findTipoPresupuestoActividad();

	/**
	 * Devuelve el tipo de presupuesto correspondiente a proyectos
	 * 
	 * @return
	 */
	TipoPresupuesto findTipoPresupuestoProyecto();

}
