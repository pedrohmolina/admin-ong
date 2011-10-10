package com.antares.sirius.service.impl;

import java.util.Collection;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.ObjetivoGeneralDAO;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.EstadoProyectoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.ParametroService;

/**
 * Implementacion de la interfaz ObjetivoGeneralService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ObjetivoGeneralServiceImpl extends BusinessEntityServiceImpl<ObjetivoGeneral, ObjetivoGeneralDAO> implements ObjetivoGeneralService {

	private EstadoProyectoService estadoProyectoService;
	private ParametroService parametroService;

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido = false;
		ObjetivoGeneral entity = dao.findByNombre(nombre);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

	public Collection<ObjetivoGeneral> findAllByProyecto(Proyecto proyecto) {
		return dao.findAllByProyecto(proyecto);
	}

	public Collection<ObjetivoGeneral> findAllNoFinalizadosNiCierre() {
		EstadoProyecto estadoFinalizado = estadoProyectoService.findById(parametroService.findIdEstadoProyectoFinalizado());
		EstadoProyecto estadoCierre = estadoProyectoService.findById(parametroService.findIdEstadoProyectoCierre());
		return dao.findAllExceptEstados(estadoFinalizado, estadoCierre);
	}

	public void setEstadoProyectoService(EstadoProyectoService estadoProyectoService) {
		this.estadoProyectoService = estadoProyectoService;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
