package com.antares.sirius.service;

import com.antares.commons.service.BaseService;
import com.antares.sirius.model.TipoIngreso;

/**
 * Servicio que contiene la lógica de negocio de la entidad TipoIngreso.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface TipoIngresoService extends BaseService<TipoIngreso> {

	/**
	 * Veirifica que el id pasado por parametro corresponda al Tipo Ingreso Financiacion 
	 * @param id
	 * @return
	 */
	boolean isIdFinanciacion(String id);

}
