package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.CREATE;
import static com.antares.sirius.base.Constants.ACCESO_ADMIN_GASTOS_ACTIVIDAD;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.antares.commons.exception.RestrictedAccessException;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.AsignacionService;
import com.antares.sirius.service.NotificacionService;
import com.antares.sirius.service.PresupuestoService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.GastoForm;

public class GastoActividadAction extends GastoAction {

	private ProyectoService proyectoService;
	private ActividadService actividadService;
	private AsignacionService asignacionService;
	private PresupuestoService presupuestoService;
	private NotificacionService notificacionService;

	@Override
	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyectoActividad(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdActividad())) {
			filter.setActividad(actividadService.findById(Utils.parseInteger(form.getFiltroIdActividad())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdPersona())) {
			filter.setPersona(personaService.findById(Utils.parseInteger(form.getFiltroIdPersona())));
		}
		filter.setFechaDesde(Utils.parseDate(form.getFiltroFechaDesde()));
		filter.setFechaHasta(Utils.parseDate(form.getFiltroFechaHasta()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRubro())) {
			filter.setRubro(rubroService.findById(Utils.parseInteger(form.getFiltroIdRubro())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
		entity.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		super.updateEntity(entity, form);
		entity.setActividad(actividadService.findById(Utils.parseInteger(form.getIdActividad())));

		if (proyectoService.isIndividual(entity.getActividad().getProyecto())) {
			entity.setProveedor(proveedorService.findById(Utils.parseInteger(form.getIdProveedor())));
			entity.setTipoComprobante(tipoComprobanteService.findById(Utils.parseInteger(form.getIdTipoComprobante())));
			entity.setNumeroComprobante(form.getNumeroComprobante());
		} else {
			entity.setProveedor(null);
			entity.setTipoComprobante(null);
			entity.setNumeroComprobante(null);
		}

		if (proyectoService.isAgrupado(entity.getActividad().getProyecto())) {
			entity.setPaquete(form.getPaquete());
		} else {
			entity.setPaquete(null);
		}
	}

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = super.save(mapping, form, request, response);
		GastoForm viewForm = (GastoForm)form;

		// Si la creacion fue exitosa, se envia una notificacion
		if (viewForm.getAction().equals(CREATE) && forward.equals(mapping.findForward("successCreate"))) {
			Rubro rubro = rubroService.findById(Utils.parseInteger(viewForm.getIdRubro())).getRubroPrimerNivel();
			Actividad actividad = actividadService.findById(Utils.parseInteger(viewForm.getIdActividad()));
			Double montoPresupuestado = presupuestoService.presupuestoActividad(actividad, rubro);
			Double montoGastado = service.gastoActividad(actividad, rubro);
			if (montoGastado > montoPresupuestado) {
				Double diff = new BigDecimal(montoGastado).subtract(new BigDecimal(montoPresupuestado)).doubleValue();
				String montoExcedido = Utils.formatDouble(diff);
				String msg = getResources(request).getMessage("sirius.notificacion.gastoExcedido.actividad", actividad.getNombre(), actividad.getProyecto().getNombre(), rubro.getNombre(), montoExcedido);
				notificacionService.ejecutarNotificacionGastoProyectoExcedido(actividad.getProyecto(), msg);
			}
		}
		return forward;
	}
	
	@Override
	protected void loadCollections(GastoForm form) {
		super.loadCollections(form);
		form.setPersonas(personaService.findAll());

		form.setProyectos(getProyectos(form));
		if (form.getActividades() == null) {
			form.setActividades(new ArrayList<Actividad>());
		}

		if (Utils.isNullOrEmpty(form.getIdPersona())) {
			form.setIdPersona(personaService.findPersonaLogueada().getId().toString());
		}
	}

	@Override
	protected void postLoadEntity(Gasto entity, GastoForm viewForm) {
		viewForm.setIndividual(service.isIndividual(entity));
		viewForm.setAgrupado(service.isAgrupado(entity));
	}

	@Override
	protected ActionErrors validate(GastoForm form) {
		ActionErrors errors = new ActionErrors();
		Actividad actividad = actividadService.findById(Utils.parseInteger(form.getIdActividad()));
		Proyecto proyecto = actividad.getProyecto();
		if (proyectoService.isFinalizado(proyecto)) {
			errors.add("error", new ActionMessage("errors.proyectoFinalizado"));
		} else if (actividadService.isSuspendida(actividad)) {
			errors.add("error", new ActionMessage("errors.actividadSuspendida"));
		} else if (actividad.getFechaFinalizacion() != null && actividad.getFechaFinalizacion().before(Utils.parseDate(form.getFecha()))) {
			errors.add("error", new ActionMessage("errors.fechaFinalizacion", Utils.getMessage("sirius.gasto.fecha.label"), Utils.formatDate(actividad.getFechaFinalizacion())));
		} else  if (proyectoService.isIndividual(proyecto)) {
			if (Utils.isNullOrEmpty(form.getIdProveedor())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.proveedor.label")));
			}
			if (Utils.isNullOrEmpty(form.getIdTipoComprobante())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.tipoComprobante.label")));
			}
			if (Utils.isNullOrEmpty(form.getNumeroComprobante())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.numeroComprobante.label")));
			}
		} else if (proyectoService.isAgrupado(proyecto)) {
			if (Utils.isNullOrEmpty(form.getPaquete())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.paquete.label")));
			}
		}
		return errors;
	}

	public ActionForward confirmar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		Gasto entity = service.findById(id);
		try {
			if (entity != null) {
				service.ejecutarConfirmacion(entity);
				forward = query(mapping, form, request, response);
			} else {
				forward = mapping.findForward("restrictedAccess"); 
			}
		} catch (RestrictedAccessException e) {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	public ActionForward cargarComboActividad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		GastoForm gastoForm = (GastoForm)form;
		String id =(String) request.getParameter("idProyecto");
		Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
		gastoForm.setActividades(getActividades(proyecto, gastoForm));
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Actividad actividad : gastoForm.getActividades()) {
			map.put(new Integer(actividad.getId()).toString(), actividad.getNombre());
		}
		
		sendJSON(response, map);
		return null;
	}

	public ActionForward isIndividual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idProyecto =(String) request.getParameter("idProyecto");
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (Utils.isNotNullNorEmpty(idProyecto)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(idProyecto));
			map.put("isIndividual", proyectoService.isIndividual(proyecto) ? "true" : "false");
		} else {
			map.put("isIndividual", "true");
		}
		
		sendJSON(response, map);
		return null;
	}
	
	public ActionForward isAgrupado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idProyecto =(String) request.getParameter("idProyecto");
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (Utils.isNotNullNorEmpty(idProyecto)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(idProyecto));
			map.put("isAgrupado", proyectoService.isAgrupado(proyecto) ? "true" : "false");
		} else {
			map.put("isAgrupado", "true");
		}
		
		sendJSON(response, map);
		return null;
	}

	public ActionForward initReferencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		GastoForm viewForm = (GastoForm)form;
		Integer id = new Integer(request.getParameter("id"));
		Gasto entity = service.findById(id);
		if (entity != null) {
			viewForm.setReferencia(entity.getReferencia());
			viewForm.setUpdated("false");
			forward = mapping.findForward("referencia");
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	public ActionForward saveReferencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		GastoForm viewForm = (GastoForm)form;
		Gasto entity = service.findById(viewForm.getId());
		try {
			if (entity != null) {
				if (viewForm.getReferencia() != null && viewForm.getReferencia().length() > 255) {
					ActionErrors errors = new ActionErrors();
					errors.add("error", new ActionMessage("errors.maxlength", new Object[]{Utils.getMessage("sirius.gasto.referencia.label"), "255"}));
					saveErrors(request, errors);
				} else {
					entity.setReferencia(viewForm.getReferencia());
					service.save(entity);
					viewForm.setUpdated("true");
				}
				forward = mapping.findForward("referencia");
			} else {
				forward = mapping.findForward("restrictedAccess"); 
			}
		} catch (RestrictedAccessException e) {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	public ActionForward removeReferencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		Gasto entity = service.findById(id);
		try {
			if (entity != null) {
				entity.setReferencia(null);
				service.save(entity);
				forward = query(mapping, form, request, response);
			} else {
				forward = mapping.findForward("restrictedAccess"); 
			}
		} catch (RestrictedAccessException e) {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	public ActionForward initList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GastoForm gastoForm = (GastoForm)form;
		gastoForm.initialize();
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		filter.setPersona(personaService.findPersonaLogueada());
		Collection<Gasto> result = service.findByFilter(filter);
		gastoForm.setResult(result);
		return mapping.findForward("query");
	}

	public ActionForward showList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GastoForm viewForm = (GastoForm)form;
		if (!getErrors(request).isEmpty()) {
			viewForm.setResult(null);
		}
		return mapping.findForward("query");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GastoForm gastoForm = (GastoForm)form;
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		filter.setPersona(personaService.findPersonaLogueada());
		if (Utils.isNotNullNorEmpty(gastoForm.getFiltroFechaDesde())) {
			filter.setFechaDesde(Utils.parseDate(gastoForm.getFiltroFechaDesde()));
		}
		if (Utils.isNotNullNorEmpty(gastoForm.getFiltroFechaHasta())) {
			filter.setFechaHasta(Utils.parseDate(gastoForm.getFiltroFechaHasta()));
		}
		Collection<Gasto> result = service.findByFilter(filter);
		gastoForm.setResult(result);
		return mapping.findForward("query");
	}

	protected Collection<Proyecto> getProyectos(GastoForm form) {
		Collection<Proyecto> proyectos;
		if (CREATE.equals(form.getAction())) {
			if (usuarioService.userHasAccess(ACCESO_ADMIN_GASTOS_ACTIVIDAD)) {
				proyectos = proyectoService.findAllNoFinalizados();
			} else {
				Persona persona = personaService.findPersonaLogueada();
				proyectos = asignacionService.findProyectosAsignados(persona);
			}
		} else{
			proyectos = proyectoService.findAll();
		}
		return proyectos;
	}

	protected Collection<Actividad> getActividades(Proyecto proyecto, GastoForm form) {
		Collection<Actividad> actividades;
		if (CREATE.equals(form.getAction())) {
			if (usuarioService.userHasAccess(ACCESO_ADMIN_GASTOS_ACTIVIDAD)) {
				actividades = actividadService.findAllNoSuspendidasByProyecto(proyecto);
			} else {
				Persona persona = personaService.findPersonaLogueada();
				actividades = asignacionService.findActividadesAsignadas(persona, proyecto);
			}
		} else{
			actividades = actividadService.findAllByProyecto(proyecto);
		}
		return actividades;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

	public void setAsignacionService(AsignacionService asignacionService) {
		this.asignacionService = asignacionService;
	}

	public void setPresupuestoService(PresupuestoService presupuestoService) {
		this.presupuestoService = presupuestoService;
	}

	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

}
