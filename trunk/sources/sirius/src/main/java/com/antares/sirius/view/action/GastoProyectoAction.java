package com.antares.sirius.view.action;

import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.GastoForm;

public class GastoProyectoAction extends GastoAction {

	protected ProyectoService proyectoService;

	@Override
	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoProyecto());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyecto(proyectoService.findById(Integer.parseInt(form.getFiltroIdProyecto())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
		entity.setTipoGasto(tipoGastoService.findTipoGastoProyecto());
		super.updateEntity(entity, form);
		entity.setProyecto(proyectoService.findById(Integer.parseInt(form.getIdProyecto())));
	}

	@Override
	protected void loadCollections(GastoForm form) {
		super.loadCollections(form);
		form.setProyectos(proyectoService.findAll());
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
