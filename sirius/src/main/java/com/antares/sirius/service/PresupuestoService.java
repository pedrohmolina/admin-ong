package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BaseService;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.dto.PresupuestoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

/**
 * Servicio que contiene la lógica de negocio de la entidad Presupuesto.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface PresupuestoService extends BaseService<Presupuesto> {

	/**
	 * Devuelve un DTO con todos los presupuestos de un proyecto
	 * 
	 * @param proyecto proyecto a partir del cual se quieren obtener los presupuestos
	 * @return DTO con todos los presupuestos
	 */
	PresupuestoDTO findAllByProyecto(Proyecto proyecto);

	/**
	 * Persiste los presupuestos que recibe por parametro en un DTO. 
	 * Los presupuestos del mismo proyecto que no se encuentran en el DTO, se eliminan. 
	 * 
	 * @param presupuestos DTO con los presupuestos a persistir
	 */
	void saveAll(PresupuestoDTO presupuestos);

	/**
	 * Agrega un nuevo presupuesto de tipo actividad al DTO
	 * 
	 * @param presupuestos DTO de presupuestos
	 * @param actividad actividad del nuevo presupuesto
	 * @param rubro rubro del nuevo presupuesto
	 * @param monto monto del nuevo presupuesto
	 */
	void addPresupuestoActividad(PresupuestoDTO presupuestos, Actividad actividad, Rubro rubro, Double monto);

	/**
	 * Agrega un nuevo presupuesto de tipo proyecto al DTO
	 * 
	 * @param presupuestos DTO de presupuestos
	 * @param rubro rubro del nuevo presupuesto
	 * @param monto monto del nuevo presupuesto
	 */
	void addPresupuestoProyecto(PresupuestoDTO presupuestos, Rubro rubro, Double monto);

	/**
	 * Devuelve los presupuestos correspondientes a actividades de un proyecto para los rubros elegidos
	 * 
	 * @param proyecto
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByProyectoAndRubro(Proyecto proyecto, Rubro[] rubros);

	/**
	 * Devuelve los presupuestos correspondientes a actividades de un objetivo general para los rubros elegidos
	 * 
	 * @param objetivoGeneral objetivo general
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByObjetivoGeneralAndRubro(ObjetivoGeneral objetivoGeneral, Rubro[] rubros);

	/**
	 * Devuelve los presupuestos correspondientes a actividades de un objetivo especifico para los rubros elegidos
	 * 
	 * @param objetivoEspecifico objetivo especifico
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByObjetivoEspecificoAndRubro(ObjetivoEspecifico objetivoEspecifico, Rubro[] rubros);

	/**
	 * Devuelve los presupuestos correspondientes a actividades de una meta para los rubros elegidos
	 * 
	 * @param meta meta
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByMetaAndRubro(Meta meta, Rubro[] rubros);

	/**
	 * Devuelve los presupuestos correspondientes a una actividad para los rubros elegidos
	 * 
	 * @param actividad actividad
	 * @param rubros ids de los rubros
	 * @return
	 */
	Collection<MontoDTO> obtainMontosByActividadAndRubro(Actividad actividad, Rubro[] rubros);

	/**
	 * Devuelve el monto presupuestado para gastos de proyecto
	 * 
	 * @param proyecto proyecto
	 * @param rubro rubro del presupuesto (opcional)
	 * @return
	 */
	Double presupuestoProyecto(Proyecto proyecto, Rubro rubro);

	/**
	 * Devuelve el monto presupuestado para gastos de la actividad
	 * 
	 * @param actividad actividad
	 * @param rubro rubro del presupuesto (opcional)
	 * @return
	 */
	Double presupuestoActividad(Actividad actividad, Rubro rubro);

}
