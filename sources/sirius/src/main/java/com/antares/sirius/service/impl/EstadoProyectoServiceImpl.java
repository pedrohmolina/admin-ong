package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.sirius.dao.EstadoProyectoDAO;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.service.EstadoProyectoService;
import com.antares.sirius.service.ParametroService;

/**
 * Implementacion de la interfaz EstadoProyectoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class EstadoProyectoServiceImpl extends BaseServiceImpl<EstadoProyecto, EstadoProyectoDAO> implements EstadoProyectoService {

	private ParametroService parametroService;

	public EstadoProyecto findDefault() {
		Integer id = parametroService.findIdEstadoActividadLatente();
		return dao.findById(id);
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
