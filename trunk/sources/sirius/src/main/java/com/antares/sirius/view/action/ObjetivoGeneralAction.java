package com.antares.sirius.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.validation.CustomValidationRoutines;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ObjetivoGeneralFilter;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.ObjetivoGeneralForm;

public class ObjetivoGeneralAction extends BaseAction<ObjetivoGeneral, ObjetivoGeneralForm, ObjetivoGeneralService> {

	private ProyectoService proyectoService;
	private GastoService gastoService;

	@Override
	public ObjetivoGeneralFilter createFilter(ObjetivoGeneralForm form) {
		ObjetivoGeneralFilter filter = new ObjetivoGeneralFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyecto(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		return filter;
	}

	@Override
	public void updateEntity(ObjetivoGeneral entity, ObjetivoGeneralForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		entity.setPonderacion(Utils.parseInteger(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdProyecto())) {
			entity.setProyecto(proyectoService.findById(Utils.parseInteger(form.getIdProyecto())));
		}
	}

	@Override
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		ObjetivoGeneral entity = service.findById(id);
		if (entity != null) {
			if (!gastoService.existenGastosObjetivoGeneral(entity)) {
				service.delete(entity);
				forward = query(mapping, form, request, response);
			} else {
				forward = sendMessage(request, mapping, "errors.existenGastos", "/objetivo-general/objetivo-general-query.do?method=lastQuery");
			}
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	@Override
	protected void loadCollections(ObjetivoGeneralForm form) {
		form.setProyectos(proyectoService.findAll());
	}

	@Override
	protected ActionErrors validate(ObjetivoGeneralForm form) {
		ActionErrors errors = new ActionErrors();
		Proyecto proyecto = proyectoService.findById(Utils.parseInteger(form.getIdProyecto()));
		Integer ponderacionTotal = proyecto.ponderacionTotal(form.getId());
		Integer nuevaPonderacion = Utils.parseInteger(form.getPonderacion());
		CustomValidationRoutines.validatePonderacion(ponderacionTotal, nuevaPonderacion, errors, "sirius.objetivoGeneral.proyecto.label");
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.objetivoGeneral.nombre.label")));
		}
		return errors;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setGastoService(GastoService gastoService) {
		this.gastoService = gastoService;
	}

}
