package com.antares.sirius.view.action;

import static com.antares.sirius.base.Constants.ACCESO_CONFIRMAR_GASTOS_ACTIVIDAD;
import static com.antares.sirius.base.Constants.ACCESO_CONFIRMAR_GASTOS_PROYECTO;
import static java.lang.Boolean.TRUE;

import java.util.ArrayList;

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
		GastoForm viewForm = (GastoForm)form;
		viewForm.setResult(new ArrayList<Gasto>());
		if (usuarioService.userHasAccess(ACCESO_CONFIRMAR_GASTOS_ACTIVIDAD)) {
			viewForm.getResult().addAll(service.findByFilter(createFilterGastosActividad(viewForm)));
		}
		if (usuarioService.userHasAccess(ACCESO_CONFIRMAR_GASTOS_PROYECTO)) {
			viewForm.getResult().addAll(service.findByFilter(createFilterGastosProyecto(viewForm)));
		}

		for (Gasto gasto : viewForm.getResult()) {
			if (viewForm.getIdGastos().contains(gasto.getId())) {
				gasto.setConfirmado(TRUE);
			}
		}

		loadCollections(viewForm);
		return mapping.findForward("query");
	}

	@Override
	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setConfirmado(Boolean.FALSE);
		return filter;
	}

	protected Filter<Gasto> createFilterGastosActividad(GastoForm form) {
		GastoFilter filter = (GastoFilter)createFilter(form);
		filter.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyectoActividad(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		return filter;
	}

	protected Filter<Gasto> createFilterGastosProyecto(GastoForm form) {
		GastoFilter filter = (GastoFilter)createFilter(form);
		filter.setTipoGasto(tipoGastoService.findTipoGastoProyecto());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyecto(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
	}

	@Override
	protected void loadCollections(GastoForm form) {
		form.setProyectos(proyectoService.findAllNoFinalizados());
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
				if ((gasto.getActividad() != null && usuarioService.userHasAccess(ACCESO_CONFIRMAR_GASTOS_ACTIVIDAD)) 
						|| (gasto.getProveedor() != null && usuarioService.userHasAccess(ACCESO_CONFIRMAR_GASTOS_PROYECTO))) {
					service.ejecutarConfirmacion(gasto);
				}
			}
		}
		return query(mapping, form, request, response);
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

}
