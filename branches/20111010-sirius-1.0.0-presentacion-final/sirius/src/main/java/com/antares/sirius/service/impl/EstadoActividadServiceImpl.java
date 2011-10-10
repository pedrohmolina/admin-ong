package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.sirius.dao.EstadoActividadDAO;
import com.antares.sirius.model.EstadoActividad;
import com.antares.sirius.service.EstadoActividadService;
import com.antares.sirius.service.ParametroService;

/**
 * Implementacion de la interfaz EstadoActividadService.
 *
 * @version 1.0.0 Created 12/02/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class EstadoActividadServiceImpl extends BaseServiceImpl<EstadoActividad, EstadoActividadDAO> implements EstadoActividadService {

	private ParametroService parametroService;

	public EstadoActividad findDefault() {
		Integer id = parametroService.findIdEstadoActividadLatente();
		return dao.findById(id);
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
