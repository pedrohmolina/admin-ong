package com.antares.sirius.view.action;

import java.util.HashSet;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.RolFilter;
import com.antares.sirius.model.Acceso;
import com.antares.sirius.model.Rol;
import com.antares.sirius.service.AccesoService;
import com.antares.sirius.service.RolService;
import com.antares.sirius.view.form.RolForm;

public class RolAction extends BaseAction<Rol, RolForm, RolService> {
	
	private AccesoService accesoService;
	
	@Override
	public RolFilter createFilter(RolForm form) {
		RolFilter filter = new RolFilter();
		filter.setNombre(form.getFiltroNombre());
		return filter;
	}

	@Override
	public void updateEntity(Rol entity, RolForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		if (entity.getAccesos() == null) {
			entity.setAccesos(new HashSet<Acceso>());
		} else {
			entity.getAccesos().clear();
		}
		if (form.getIdAccesos() != null) {
			for (Integer idAcceso : form.getIdAccesos()) {
				if (idAcceso > 0) {
					entity.getAccesos().add(accesoService.findById(idAcceso));
				}
			}
		}
	}

	@Override
	protected void loadCollections(RolForm form) {
		form.setAccesos(accesoService.findAll());		
	}

	@Override
	protected ActionErrors validate(RolForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.rol.nombre.label")));
		}
		return errors;
	}

	public void setAccesoService(AccesoService accesoService) {
		this.accesoService = accesoService;
	}

}
