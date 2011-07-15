package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ObjetivoGeneralFilter;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.ObjetivoGeneralForm;

public class ObjetivoGeneralAction extends BaseAction<ObjetivoGeneral, ObjetivoGeneralForm, ObjetivoGeneralService> {

	private ProyectoService proyectoService;

	@Override
	public ObjetivoGeneralFilter createFilter(ObjetivoGeneralForm form) {
		ObjetivoGeneralFilter filter = new ObjetivoGeneralFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyecto(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		return filter;
	}

	@Override
	public void updateEntity(ObjetivoGeneral entity, ObjetivoGeneralForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		entity.setPonderacion(Utils.parseInteger(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdProyecto())) {
			entity.setProyecto(proyectoService.findById(Utils.parseInteger(form.getIdProyecto())));
		}
	}

	@Override
	protected void loadCollections(ObjetivoGeneralForm form) {
		form.setProyectos(proyectoService.findAll());
	}

	@Override
	protected ActionErrors validate(ObjetivoGeneralForm form) {
		ActionErrors errors = new ActionErrors();
		Proyecto proyecto = proyectoService.findById(Utils.parseInteger(form.getIdProyecto()));
		if (Utils.excedePonderacion(Utils.parseInteger(form.getPonderacion()), proyecto.getObjetivosGenerales(), form.getId())) {
			errors.add("error", new ActionMessage("errors.ponderiacion", Utils.getMessage("sirius.objetivoGeneral.proyecto.label")));
		}
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.objetivoGeneral.nombre.label")));
		}
		return errors;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
