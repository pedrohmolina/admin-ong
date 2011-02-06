package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.ProyectoDAO;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.ProyectoService;

/**
 * Implementacion de la interfaz ProyectoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ProyectoServiceImpl extends BusinessEntityServiceImpl<Proyecto, ProyectoDAO> implements ProyectoService {

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido;
		if (id == null) {
			isNombreRepetido = dao.findByNombre(nombre) != null;
		} else {
			isNombreRepetido = !dao.findByNombre(nombre).getId().equals(id);
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

}
