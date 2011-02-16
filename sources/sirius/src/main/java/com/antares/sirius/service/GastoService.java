package com.antares.sirius.service;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Gasto;

/**
 * Servicio que contiene la lógica de negocio de la entidad Gasto.
 * 
 * @version 1.0.0 Created 16/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface GastoService extends BusinessEntityService<Gasto> {

	/**
	 * Confirma el gasto
	 * 
	 * @param entity gasto a confirmar
	 */
	void ejecutarConfirmacion(Gasto entity);

}
