package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

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

	/**
	 * Devuelve los gastos correspondientes a actividades de un proyecto para los rubros elegidos
	 * 
	 * @param proyecto proyecto
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByProyectoAndRubro(Proyecto proyecto, Rubro[] rubros);

	/**
	 * Devuelve los gastos correspondientes a actividades de un objetivo general para los rubros elegidos
	 * 
	 * @param objetivoGeneral objetivo general
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByObjetivoGeneralAndRubro(ObjetivoGeneral objetivoGeneral, Rubro[] rubros);

	/**
	 * Devuelve los gastos correspondientes a actividades de un objetivo especifico para los rubros elegidos
	 * 
	 * @param objetivoEspecifico objetivo especifico
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByObjetivoEspecificoAndRubro(ObjetivoEspecifico objetivoEspecifico, Rubro[] rubros);

	/**
	 * Devuelve los gastos correspondientes a actividades de una meta para los rubros elegidos
	 * 
	 * @param meta meta
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByMetaAndRubro(Meta meta, Rubro[] rubros);

	/**
	 * Devuelve los gastos correspondientes a una actividad para los rubros elegidos
	 * 
	 * @param actividad actividad
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByActividadAndRubro(Actividad actividad, Rubro[] rubros);

}
