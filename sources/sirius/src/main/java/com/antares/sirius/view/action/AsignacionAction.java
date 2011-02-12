package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.AsignacionFilter;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.AsignacionService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.TipoAsignacionService;
import com.antares.sirius.view.form.AsignacionForm;

public class AsignacionAction extends BaseAction<Asignacion, AsignacionForm, AsignacionService> {

	private TipoAsignacionService tipoAsignacionService;
	private ActividadService actividadService;
	private PersonaService personaService;

	@Override
	public AsignacionFilter createFilter(AsignacionForm form) {
		AsignacionFilter filter = new AsignacionFilter();
		if (Utils.isNotNullNorEmpty(form.getFiltroIdActividad())) {
			filter.setActividad(actividadService.findById(Integer.parseInt(form.getFiltroIdActividad())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdPersona())) {
			filter.setPersona(personaService.findById(Integer.parseInt(form.getFiltroIdPersona())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Asignacion entity, AsignacionForm form) {
		entity.setCantidad(Integer.parseInt(form.getCantidad()));
		if (Utils.isNotNullNorEmpty(form.getIdTipoAsignacion())) {
			entity.setTipoAsignacion(tipoAsignacionService.findById(Integer.parseInt(form.getIdTipoAsignacion())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdActividad())) {
			entity.setActividad(actividadService.findById(Integer.parseInt(form.getIdActividad())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdPersona())) {
			entity.setPersona(personaService.findById(Integer.parseInt(form.getIdPersona())));
		}
	}

	@Override
	protected void loadCollections(AsignacionForm form) {
		form.setTiposAsignacion(tipoAsignacionService.findAll());
		form.setActividades(actividadService.findAll());
		form.setPersonas(personaService.findAll());
	}

	@Override
	protected ActionErrors validate(AsignacionForm form) {
		ActionErrors errors = new ActionErrors();
		//TODO revisar estas validaciones una vez que este hecho el CU
		return errors;
	}

	public void setTipoAsignacionService(TipoAsignacionService tipoAsignacionService) {
		this.tipoAsignacionService = tipoAsignacionService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

}
