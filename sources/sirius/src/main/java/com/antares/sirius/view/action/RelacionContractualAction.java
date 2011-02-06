package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.RelacionContractualFilter;
import com.antares.sirius.model.RelacionContractual;
import com.antares.sirius.service.RelacionContractualService;
import com.antares.sirius.view.form.RelacionContractualForm;

public class RelacionContractualAction extends BaseAction<RelacionContractual, RelacionContractualForm, RelacionContractualService> {
	
	@Override
	public RelacionContractualFilter createFilter(RelacionContractualForm form) {
		RelacionContractualFilter filter = new RelacionContractualFilter();
		filter.setNombre(form.getFiltroNombre());
		return filter;
	}

	@Override
	public void updateEntity(RelacionContractual entity, RelacionContractualForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
	}

	@Override
	protected ActionErrors validate(RelacionContractualForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.relacionContractual.nombre.label")));
		}
		return errors;
	}

}
