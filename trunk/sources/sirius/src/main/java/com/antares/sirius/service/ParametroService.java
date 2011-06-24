package com.antares.sirius.service;

import com.antares.commons.enums.ParametroEnum;
import com.antares.commons.service.BaseService;
import com.antares.sirius.model.Parametro;

/**
 * Servicio que contiene la lógica de negocio de la entidad Parametro.
 * 
 * @version 1.0.0 Created 13/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ParametroService extends BaseService<Parametro> {

	/**
	 * Devuelve el valor del parametro a partir de su correspondiente enum
	 * 
	 * @param parametroEnum
	 * @return
	 */
	String findValueByEnum(ParametroEnum parametroEnum);

	/**
	 * Devuelve el id del tipo de ingreso correspondiente a "Financiacion"
	 * 
	 * @return
	 */
	String findIdTipoIngresoFinanciacion();

	/**
	 * Devuelve el id del estado de proyecto correspondiente a "Idea"
	 * 
	 * @return
	 */
	Integer findIdEstadoProyectoIdea();

	/**
	 * Devuelve el id del estado de actividad correspondiente a "Latente"
	 * 
	 * @return
	 */
	Integer findIdEstadoActividadLatente();

	/**
	 * Devuelve el id del estado de actividad correspondiente a "En Progreso"
	 * 
	 * @return
	 */
	Integer findIdEstadoActividadProgreso();

	/**
	 * Devuelve el id del tipo de gasto correspondiente a gastos de actividades
	 * 
	 * @return
	 */
	Integer findIdTipoGastoActividad();

	/**
	 * Devuelve el id del tipo de gasto correspondiente a gastos de organizacion
	 * 
	 * @return
	 */
	Integer findIdTipoGastoOrganizacion();

	/**
	 * Devuelve el id del tipo de gasto correspondiente a gastos de proyectos
	 * 
	 * @return
	 */
	Integer findIdTipoGastoProyecto();

	/**
	 * Devuelve el id del tipo de agrupamiento de gastos correspondiente a gastos indviduales
	 * 
	 * @return
	 */
	Integer findIdTipoAgrupamientoIndividual();

	/**
	 * Devuelve el id del tipo de agrupamiento de gastos correspondiente a gastos agrupados
	 * 
	 * @return
	 */
	Integer findIdTipoAgrupamientoAgrupado();

	/**
	 * Devuelve el id del tipo de presupuesto correspondiente a gastos de actividades
	 * 
	 * @return
	 */
	Integer findIdTipoPresupuestoActividad();

	/**
	 * Devuelve el id del tipo de presupuesto correspondiente a gastos de proyectos
	 * 
	 * @return
	 */
	Integer findIdTipoPresupuestoProyecto();

}
