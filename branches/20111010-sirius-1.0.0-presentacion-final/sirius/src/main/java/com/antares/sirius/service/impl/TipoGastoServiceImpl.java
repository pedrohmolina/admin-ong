package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.sirius.dao.TipoGastoDAO;
import com.antares.sirius.model.TipoGasto;
import com.antares.sirius.service.ParametroService;
import com.antares.sirius.service.TipoGastoService;

/**
 * Implementacion de la interfaz TipoGastoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoGastoServiceImpl extends BaseServiceImpl<TipoGasto, TipoGastoDAO> implements TipoGastoService {

	private ParametroService parametroService;
	
	public TipoGasto findTipoGastoActividad() {
		return dao.findById(parametroService.findIdTipoGastoActividad());
	}

	public TipoGasto findTipoGastoOrganizacion() {
		return dao.findById(parametroService.findIdTipoGastoOrganizacion());
	}

	public TipoGasto findTipoGastoProyecto() {
		return dao.findById(parametroService.findIdTipoGastoProyecto());
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
