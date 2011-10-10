package com.antares.sirius.service.impl;

import java.util.Collection;
import java.util.Date;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.ActividadDAO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.EstadoActividad;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.EstadoActividadService;
import com.antares.sirius.service.EstadoProyectoService;
import com.antares.sirius.service.ParametroService;

/**
 * Implementacion de la interfaz ActividadService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ActividadServiceImpl extends BusinessEntityServiceImpl<Actividad, ActividadDAO> implements ActividadService {

	private ParametroService parametroService;
	private EstadoActividadService estadoActividadService;
	private EstadoProyectoService estadoProyectoService;
	
	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido = false;
		Actividad entity = dao.findByNombre(nombre);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

	public boolean isTransicionValida(Actividad actividad, Integer idEstado) {
		boolean isTransicionValida = false;
		for (EstadoActividad estado : actividad.getEstadoActividad().getProximosEstadosPosibles()) {
			if (estado.getId().equals(idEstado)) {
				isTransicionValida = true;
			}
		}
		return isTransicionValida;
	}

	public void saveCambioEstado(Actividad actividad, Integer idEstado) {
		EstadoActividad nuevoEstado = estadoActividadService.findById(idEstado);
		actividad.setEstadoActividad(nuevoEstado);
		if (idEstado.equals(parametroService.findIdEstadoActividadCancelada()) || idEstado.equals(parametroService.findIdEstadoActividadCumplida())) {
			actividad.setFechaFinalizacion(new Date());
		}
		dao.save(actividad);
	}

	public boolean isActualizarCompletitud(Actividad actividad) {
		boolean actualizarCompletitud = false;
		if (actividad.getEstadoActividad() != null) {
			Integer idEstadoActividadProgreso = parametroService.findIdEstadoActividadProgreso();
			actualizarCompletitud = actividad.getEstadoActividad().getId().equals(idEstadoActividadProgreso);
		}
		return actualizarCompletitud;
	}

	public Collection<Actividad> findAllByProyecto(Proyecto proyecto) {
		return dao.findAllByProyecto(proyecto);
	}

	public boolean isSuspendida(Actividad actividad) {
		Integer idEstadoSuspendida = parametroService.findIdEstadoActividadSuspendida();
		return actividad.getEstadoActividad().getId().equals(idEstadoSuspendida);
	}

	public boolean isCancelada(Actividad actividad) {
		Integer idEstadoCancelada = parametroService.findIdEstadoActividadCancelada();
		return actividad.getEstadoActividad().getId().equals(idEstadoCancelada);
	}

	public boolean isCumplida(Actividad actividad) {
		Integer idEstadoCumplida = parametroService.findIdEstadoActividadCumplida();
		return actividad.getEstadoActividad().getId().equals(idEstadoCumplida);
	}

	public Collection<Actividad> findAllNoSuspendidasByProyecto(Proyecto proyecto) {
		EstadoActividad estadoSuspendida = estadoActividadService.findById(parametroService.findIdEstadoActividadSuspendida());
		return dao.findAllByProyectoExceptEstados(proyecto, estadoSuspendida);
	}

	public Collection<Actividad> findAllNoFinalizados() {
		EstadoProyecto estadoFinalizado = estadoProyectoService.findById(parametroService.findIdEstadoProyectoFinalizado());
		return dao.findAllExceptEstados(estadoFinalizado);
	}

	public Collection<Actividad> findAllByObjetivoGeneral(ObjetivoGeneral objetivoGeneral) {
		return dao.findAllByObjetivoGeneral(objetivoGeneral);
	}

	public Collection<Actividad> findAllByObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico) {
		return dao.findAllByObjetivoEspecifico(objetivoEspecifico);
	}

	public boolean existenActividadesNoFinalizadas(Proyecto proyecto) {
		EstadoActividad estadoCancelada = estadoActividadService.findById(parametroService.findIdEstadoActividadCancelada());
		EstadoActividad estadoCumplida = estadoActividadService.findById(parametroService.findIdEstadoActividadCumplida());
		return dao.countByProyectoExceptEstados(proyecto, estadoCancelada, estadoCumplida) > 0;
	}

	public Collection<Actividad> findAllByMeta(Meta meta) {
		return dao.findAllByMeta(meta);
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	public void setEstadoActividadService(EstadoActividadService estadoActividadService) {
		this.estadoActividadService = estadoActividadService;
	}

	public void setEstadoProyectoService(EstadoProyectoService estadoProyectoService) {
		this.estadoProyectoService = estadoProyectoService;
	}

}
