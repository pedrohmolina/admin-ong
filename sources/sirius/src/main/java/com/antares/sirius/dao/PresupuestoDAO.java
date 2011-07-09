package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.GenericDAO;
import com.antares.sirius.model.Actividad;
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
	 * Devuelve el presupuesto correspondiente a una actividad y un rubro especifico
	 * @param actividad
	 * @param rubro
	 * @return presupuesto
	 */
	Presupuesto findPresupuestoByActividadRubro(Actividad actividad, Rubro rubro);

}
