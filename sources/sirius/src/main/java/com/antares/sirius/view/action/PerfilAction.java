package com.antares.sirius.view.action;

import java.util.HashSet;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.PerfilFilter;
import com.antares.sirius.model.Perfil;
import com.antares.sirius.model.Rol;
import com.antares.sirius.service.PerfilService;
import com.antares.sirius.service.RolService;
import com.antares.sirius.view.form.PerfilForm;

public class PerfilAction extends BaseAction<Perfil, PerfilForm, PerfilService> {
	
	private RolService rolService;
	
	@Override
	public PerfilFilter createFilter(PerfilForm form) {
		PerfilFilter filter = new PerfilFilter();
		filter.setNombre(form.getFiltroNombre());
		return filter;
	}

	@Override
	public void updateEntity(Perfil entity, PerfilForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		if (entity.getRoles() == null) {
			entity.setRoles(new HashSet<Rol>());
		} else {
			entity.getRoles().clear();
		}
		if (form.getIdRoles() != null) {
			for (Integer idRol : form.getIdRoles()) {
				entity.getRoles().add(rolService.findById(idRol));
			}
		}
	}

	@Override
	protected ActionErrors validate(PerfilForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.perfil.nombre.label")));
		}
		return errors;
	}

	@Override
	protected void loadCollections(PerfilForm form) {
		form.setRoles(rolService.findAll());		
	}

	public void setRolService(RolService rolService) {
		this.rolService = rolService;
	}

}
