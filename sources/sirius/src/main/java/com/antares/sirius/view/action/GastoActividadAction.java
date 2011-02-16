package com.antares.sirius.view.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
			filter.setProyectoActividad(proyectoService.findById(Integer.parseInt(form.getFiltroIdProyecto())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdActividad())) {
			filter.setActividad(actividadService.findById(Integer.parseInt(form.getFiltroIdActividad())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdPersona())) {
			filter.setPersona(personaService.findById(Integer.parseInt(form.getFiltroIdPersona())));
		}
		filter.setFecha(Utils.parseDate(form.getFiltroFecha()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRubro())) {
			filter.setRubro(rubroService.findById(Integer.parseInt(form.getFiltroIdRubro())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdOrigen())) {
			filter.setOrigen(origenService.findById(Integer.parseInt(form.getFiltroIdOrigen())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProveedor())) {
			filter.setProveedor(proveedorService.findById(Integer.parseInt(form.getFiltroIdProveedor())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
		entity.setTipoGasto(tipoGastoService.findTipoGastoActividad());
		super.updateEntity(entity, form);
		entity.setActividad(actividadService.findById(Integer.parseInt(form.getIdActividad())));
		entity.setPaquete(form.getPaquete());
	}

	@Override
	protected void postLoadEntity(Gasto entity, GastoForm viewForm) {
		if (entity.getActividad() != null) {
			viewForm.setActividades(actividadService.findAllByProyecto(entity.getActividad().getProyecto()));
		}
	}

	@Override
	protected void loadCollections(GastoForm form) {
		super.loadCollections(form);
		form.setPersonas(personaService.findAll());
		form.setProyectos(proyectoService.findAll());
		if (form.getActividades() == null) {
			form.setActividades(new ArrayList<Actividad>());
		}
	}

	public ActionForward confirmar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = new Integer(request.getParameter("id"));
		Gasto entity = service.findById(id);
		service.ejecutarConfirmacion(entity);
		return mapping.findForward("success");
	}

	public ActionForward cargarComboActividad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String id =(String) request.getParameter("idProyecto");
		Proyecto proyecto = proyectoService.findById(Integer.parseInt(id));
		Collection<Actividad> lista = actividadService.findAllByProyecto(proyecto);
		((GastoForm)form).setActividades(lista);
		Map<String, String> map = new HashMap<String, String>();
		for (Actividad actividad : lista) {
			map.put(new Integer(actividad.getId()).toString(), actividad.getNombre());
		}
		
		sendJSON(response, map);
		return null;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

}
