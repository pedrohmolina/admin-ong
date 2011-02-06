package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.MetaFilter;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.service.MetaService;
import com.antares.sirius.service.ObjetivoEspecificoService;
import com.antares.sirius.view.form.MetaForm;

public class MetaAction extends BaseAction<Meta, MetaForm, MetaService> {
	
	private ObjetivoEspecificoService objetivoEspecificoService;

	@Override
	public MetaFilter createFilter(MetaForm form) {
		MetaFilter filter = new MetaFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdObjetivoEspecifico())) {
			filter.setObjetivoEspecifico(objetivoEspecificoService.findById(Integer.parseInt(form.getFiltroIdObjetivoEspecifico())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Meta entity, MetaForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		entity.setPonderacion(Integer.parseInt(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdObjetivoEspecifico())) {
			entity.setObjetivoEspecifico(objetivoEspecificoService.findById(Integer.parseInt(form.getIdObjetivoEspecifico())));
		}
	}

	@Override
	protected void loadCollections(MetaForm form) {
		form.setObjetivosEspecificos(objetivoEspecificoService.findAll());
	}

	@Override
	protected ActionErrors validate(MetaForm form) {
		ActionErrors errors = new ActionErrors();
		ObjetivoEspecifico objetivoEspecifico = objetivoEspecificoService.findById(Integer.parseInt(form.getIdObjetivoEspecifico()));
		if (Utils.excedePonderacion(Integer.parseInt(form.getPonderacion()), objetivoEspecifico.getMetas(), form.getId())) {
			errors.add("error", new ActionMessage("errors.ponderiacion", Utils.getMessage("sirius.meta.objetivoEspecifico.label")));
		}
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.meta.nombre.label")));
		}
		return errors;
	}

	public void setObjetivoEspecificoService(ObjetivoEspecificoService objetivoEspecificoService) {
		this.objetivoEspecificoService = objetivoEspecificoService;
	}

}
