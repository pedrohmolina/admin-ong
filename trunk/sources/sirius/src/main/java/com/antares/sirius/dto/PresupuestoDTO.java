package com.antares.sirius.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;


/**
 * Representacion de los presupuestos del proyecto. 
 * 
 * @version 1.0.0 Created 18/06/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
@SuppressWarnings("serial")
public class PresupuestoDTO implements Serializable {

	private Proyecto proyecto;

	// Mapas indexados por id de actividad y rubro
	private Map<Integer, Map<Integer, Presupuesto>> presupuestosPorActividad = new HashMap<Integer, Map<Integer,Presupuesto>>();
	private Map<Integer, Presupuesto> presupuestosPorProyecto = new HashMap<Integer,Presupuesto>();

	// Collecciones de presupuestos
	private Collection<Presupuesto> presupuestosActividad = new ArrayList<Presupuesto>();
	private Collection<Presupuesto> presupuestosProyecto = new ArrayList<Presupuesto>();

	public PresupuestoDTO(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	/**
	 * Agrega un presupuesto de tipo actividad.

	 * @param presupuesto a agregar
	 * @return
	 */
	public Presupuesto putPresupuestoActividad(Presupuesto presupuesto) {
		Map<Integer, Presupuesto> mapaPorRubro = presupuestosPorActividad.get(presupuesto.getActividad().getId());
		if (mapaPorRubro == null) {
			mapaPorRubro = new HashMap<Integer, Presupuesto>(); 
			presupuestosPorActividad.put(presupuesto.getActividad().getId(), mapaPorRubro);
		}
		mapaPorRubro.put(presupuesto.getRubro().getId(), presupuesto);
		presupuestosActividad.add(presupuesto);
		return presupuestosPorActividad.get(presupuesto.getActividad().getId()).get(presupuesto.getRubro().getId());
	}

	/**
	 * Obtiene el presupuesto de tipo actividad.
	 * 
	 * @param actividad actividad del presupuesto
	 * @param rubro rubro del presupuesto
	 * @return
	 */
	public Presupuesto get(Actividad actividad, Rubro rubro) {
		Presupuesto presupuesto = null;
		Map<Integer, Presupuesto> mapaPorRubro = presupuestosPorActividad.get(actividad.getId());
		if (mapaPorRubro != null) {
			presupuesto = mapaPorRubro.get(rubro.getId()); 
		}
		return presupuesto;
	}

	/**
	 * Elimina el presupuesto de tipo actividad correspondiente a la actividad y rubro pasado por parametro.
	 * 
	 * @param actividad actividad del presupuesto a eliminar
	 * @param rubro rubro del presupuesto a eliminar
	 * @return
	 */
	public Presupuesto remove(Actividad actividad, Rubro rubro) {
		Presupuesto presupuesto = null;
		Map<Integer, Presupuesto> mapaPorRubro = presupuestosPorActividad.get(actividad.getId());
		if (mapaPorRubro != null) {
			presupuesto = mapaPorRubro.remove(rubro.getId());
			presupuestosActividad.remove(presupuesto);
			if (mapaPorRubro.size() == 0) {
				presupuestosPorActividad.remove(actividad.getId());
			}
		}
		return presupuesto;
	}

	/**
	 * Agrega un presupuesto de tipo proyecto.
	 * 
	 * @param presupuesto a agregar
	 * @return
	 */
	public Presupuesto putPresupuestoProyecto(Presupuesto presupuesto) {
		presupuestosPorProyecto.put(presupuesto.getRubro().getId(), presupuesto);
		presupuestosProyecto.add(presupuesto);
		return presupuestosPorProyecto.get(presupuesto.getRubro().getId());
	}

	/**
	 * Obtiene el presupuesto de tipo proyecto.
	 * 
	 * @param rubro rubro del presupuesto
	 * @return
	 */
	public Presupuesto get(Rubro rubro) {
		return presupuestosPorProyecto.get(rubro.getId());
	}

	/**
	 * Elimina el presupuesto de tipo proyecto correspondiente al rubro pasado por parametro.
	 * 
	 * @param rubro rubro del presupuesto a eliminar
	 * @return
	 */
	public Presupuesto remove(Rubro rubro) {
		Presupuesto presupuesto = presupuestosPorProyecto.remove(rubro.getId());
		presupuestosProyecto.remove(presupuesto);
		return presupuesto;
	}

	/**
	 * Obtiene todos los presupuestos de actividad
	 * 
	 * @return coleccion con todos los presupuestos de tipo actividad
	 */
	public Collection<Presupuesto> getPresupuestosActividad() {
		return presupuestosActividad;
	}

	/**
	 * Obtiene todos los presupuestos de proyecto
	 * 
	 * @return coleccion con todos los presupuestos de tipo proyecto
	 */
	public Collection<Presupuesto> getPresupuestosProyecto() {
		return presupuestosProyecto;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

}
