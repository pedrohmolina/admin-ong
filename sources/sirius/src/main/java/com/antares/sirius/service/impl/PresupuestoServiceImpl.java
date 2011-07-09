package com.antares.sirius.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.sirius.dao.PresupuestoDAO;
import com.antares.sirius.dto.PresupuestoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.PresupuestoService;
import com.antares.sirius.service.TipoPresupuestoService;

/**
 * Implementacion de la interfaz PresupuestoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class PresupuestoServiceImpl extends BaseServiceImpl<Presupuesto, PresupuestoDAO> implements PresupuestoService {

	private TipoPresupuestoService tipoPresupuestoService;

	@Override
	public PresupuestoDTO findAllByProyecto(Proyecto proyecto) {
		PresupuestoDTO presupuestos = new PresupuestoDTO(proyecto);
		
		// Primero traigo todos los presupuestos por actividad
		Collection<Presupuesto> presupuestosPorActividad = dao.findPresupuestoActividadByProyecto(proyecto);
		for (Presupuesto presupuesto : presupuestosPorActividad) {
			presupuestos.putPresupuestoActividad(presupuesto);
		}
		
		// Despues traigo todos los presupuestos por proyecto
		Collection<Presupuesto> presupuestosPorProyecto = dao.findPresupuestoByProyecto(proyecto);
		for (Presupuesto presupuesto : presupuestosPorProyecto) {
			presupuestos.putPresupuestoProyecto(presupuesto);
		}

		return presupuestos;
	}

	@Override
	public void saveAll(PresupuestoDTO presupuestos) {
		// Guardo los presupuestos ingresados 
		Collection<Presupuesto> presupuestosActividadIngresados = dao.findPresupuestoActividadByProyecto(presupuestos.getProyecto());
		guardarPresupuestos(presupuestos.getPresupuestosActividad());
		guardarPresupuestos(presupuestos.getPresupuestosProyecto());

		// Eliminar los presupuestos que ya no se encuentran en el DTO
		Collection<Presupuesto> presupuestosProyectoIngresados = dao.findPresupuestoByProyecto(presupuestos.getProyecto());
		eliminarPresupuestos(presupuestos.getPresupuestosActividad(), presupuestosActividadIngresados);
		eliminarPresupuestos(presupuestos.getPresupuestosProyecto(), presupuestosProyectoIngresados);
	}

	/**
	 * Persiste la coleccion de presupuestos.
	 * 
	 * @param presupuestosAIngresar coleccion de presupuestos a persistir
	 */
	protected void guardarPresupuestos(Collection<Presupuesto> presupuestosAIngresar) {
		for (Presupuesto presupuesto : presupuestosAIngresar) {
			dao.save(presupuesto);
		}
	}

	/**
	 * Determina que presupuestos deben ser eliminados, comparando las colecciones de elementos nuevos y de elementos 
	 * ya ingresados con anterioridad, y se encarga de su eliminacion.
	 * 
	 * @param presupuestosAIngresar
	 * @param presupuestosIngresados
	 */
	protected void eliminarPresupuestos(Collection<Presupuesto> presupuestosAIngresar, Collection<Presupuesto> presupuestosIngresados) {
		// Primero verifico que haya algun presupuesto ingresado previamente
		if (presupuestosIngresados.size() > 0) {

			// Obtengo ambas collecciones de presupuestos (a ingresar y ya ingresados) y creo mapas indexados por id para cada uno
			Map<Integer, Presupuesto> mapaAIngresar = agregarMapaPresupuestos(new HashMap<Integer, Presupuesto>(), presupuestosAIngresar);
			Map<Integer, Presupuesto> mapaIngresados = agregarMapaPresupuestos(new HashMap<Integer, Presupuesto>(), presupuestosIngresados);

			// Obtengo los conjuntos de keys de cada mapa y al conjunto de keys ingresadas le resto el de no ingresadas.
			// El resultado de esta operacion es el conjuntos de keys correspondientes a los registros que se deben eliminar
			Set<Integer> idsAIngresar = mapaAIngresar.keySet();
			Set<Integer> idsIngresados = mapaIngresados.keySet();
			idsIngresados.removeAll(idsAIngresar);

			for (Integer id : idsIngresados) {
				dao.delete(mapaIngresados.get(id));
			}
		}
	}

	/**
	 * Agrega los presupuestos a un mapa indexados por id.
	 * 
	 * @param mapa para con presupuestos indexados por id
	 * @param presupuestos coleccion de presupuestos a agregar en el mapa
	 * @return mapa actualizado
	 */
	protected Map<Integer, Presupuesto> agregarMapaPresupuestos(Map<Integer, Presupuesto> mapa, Collection<Presupuesto> presupuestos) {
		for (Presupuesto presupuesto : presupuestos) {
			if (presupuesto.getId() != null){
				mapa.put(presupuesto.getId(), presupuesto);
			}
		}
		return mapa;
	}

	@Override
	public void addPresupuestoActividad(PresupuestoDTO presupuestos, Actividad actividad, Rubro rubro, Double monto) {
		Presupuesto presupuesto = presupuestos.get(actividad, rubro);
		if (monto != null) {
			if (presupuesto == null) {
				presupuesto = new Presupuesto();
				presupuesto.setActividad(actividad);
				presupuesto.setRubro(rubro);
				presupuesto.setTipoPresupuesto(tipoPresupuestoService.findTipoPresupuestoActividad());
				presupuestos.putPresupuestoActividad(presupuesto);
			}
			presupuesto.setMonto(monto);
		} else {
			if (presupuesto != null) {
				presupuestos.remove(actividad, rubro);
			}
		}
	}

	@Override
	public void addPresupuestoProyecto(PresupuestoDTO presupuestos, Rubro rubro, Double monto) {
		Presupuesto presupuesto = presupuestos.get(rubro);
		if (monto != null) {
			if (presupuesto == null) {
				presupuesto = new Presupuesto();
				presupuesto.setProyecto(presupuestos.getProyecto());
				presupuesto.setRubro(rubro);
				presupuesto.setTipoPresupuesto(tipoPresupuestoService.findTipoPresupuestoProyecto());
				presupuestos.putPresupuestoProyecto(presupuesto);
			}
			presupuesto.setMonto(monto);
		} else {
			if (presupuesto != null) {
				presupuestos.remove(rubro);
			}
		}
	}

	public void setTipoPresupuestoService(TipoPresupuestoService tipoPresupuestoService) {
		this.tipoPresupuestoService = tipoPresupuestoService;
	}

	@Override
	public Presupuesto findPresupuestoByActividadRubro(Actividad actividad,
			Rubro rubro) {
		return dao.findPresupuestoByActividadRubro(actividad, rubro);
	}

}
