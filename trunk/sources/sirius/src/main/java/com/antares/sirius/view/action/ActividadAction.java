package com.antares.sirius.view.action;

import java.util.Date;
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
import com.antares.sirius.filter.ActividadFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Meta;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.EstadoActividadService;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.MetaService;
import com.antares.sirius.view.form.ActividadForm;

public class ActividadAction extends BaseAction<Actividad, ActividadForm, ActividadService> {

	private MetaService metaService;
	private FinanciadorService financiadorService;
	private EstadoActividadService estadoActividadService;

	@Override
	public ActividadFilter createFilter(ActividadForm form) {
		ActividadFilter filter = new ActividadFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdMeta())) {
			filter.setMeta(metaService.findById(Integer.parseInt(form.getFiltroIdMeta())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Actividad entity, ActividadForm form) {
		entity.setNombre(form.getNombre());
		entity.setFechaInicio(Utils.parseDate(form.getFechaInicio()));
		entity.setFechaFin(Utils.parseDate(form.getFechaFin()));
		entity.setObservaciones(form.getObservaciones());
		entity.setPresupuesto(Utils.parseDouble(form.getPresupuesto()));
		entity.setPonderacion(Integer.parseInt(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdMeta())) {
			entity.setMeta(metaService.findById(Integer.parseInt(form.getIdMeta())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdFinanciador())) {
			entity.setFinanciador(financiadorService.findById(Integer.parseInt(form.getIdFinanciador())));
		}

		if (entity.getEstadoActividad() == null) {
			entity.setEstadoActividad(estadoActividadService.findDefault());
			entity.setCompletitud(0D);
		} else if (service.isActualizarCompletitud(entity)) {
			entity.setCompletitud(form.getCompletitud().doubleValue());
		}
	}

	@Override
	protected void postLoadEntity(Actividad entity, ActividadForm viewForm) {
		viewForm.setActualizarCompletitud(service.isActualizarCompletitud(entity));
	}

	@Override
	protected void loadCollections(ActividadForm form) {
		form.setMetas(metaService.findAll());
		form.setFinanciadores(financiadorService.findAll());
	}

	@Override
	protected ActionErrors validate(ActividadForm form) {
		ActionErrors errors = new ActionErrors();
		Meta meta = metaService.findById(Integer.parseInt(form.getIdMeta()));
		if (Utils.excedePonderacion(Integer.parseInt(form.getPonderacion()), meta.getActividades(), form.getId())) {
			errors.add("error", new ActionMessage("errors.ponderiacion", Utils.getMessage("sirius.actividad.meta.label")));
		}
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
		/*
		 * TODO faltaria validar que la suma de los presupuestos de las actividades de la misma meta 
		 * no excedan el presupuesto asignado a dicha meta y que dicho presupuesto sea mayor a cero. 
		 */
		return errors;
	}

	public ActionForward obtenerFinanciador(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idMeta =(String) request.getParameter("idMeta");
		Meta meta = metaService.findById(Integer.parseInt(idMeta));
		Financiador financiador = meta.getObjetivoEspecifico().getObjetivoGeneral().getProyecto().getFinanciador();
		Map<String, String> map = new HashMap<String, String>();
		if (financiador != null) {
			map.put("idFinanciador", financiador.getId().toString());
		}
		sendJSON(response, map);
		
		return null;
	}
		
	public ActionForward cambiarEstado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strId = request.getParameter("id");
		String strIdEstado = request.getParameter("idEstado");
		if (Utils.isNotNullNorEmpty(strId) && Utils.isNotNullNorEmpty(strIdEstado)) {
			Actividad actividad = service.findById(Integer.parseInt(strId));
			Integer idEstado = new Integer(strIdEstado);
			service.saveCambioEstado(actividad, idEstado);
		}
		return query(mapping, form, request, response);
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

}
