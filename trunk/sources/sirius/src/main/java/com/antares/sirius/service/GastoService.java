package com.antares.sirius.service;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Gasto;

/**
 * Servicio que contiene la l�gica de negocio de la entidad Gasto.
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

	/**
	 * Determina si el gasto soporta carga individual o agrupada. Para gastos de administracion, devuelve true
	 * 
	 * @param gasto
	 * @return
	 */
	boolean isIndividual(Gasto gasto);
	
	/**
	 * Determina si el gasto soporta carga individual o agrupada. Para gastos de administracion, devuelve false
	 * 
	 * @param gasto
	 * @return
	 */
	boolean isAgrupado(Gasto gasto);

}
