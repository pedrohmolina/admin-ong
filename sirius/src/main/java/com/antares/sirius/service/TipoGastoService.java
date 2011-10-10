package com.antares.sirius.service;

import com.antares.commons.service.BaseService;
import com.antares.sirius.model.TipoGasto;

/**
 * Servicio que contiene la lógica de negocio de la entidad TipoGasto.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface TipoGastoService extends BaseService<TipoGasto> {

	/**
	 * Devuelve el tipo de gasto correspondiente a actividades
	 * 
	 * @return
	 */
	TipoGasto findTipoGastoActividad();

	/**
	 * Devuelve el tipo de gasto correspondiente a la organizacion
	 * 
	 * @return
	 */
	TipoGasto findTipoGastoOrganizacion();

	/**
	 * Devuelve el tipo de gasto correspondiente a proyectos
	 * 
	 * @return
	 */
	TipoGasto findTipoGastoProyecto();

}
