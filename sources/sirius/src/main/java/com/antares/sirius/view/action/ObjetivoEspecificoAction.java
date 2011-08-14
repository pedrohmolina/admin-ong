package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.UPDATE;

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
import com.antares.sirius.filter.ObjetivoEspecificoFilter;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.ObjetivoEspecificoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.ObjetivoEspecificoForm;

public class ObjetivoEspecificoAction extends BaseAction<ObjetivoEspecifico, ObjetivoEspecificoForm, ObjetivoEspecificoService> {
	
	private ProyectoService proyectoService;
	private ObjetivoGeneralService objetivoGeneralService;
	private GastoService gastoService;

	@Override
	public ObjetivoEspecificoFilter createFilter(ObjetivoEspecificoForm form) {
		ObjetivoEspecificoFilter filter = new ObjetivoEspecificoFilter();
		filter.setNombre(form.getFiltroNombre());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdObjetivoGeneral())) {
			filter.setObjetivoGeneral(objetivoGeneralService.findById(Utils.parseInteger(form.getFiltroIdObjetivoGeneral())));
		}
		return filter;
	}

	@Override
	public void updateEntity(ObjetivoEspecifico entity, ObjetivoEspecificoForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		entity.setPonderacion(Utils.parseInteger(form.getPonderacion()));
		if (Utils.isNotNullNorEmpty(form.getIdObjetivoGeneral())) {
			entity.setObjetivoGeneral(objetivoGeneralService.findById(Utils.parseInteger(form.getIdObjetivoGeneral())));
		}
	}

	@Override
	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		ObjetivoEspecifico entity = service.findById(id);
		if (entity != null && entity.isActivo() && proyectoService.isFinalizado(entity.getProyecto())) {
			forward = sendMessage(request, mapping, "errors.ProyectoFinalizado", "/objetivo-especifico/objetivo-especifico-query.do?method=lastQuery");
		} else {
			forward = super.initUpdate(mapping, form, request, response);
		}
		
		return forward;
	}

	@Override
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		ObjetivoEspecifico entity = service.findById(id);
		if (entity != null) {
			if (proyectoService.isFinalizado(entity.getProyecto())) {
				forward = sendMessage(request, mapping, "errors.ProyectoFinalizado", "/objetivo-especifico/objetivo-especifico-query.do?method=lastQuery");
			} else if (gastoService.existenGastosObjetivoEspecifico(entity)) {
				forward = sendMessage(request, mapping, "errors.existenGastos", "/objetivo-especifico/objetivo-especifico-query.do?method=lastQuery");
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
	protected void loadCollections(ObjetivoEspecificoForm form) {
		form.setObjetivosGenerales(objetivoGeneralService.findAll());
	}

	@Override
	protected ActionErrors validate(ObjetivoEspecificoForm form) {
		ActionErrors errors = new ActionErrors();
		ObjetivoGeneral objetivoGeneral = objetivoGeneralService.findById(Utils.parseInteger(form.getIdObjetivoGeneral()));
		Integer ponderacionTotal = objetivoGeneral.ponderacionTotal(form.getId());
		Integer nuevaPonderacion = Utils.parseInteger(form.getPonderacion());
		CustomValidationRoutines.validatePonderacion(ponderacionTotal, nuevaPonderacion, errors, "sirius.objetivoEspecifico.objetivoGeneral.label");
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.objetivoEspecifico.nombre.label")));
		}
		if (UPDATE.equals(form.getAction())) {
			ObjetivoEspecifico entity = service.findById(form.getId());
			if (entity != null && proyectoService.isFinalizado(entity.getProyecto())) {
				errors.add("error", new ActionMessage("errors.ProyectoFinalizado"));
			}
		}
		return errors;
	}

	public void setObjetivoGeneralService(ObjetivoGeneralService objetivoGeneralService) {
		this.objetivoGeneralService = objetivoGeneralService;
	}

	public void setGastoService(GastoService gastoService) {
		this.gastoService = gastoService;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
