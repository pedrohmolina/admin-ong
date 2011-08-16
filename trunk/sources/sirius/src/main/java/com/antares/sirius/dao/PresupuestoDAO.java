package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.GenericDAO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

public interface PresupuestoDAO extends GenericDAO<Presupuesto> {

	/**
	 * Devuelve todos los presupuestos por rubro (de primer nivel) para los gastos de las distintas actividades
	 * 
	 * @param proyecto
	 * @return
	 */
	Collection<Presupuesto> findPresupuestoActividadByProyecto(Proyecto proyecto);

	/**
	 * Devuelve todos los presupuestos por rubro (de primer nivel) para los gastos generales del proyecto
	 * 
	 * @param proyecto
	 * @return
	 */
	Collection<Presupuesto> findPresupuestoByProyecto(Proyecto proyecto);

	/**
	 * Devuelve los presupuestos correspondientes a actividades de un proyecto para los rubros elegidos
	 * 
	 * @param proyecto proyecto
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
	 * Devuelve el presupuesto para gastos de proyecto
	 * 
	 * @param proyecto proyecto
	 * @param rubro rubro (opcional)
	 * @return
	 */
	Presupuesto findPresupuestoProyecto(Proyecto proyecto, Rubro rubro);

	/**
	 * Devuelve el presupuesto para gastos de actividad
	 * 
	 * @param actividad actividad
	 * @param rubro rubro (opcional)
	 * @return
	 */
	Presupuesto findPresupuestoActividad(Actividad actividad, Rubro rubro);

}
