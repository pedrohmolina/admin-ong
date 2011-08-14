package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.UPDATE;

import java.util.Date;
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
import com.antares.commons.util.Utils;
import com.antares.commons.validation.CustomValidationRoutines;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ActividadFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Meta;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.EstadoActividadService;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.MetaService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.ActividadForm;

public class ActividadAction extends BaseAction<Actividad, ActividadForm, ActividadService> {

	private ProyectoService proyectoService;
	private MetaService metaService;
	private FinanciadorService financiadorService;
	private EstadoActividadService estadoActividadService;
	private GastoService gastoService;

	@Override
	public ActividadFilter createFilter(ActividadForm form) {
		ActividadFilter filter = new ActividadFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdMeta())) {
			filter.setMeta(metaService.findById(Utils.parseInteger(form.getFiltroIdMeta())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdEstadoActividad())) {
			filter.setEstadoActividad(estadoActividadService.findById(Utils.parseInteger(form.getFiltroIdEstadoActividad())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Actividad entity, ActividadForm form) {
		entity.setNombre(form.getNombre());
		entity.setFechaInicio(Utils.parseDate(form.getFechaInicio()));
		entity.setFechaFin(Utils.parseDate(form.getFechaFin()));
		entity.setObservaciones(form.getObservaciones());
		entity.setPonderacion(Utils.parseInteger(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdMeta())) {
			entity.setMeta(metaService.findById(Utils.parseInteger(form.getIdMeta())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdFinanciador())) {
			entity.setFinanciador(financiadorService.findById(Utils.parseInteger(form.getIdFinanciador())));
		}

		if (entity.getEstadoActividad() == null) {
			entity.setEstadoActividad(estadoActividadService.findDefault());
			entity.setCompletitud(0D);
		} else if (service.isActualizarCompletitud(entity)) {
			entity.setCompletitud(Utils.parseDouble(form.getCompletitud()));
		}
	}

	@Override
	protected void postLoadEntity(Actividad entity, ActividadForm viewForm) {
		viewForm.setActualizarCompletitud(service.isActualizarCompletitud(entity));
	}

	@Override
	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		Actividad entity = service.findById(id);
		if (entity != null && entity.isActivo() && proyectoService.isFinalizado(entity.getProyecto())) {
			forward = sendMessage(request, mapping, "errors.ProyectoFinalizado", "/actividad/actividad-query.do?method=lastQuery");
		} else {
			forward = super.initUpdate(mapping, form, request, response);
		}
		
		return forward;
	}

	@Override
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		Actividad entity = service.findById(id);
		if (entity != null) {
			if (proyectoService.isFinalizado(entity.getProyecto())) {
				forward = sendMessage(request, mapping, "errors.ProyectoFinalizado", "/actividad/actividad-query.do?method=lastQuery");
			} else if (gastoService.existenGastosActividad(entity)) {
				forward = sendMessage(request, mapping, "errors.existenGastos", "/actividad/actividad-query.do?method=lastQuery");
			} else {
				service.delete(entity);
				forward = query(mapping, form, request, response);
			}
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	@Override
	protected void loadCollections(ActividadForm form) {
		form.setMetas(metaService.findAll());
		form.setFinanciadores(financiadorService.findAll());
		form.setEstadosActividad(estadoActividadService.findAll());
	}

	@Override
	protected void completeCollections(Actividad entity, ActividadForm form) {
		if (!entity.getFinanciador().isActivo()) {
			form.getFinanciadores().add(entity.getFinanciador());
		}
	}

	@Override
	protected ActionErrors validate(ActividadForm form) {
		ActionErrors errors = new ActionErrors();
		Meta meta = metaService.findById(Utils.parseInteger(form.getIdMeta()));
		Integer ponderacionTotal = meta.ponderacionTotal(form.getId());
		Integer nuevaPonderacion = Utils.parseInteger(form.getPonderacion());
		CustomValidationRoutines.validatePonderacion(ponderacionTotal, nuevaPonderacion, errors, "sirius.actividad.meta.label");
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.actividad.nombre.label")));
		}
		if (Utils.isNotNullNorEmpty(form.getFechaInicio()) && Utils.isNotNullNorEmpty(form.getFechaFin())) {
			Date fechaInicio = Utils.parseDate(form.getFechaInicio());
			Date fechaFin = Utils.parseDate(form.getFechaFin());
			if (fechaInicio.after(fechaFin)) {
				errors.add("error", new ActionMessage("errors.date.menor", new Object[]{
						Utils.getMessage("sirius.actividad.fechaInicio.label"),
						Utils.getMessage("sirius.actividad.fechaFin.label")
				}));
			}
		}
		if (UPDATE.equals(form.getAction())) {
			Actividad entity = service.findById(form.getId());
			if (entity != null && proyectoService.isFinalizado(entity.getProyecto())) {
				errors.add("error", new ActionMessage("errors.ProyectoFinalizado"));
			}
		}
		return errors;
	}

	public ActionForward obtenerFinanciador(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idMeta =(String) request.getParameter("idMeta");
		Meta meta = metaService.findById(Utils.parseInteger(idMeta));
		Financiador financiador = meta.getProyecto().getFinanciador();
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (financiador != null) {
			map.put("idFinanciador", financiador.getId().toString());
		}
		sendJSON(response, map);
		
		return null;
	}
		
	public ActionForward cambiarEstado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		String strId = request.getParameter("id");
		String strIdEstado = request.getParameter("idEstado");
		try {
			if (Utils.isNotNullNorEmpty(strId) && Utils.isNotNullNorEmpty(strIdEstado)) {
				Actividad actividad = service.findById(Utils.parseInteger(strId));
				if (actividad != null) {
					Integer idEstado = new Integer(strIdEstado);
					service.saveCambioEstado(actividad, idEstado);
				} else {
					forward = mapping.findForward("restrictedAccess"); 
				}
			}
			forward = query(mapping, form, request, response);
		} catch (RestrictedAccessException e) {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}

	public void setFinanciadorService(FinanciadorService financiadorService) {
		this.financiadorService = financiadorService;
	}

	public void setEstadoActividadService(EstadoActividadService estadoActividadService) {
		this.estadoActividadService = estadoActividadService;
	}

	public void setGastoService(GastoService gastoService) {
		this.gastoService = gastoService;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
