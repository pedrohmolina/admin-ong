package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.CREATE;

import java.util.Collection;
import java.util.LinkedHashMap;
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
import com.antares.sirius.service.NotificacionService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.TipoAsignacionService;
import com.antares.sirius.view.form.AsignacionForm;

public class AsignacionAction extends BaseAction<Asignacion, AsignacionForm, AsignacionService> {

	private TipoAsignacionService tipoAsignacionService;
	private ProyectoService proyectoService;
	private ActividadService actividadService;
	private PersonaService personaService;
	private NotificacionService notificacionService;

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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = super.save(mapping, form, request, response);
		AsignacionForm viewForm = (AsignacionForm)form;

		// Si la creacion fue exitosa, se envia una notificacion
		if (viewForm.getAction().equals(CREATE) && forward.equals(mapping.findForward("successCreate"))) {
			Persona persona = personaService.findById(Utils.parseInteger(viewForm.getIdPersona()));
			Actividad actividad = actividadService.findById(Utils.parseInteger(viewForm.getIdActividad()));
			String msg = getResources(request).getMessage("sirius.notificacion.asignacion", actividad.getNombre(), actividad.getProyecto().getNombre());
			notificacionService.ejecutarNotificacion(persona.getUsuario(), actividad.getProyecto(), msg);
		}
		return forward;
	}
	
	@Override
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		Asignacion entity = service.findById(id);
		if (entity != null) {
			service.delete(entity);
			
			// Se envia una notificacion avisando que la persona ya no se encuentra asignada a la actividad
			Actividad actividad = entity.getActividad();
			String msg = getResources(request).getMessage("sirius.notificacion.desasignacion", actividad.getNombre(), actividad.getProyecto().getNombre());
			notificacionService.ejecutarNotificacion(entity.getPersona().getUsuario(), actividad.getProyecto(), msg);

			forward = query(mapping, form, request, response);
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}
	
	@Override
	protected void loadCollections(AsignacionForm form) {
		form.setTiposAsignacion(tipoAsignacionService.findAll());
		form.setPersonas(personaService.findAll());
		if (CREATE.equals(form.getAction())) {
			form.setProyectos(proyectoService.findAllNoFinalizados());
			form.setActividades(actividadService.findAllNoFinalizados());
		} else{
			form.setProyectos(proyectoService.findAll());
			form.setActividades(actividadService.findAll());
		}
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

		if (proyectoService.isFinalizado(actividad.getProyecto())) {
			errors.add("error", new ActionMessage("errors.proyectoFinalizado"));
		} else if (actividadService.isCancelada(actividad)) {
			errors.add("error", new ActionMessage("errors.actividadCancelada"));
		} else if (actividadService.isCumplida(actividad)) {
			errors.add("error", new ActionMessage("errors.actividadCumplida"));
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

		Map<String, String> map = new LinkedHashMap<String, String>();
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

	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

}
