package com.antares.sirius.service.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.AsignacionDAO;
import com.antares.sirius.filter.AsignacionFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAsignacion;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.AsignacionService;
import com.antares.sirius.service.ProyectoService;

/**
 * Implementacion de la interfaz AsignacionService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class AsignacionServiceImpl extends BusinessEntityServiceImpl<Asignacion, AsignacionDAO> implements AsignacionService {
	
	private ProyectoService proyectoService;
	private ActividadService actividadService;

	public boolean isAsignacionRepetida(Integer id, Actividad actividad, Persona persona, TipoAsignacion tipoAsignacion) {
		boolean isAsignacionRepetida = false;
		AsignacionFilter filter = new AsignacionFilter();
		filter.setActividad(actividad);
		filter.setPersona(persona);
		filter.setTipoAsignacion(tipoAsignacion);
		Collection<Asignacion> asignaciones = dao.findByFilter(filter); 
		if (!asignaciones.isEmpty()) {
			if (id != null) {
				for (Asignacion asignacion : asignaciones) {
					if (!asignacion.getId().equals(id)) {
						isAsignacionRepetida = true;
					}
				}
			} else {
				isAsignacionRepetida = true;
			}
		}
		return isAsignacionRepetida;
	}

	public Collection<Proyecto> findProyectosAsignados(Persona persona) {
		Collection<Asignacion> asignaciones = dao.findAllByPersona(persona);
		Set<Proyecto> proyectos = new TreeSet<Proyecto>(new Comparator<Proyecto>() {
			public int compare(Proyecto o1, Proyecto o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
		for (Asignacion asignacion : asignaciones) {
			Proyecto proyecto = asignacion.getActividad().getProyecto();
			if (!proyectoService.isFinalizado(proyecto)) {
				proyectos.add(asignacion.getActividad().getProyecto());
			}
		}
		return proyectos;
	}

	public Collection<Actividad> findActividadesAsignadas(Persona persona) {
		return findActividadesAsignadas(persona, null);
	}

	public Collection<Actividad> findActividadesAsignadas(Persona persona, Proyecto proyecto) {
		Collection<Asignacion> asignaciones = dao.findAllByPersonaProyecto(persona, proyecto);
		Set<Actividad> actividades = new TreeSet<Actividad>(new Comparator<Actividad>() {
			public int compare(Actividad o1, Actividad o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
		for (Asignacion asignacion : asignaciones) {
			Actividad actividad = asignacion.getActividad();
			if (!proyectoService.isFinalizado(proyecto) && !actividadService.isSuspendida(actividad)) {
				actividades.add(asignacion.getActividad());
			}
		}
		return actividades;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

}
