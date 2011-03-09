package com.antares.sirius.service.impl;

import static java.lang.Boolean.TRUE;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.GastoDAO;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.ParametroService;
import com.antares.sirius.service.ProyectoService;

/**
 * Implementacion de la interfaz GastoService.
 *
 * @version 1.0.0 Created 16/02/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class GastoServiceImpl extends BusinessEntityServiceImpl<Gasto, GastoDAO> implements GastoService {

	private ParametroService parametroService;
	private ProyectoService proyectoService;

	public void ejecutarConfirmacion(Gasto entity) {
		entity.setConfirmado(TRUE);
		dao.save(entity);
	}

	private Proyecto findProyecto(Gasto gasto) {
		Proyecto proyecto = null;
		if (gasto.getTipoGasto().getId().equals(parametroService.findIdTipoGastoProyecto())) {
			proyecto = gasto.getProyecto();
		}
		if (gasto.getTipoGasto().getId().equals(parametroService.findIdTipoGastoActividad())) {
			proyecto = gasto.getActividad().getProyecto();
		}
		return proyecto;
	}
	
	public boolean isIndividual(Gasto gasto) {
		boolean isIndividual = Boolean.TRUE;
		Proyecto proyecto = findProyecto(gasto);
		if (proyecto != null) {
			isIndividual = proyectoService.isIndividual(proyecto);
		}
		return isIndividual;
	}

	public boolean isAgrupado(Gasto gasto) {
		boolean isAgrupado = Boolean.FALSE;
		Proyecto proyecto = findProyecto(gasto);
		if (proyecto != null) {
			isAgrupado = proyectoService.isAgrupado(proyecto);
		}
		return isAgrupado;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
