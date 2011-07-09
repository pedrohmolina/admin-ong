package com.antares.sirius.service;

import com.antares.commons.service.BaseService;
import com.antares.sirius.dto.PresupuestoDTO;
import com.antares.sirius.model.Actividad;
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
	 * Devuelve el presupuesto correspondiente a una actividad y un rubro especifico
	 * @param actividad
	 * @param rubro
	 * @return presupuesto
	 */
	Presupuesto findPresupuestoByActividadRubro(Actividad actividad, Rubro rubro);
	
	

}
