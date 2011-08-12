package com.antares.sirius.view.action;

import static java.lang.Boolean.TRUE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.GastoForm;

public class VerificarGastoAction extends GastoAction {

	protected ProyectoService proyectoService;

	@Override
	public ActionForward initQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.initQuery(mapping, form, request, response);
		return query(mapping, form, request, response);
	}

	@Override
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = super.query(mapping, form, request, response);
		GastoForm viewForm = (GastoForm)form;
		for (Gasto gasto : viewForm.getResult()) {
			if (viewForm.getIdGastos().contains(gasto.getId())) {
				gasto.setConfirmado(TRUE);
			}
		}
		return forward;
	}

	@Override
	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyectoActividad(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		filter.setConfirmado(Boolean.FALSE);
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
	}

	@Override
	protected void loadCollections(GastoForm form) {
		form.setProyectos(proyectoService.findAll()); // TODO en realidad deberian ser los que estan en curso
	}

	public ActionForward confirmar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = new Integer(request.getParameter("id"));
		GastoForm viewForm = (GastoForm)form;
		viewForm.getIdGastos().add(id);
		return query(mapping, form, request, response);
	}

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GastoForm viewForm = (GastoForm)form;
		for (Integer id : viewForm.getIdGastos()) {
			Gasto gasto = service.findById(id);
			if (gasto != null) {
				service.ejecutarConfirmacion(gasto);
			}
		}
		return query(mapping, form, request, response);
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
