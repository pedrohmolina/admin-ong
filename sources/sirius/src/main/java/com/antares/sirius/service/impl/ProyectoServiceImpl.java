package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.ProyectoDAO;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.ParametroService;
import com.antares.sirius.service.ProyectoService;

/**
 * Implementacion de la interfaz ProyectoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ProyectoServiceImpl extends BusinessEntityServiceImpl<Proyecto, ProyectoDAO> implements ProyectoService {

	private ParametroService parametroService;

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido = false;
		Proyecto entity = dao.findByNombre(nombre);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

	public void saveCambioEstado(Proyecto proyecto, Integer idEstado) {
		EstadoProyecto nuevoEstado = null;
		for (EstadoProyecto estado : proyecto.getEstadoProyecto().getProximosEstadosPosibles()) {
			if (estado.getId().equals(idEstado)) {
				nuevoEstado = estado;
			}
		}
		if (nuevoEstado != null) {
			proyecto.setEstadoProyecto(nuevoEstado);
			dao.save(proyecto);
		}
	}

	public boolean isIndividual(Proyecto proyecto) {
		boolean isIndividual = Boolean.TRUE;
		isIndividual = proyecto.getTipoAgrupamiento().getId().equals(parametroService.findIdTipoAgrupamientoIndividual());
		return isIndividual;
	}

	public boolean isAgrupado(Proyecto proyecto) {
		boolean isAgrupado = Boolean.FALSE;
		isAgrupado = proyecto.getTipoAgrupamiento().getId().equals(parametroService.findIdTipoAgrupamientoAgrupado());
		return isAgrupado;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
