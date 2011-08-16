package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

public interface GastoDAO extends BusinessEntityDAO<Gasto> {

	/**
	 * Devuelve todos los gasto del proyecto, tanto a nivel actividad como a nivel proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Gasto> findAllByProyecto(Proyecto proyecto);

	/**
	 * Determina si existen gastos para un proyecto dado, tanto a nivel actividad como a nivel proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	boolean existenGastosProyecto(Proyecto proyecto);

	/**
	 * Devuelve todos los gastos de las actividades de un objetivo general
	 * 
	 * @param objetivoGeneral objetivo general
	 * @return
	 */
	Collection<Gasto> findAllByObjetivoGeneral(ObjetivoGeneral objetivoGeneral);

	/**
	 * Determina si existen gastos para las actividades de un objetivo general
	 * 
	 * @param objetivoGeneral objetivo general
	 * @return
	 */
	boolean existenGastosObjetivoGeneral(ObjetivoGeneral objetivoGeneral);

	/**
	 * Devuelve todos los gastos de las actividades de un objetivo especifico
	 * 
	 * @param objetivoEspecifico objetivo especifico
	 * @return
	 */
	Collection<Gasto> findAllByObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico);

	/**
	 * Determina si existen gastos para las actividades de un objetivo especifico
	 * 
	 * @param objetivoEspecifico objetivo especifico
	 * @return
	 */
	boolean existenGastosObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico);

	/**
	 * Devuelve todos los gastos de las actividades de una meta
	 * 
	 * @param meta meta
	 * @return
	 */
	Collection<Gasto> findAllByMeta(Meta meta);

	/**
	 * Determina si existen gastos para las actividades de una meta
	 * 
	 * @param meta meta
	 * @return
	 */
	boolean existenGastosMeta(Meta meta);

	/**
	 * Devuelve todos los gastos de la actividad
	 * 
	 * @param actividad actividad
	 * @return
	 */
	Collection<Gasto> findAllByActividad(Actividad actividad);

	/**
	 * Determina si existen gastos para la actividad
	 * 
	 * @param actividad actividad
	 * @return
	 */
	boolean existenGastosActividad(Actividad actividad);

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

	/**
	 * Devuelve el monto total gastado en gastos de proyecto
	 * 
	 * @param proyecto proyecto
	 * @param rubro rubro de primer nivel (opcional)
	 * @return
	 */
	Double gastosProyecto(Proyecto proyecto, Rubro rubro);

	/**
	 * Devuelve el monto total gastado en gastos de proyecto
	 * 
	 * @param proyecto proyecto
	 * @param rubro rubro de primer nivel (opcional)
	 * @return
	 */
	Double gastosActividad(Actividad actividad, Rubro rubro);
}
