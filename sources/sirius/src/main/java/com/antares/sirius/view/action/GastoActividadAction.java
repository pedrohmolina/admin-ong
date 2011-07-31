package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.CREATE;
import static com.antares.commons.enums.ActionEnum.UPDATE;

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
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.GastoForm;

public class GastoActividadAction extends GastoAction {

	protected ProyectoService proyectoService;
	protected ActividadService actividadService;

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
		filter.setFecha(Utils.parseDate(form.getFiltroFecha()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRubro())) {
			filter.setRubro(rubroService.findById(Utils.parseInteger(form.getFiltroIdRubro())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdOrigen())) {
			filter.setOrigen(origenService.findById(Utils.parseInteger(form.getFiltroIdOrigen())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProveedor())) {
			filter.setProveedor(proveedorService.findById(Utils.parseInteger(form.getFiltroIdProveedor())));
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
		if (((GastoForm)form).getAction().equals(CREATE)) {
			forward = mapping.findForward("successCreate");
		} else if (((GastoForm)form).getAction().equals(UPDATE)) {
			forward = mapping.findForward("successEdit");
		}
		return forward;
	}

	@Override
	protected void loadCollections(GastoForm form) {
		super.loadCollections(form);
		form.setPersonas(personaService.findAll());
		form.setProyectos(proyectoService.findAll());
		if (form.getActividades() == null) {
			form.setActividades(new ArrayList<Actividad>());
		}

		if (Utils.isNullOrEmpty(form.getIdPersona())) {
			form.setIdPersona(findPersona().getId().toString());
		}
	}

	@Override
	protected void postLoadEntity(Gasto entity, GastoForm viewForm) {
		if (entity.getActividad() != null) {
			viewForm.setActividades(actividadService.findAllByProyecto(entity.getActividad().getProyecto()));
		}
		viewForm.setIndividual(service.isIndividual(entity));
		viewForm.setAgrupado(service.isAgrupado(entity));
	}

	@Override
	protected ActionErrors validate(GastoForm form) {
		ActionErrors errors = new ActionErrors();
		Actividad actividad = actividadService.findById(Utils.parseInteger(form.getIdActividad()));
		if (proyectoService.isIndividual(actividad.getProyecto())) {
			if (Utils.isNullOrEmpty(form.getIdProveedor())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.proveedor.label")));
			}
			if (Utils.isNullOrEmpty(form.getIdTipoComprobante())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.tipoComprobante.label")));
			}
			if (Utils.isNullOrEmpty(form.getNumeroComprobante())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.numeroComprobante.label")));
			}
		}
		if (proyectoService.isAgrupado(actividad.getProyecto())) {
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
				forward = mapping.findForward("success");
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
		
		String id =(String) request.getParameter("idProyecto");
		Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
		Collection<Actividad> lista = actividadService.findAllByProyecto(proyecto);
		((GastoForm)form).setActividades(lista);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Actividad actividad : lista) {
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
				forward = mapping.findForward("success");
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
		filter.setPersona(findPersona());
		Collection<Gasto> result = service.findByFilter(filter);
		gastoForm.setResult(result);
		return mapping.findForward("query");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GastoForm gastoForm = (GastoForm)form;
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		filter.setPersona(findPersona());
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

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

}
