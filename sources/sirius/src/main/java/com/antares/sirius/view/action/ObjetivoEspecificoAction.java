package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ObjetivoEspecificoFilter;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.service.ObjetivoEspecificoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.view.form.ObjetivoEspecificoForm;

public class ObjetivoEspecificoAction extends BaseAction<ObjetivoEspecifico, ObjetivoEspecificoForm, ObjetivoEspecificoService> {
	
	private ObjetivoGeneralService objetivoGeneralService;

	@Override
	public ObjetivoEspecificoFilter createFilter(ObjetivoEspecificoForm form) {
		ObjetivoEspecificoFilter filter = new ObjetivoEspecificoFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdObjetivoGeneral())) {
			filter.setObjetivoGeneral(objetivoGeneralService.findById(Integer.parseInt(form.getFiltroIdObjetivoGeneral())));
		}
		return filter;
	}

	@Override
	public void updateEntity(ObjetivoEspecifico entity, ObjetivoEspecificoForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		entity.setPonderacion(Integer.parseInt(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdObjetivoGeneral())) {
			entity.setObjetivoGeneral(objetivoGeneralService.findById(Integer.parseInt(form.getIdObjetivoGeneral())));
		}
	}

	@Override
	protected void loadCollections(ObjetivoEspecificoForm form) {
		form.setObjetivosGenerales(objetivoGeneralService.findAll());
	}

	@Override
	protected ActionErrors validate(ObjetivoEspecificoForm form) {
		ActionErrors errors = new ActionErrors();
		ObjetivoGeneral objetivoGeneral = objetivoGeneralService.findById(Integer.parseInt(form.getIdObjetivoGeneral()));
		if (Utils.excedePonderacion(Integer.parseInt(form.getPonderacion()), objetivoGeneral.getObjetivosEspecificos(), form.getId())) {
			errors.add("error", new ActionMessage("errors.ponderiacion", Utils.getMessage("sirius.objetivoEspecifico.objetivoGeneral.label")));
		}
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.objetivoEspecifico.nombre.label")));
		}
		return errors;
	}

	public void setObjetivoGeneralService(ObjetivoGeneralService objetivoGeneralService) {
		this.objetivoGeneralService = objetivoGeneralService;
	}

}
