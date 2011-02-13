package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.sirius.dao.TipoIngresoDAO;
import com.antares.sirius.model.TipoIngreso;
import com.antares.sirius.service.ParametroService;
import com.antares.sirius.service.TipoIngresoService;

/**
 * Implementacion de la interfaz TipoIngresoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoIngresoServiceImpl extends BaseServiceImpl<TipoIngreso, TipoIngresoDAO> implements TipoIngresoService {

	private ParametroService parametroService;

	public boolean isIdFinanciacion(String id) {
		boolean isIdFinanciacion = false;
		if (id != null) {
			String idFinanciacion = parametroService.findIdTipoIngresoFinanciacion();
			if (idFinanciacion != null) {
				isIdFinanciacion = idFinanciacion.equals(id);
			}
		}
		return isIdFinanciacion;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
