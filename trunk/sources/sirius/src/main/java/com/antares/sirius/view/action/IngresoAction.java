package com.antares.sirius.view.action;

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
import com.antares.sirius.filter.IngresoFilter;
import com.antares.sirius.model.Ingreso;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.IngresoService;
import com.antares.sirius.service.TipoIngresoService;
import com.antares.sirius.view.form.IngresoForm;

public class IngresoAction extends BaseAction<Ingreso, IngresoForm, IngresoService> {
	
	private TipoIngresoService tipoIngresoService;
	private FinanciadorService financiadorService;

	@Override
	public IngresoFilter createFilter(IngresoForm form) {
		IngresoFilter filter = new IngresoFilter();
		filter.setFecha(Utils.parseDate(form.getFiltroFecha()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdTipoIngreso())) {
			filter.setTipoIngreso(tipoIngresoService.findById(Integer.parseInt(form.getFiltroIdTipoIngreso())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Ingreso entity, IngresoForm form) {
		entity.setTipoIngreso(tipoIngresoService.findById(Integer.parseInt(form.getIdTipoIngreso())));
		entity.setMonto(Utils.parseDouble(form.getMonto()));
		entity.setFecha(Utils.parseDate(form.getFecha()));
		entity.setDescripcion(form.getDescripcion());
		if (tipoIngresoService.isIdFinanciacion(form.getIdTipoIngreso()) && Utils.isNotNullNorEmpty(form.getIdFinanciador())) {
			entity.setFinanciador(financiadorService.findById(Integer.parseInt(form.getIdFinanciador())));
		} else {
			entity.setFinanciador(null);
		}
	}

	@Override
	protected void loadCollections(IngresoForm form) {
		form.setTiposIngreso(tipoIngresoService.findAll());		
		form.setFinanciadores(financiadorService.findAll());		
	}

	@Override
	protected void completeCollections(Ingreso entity, IngresoForm form) {
		if (entity.getFinanciador() != null && !entity.getFinanciador().isActivo()) {
			form.getFinanciadores().add(entity.getFinanciador());
		}
	}

	@Override
	protected ActionErrors validate(IngresoForm form) {
		ActionErrors errors = new ActionErrors();
		if (tipoIngresoService.isIdFinanciacion(form.getIdTipoIngreso()) && Utils.isNullOrEmpty(form.getIdFinanciador())){
			errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.ingreso.financiador.label")));
		}
		return errors;
	}

	public ActionForward isFinanciacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idTipoIngreso =(String) request.getParameter("idTipoIngreso");
		Map<String, String> map = new HashMap<String, String>();
		map.put("isFinanciacion", tipoIngresoService.isIdFinanciacion(idTipoIngreso) ? "true" : "false");

		sendJSON(response, map);
		return null;
	}
	
	public void setTipoIngresoService(TipoIngresoService tipoIngresoService) {
		this.tipoIngresoService = tipoIngresoService;
	}

	public void setFinanciadorService(FinanciadorService financiadorService) {
		this.financiadorService = financiadorService;
	}

}
