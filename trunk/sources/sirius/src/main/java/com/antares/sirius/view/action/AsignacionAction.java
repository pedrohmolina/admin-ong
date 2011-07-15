package com.antares.sirius.view.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.AsignacionFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAsignacion;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.AsignacionService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.TipoAsignacionService;
import com.antares.sirius.view.form.AsignacionForm;

public class AsignacionAction extends BaseAction<Asignacion, AsignacionForm, AsignacionService> {

	private TipoAsignacionService tipoAsignacionService;
	private ProyectoService proyectoService;
	private ActividadService actividadService;
	private PersonaService personaService;

	@Override
	public AsignacionFilter createFilter(AsignacionForm form) {
		AsignacionFilter filter = new AsignacionFilter();
		if (Utils.isNotNullNorEmpty(form.getFiltroIdActividad())) {
			filter.setActividad(actividadService.findById(Utils.parseInteger(form.getFiltroIdActividad())));
		} else if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyecto(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdPersona())) {
			filter.setPersona(personaService.findById(Utils.parseInteger(form.getFiltroIdPersona())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Asignacion entity, AsignacionForm form) {
		entity.setCantidad(Utils.parseInteger(form.getCantidad()));
		if (Utils.isNotNullNorEmpty(form.getIdTipoAsignacion())) {
			entity.setTipoAsignacion(tipoAsignacionService.findById(Utils.parseInteger(form.getIdTipoAsignacion())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdActividad())) {
			entity.setActividad(actividadService.findById(Utils.parseInteger(form.getIdActividad())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdPersona())) {
			entity.setPersona(personaService.findById(Utils.parseInteger(form.getIdPersona())));
		}
	}

	@Override
	protected void loadCollections(AsignacionForm form) {
		form.setTiposAsignacion(tipoAsignacionService.findAll());
		form.setProyectos(proyectoService.findAll());
		form.setActividades(actividadService.findAll());
		form.setPersonas(personaService.findAll());
	}

	@Override
	protected ActionErrors validate(AsignacionForm form) {
		ActionErrors errors = new ActionErrors();
		Actividad actividad = actividadService.findById(Utils.parseInteger(form.getIdActividad()));
		Persona persona = personaService.findById(Utils.parseInteger(form.getIdPersona()));
		TipoAsignacion tipoAsignacion = tipoAsignacionService.findById(Utils.parseInteger(form.getIdTipoAsignacion()));
		if (service.isAsignacionRepetida(form.getId(), actividad, persona, tipoAsignacion)) {
			errors.add("error", new ActionMessage("errors.asignacion"));
		}
		return errors;
	}

	public ActionForward cargarComboActividad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String id =(String) request.getParameter("idProyecto");
		Collection<Actividad> lista = null;
		if (Utils.isNotNullNorEmpty(id)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
			lista = actividadService.findAllByProyecto(proyecto);
		} else {
			lista = actividadService.findAll();
		}
		((AsignacionForm)form).setActividades(lista);

		Map<String, String> map = new HashMap<String, String>();
		for (Actividad actividad : lista) {
			map.put(new Integer(actividad.getId()).toString(), actividad.getNombre());
		}
		
		sendJSON(response, map);
		return null;
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

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
