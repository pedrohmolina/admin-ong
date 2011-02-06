package com.antares.sirius.view.action;

import static com.antares.sirius.base.Constants.ID_ESTADO_PROYECTO_IDEA;

import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ProyectoFilter;
import com.antares.sirius.model.AreaTematica;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.AreaTematicaService;
import com.antares.sirius.service.EstadoProyectoService;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.ProyectoForm;

public class ProyectoAction extends BaseAction<Proyecto, ProyectoForm, ProyectoService> {
	
	private PersonaService personaService;
	private FinanciadorService financiadorService;
	private AreaTematicaService areaTematicaService;
	private EstadoProyectoService estadoProyectoService;

	@Override
	public ProyectoFilter createFilter(ProyectoForm form) {
		ProyectoFilter filter = new ProyectoFilter();
		filter.setNombre(form.getFiltroNombre());
		filter.setFechaInicio(Utils.parseDate(form.getFiltroFechaInicio()));
		filter.setFechaFin(Utils.parseDate(form.getFiltroFechaFin()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdFinanciador())) {
			filter.setFinanciador(financiadorService.findById(Integer.parseInt(form.getFiltroIdFinanciador())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdAreaTematica())) {
			filter.setAreaTematica(areaTematicaService.findById(Integer.parseInt(form.getFiltroIdAreaTematica())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Proyecto entity, ProyectoForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		entity.setFechaInicio(Utils.parseDate(form.getFechaInicio()));
		entity.setFechaFin(Utils.parseDate(form.getFechaFin()));
		entity.setUbicacion(form.getUbicacion());
		entity.setBeneficiariosDirectos(form.getBeneficiariosDirectos());
		entity.setBeneficiariosIndirectos(form.getBeneficiariosIndirectos());

		Financiador financiador = financiadorService.findById(Integer.parseInt(form.getIdFinanciador()));
		entity.setFinanciador(financiador);
		Persona responsable = personaService.findById(Integer.parseInt(form.getIdResponsable()));
		entity.setResponsable(responsable);
		updateCoordinadores(entity, form.getIdCoordinadores());
		updateAreasTematicas(entity, form.getIdCoordinadores());
		
		if (entity.getEstadoProyecto() == null) {
			entity.setEstadoProyecto(estadoProyectoService.findById(ID_ESTADO_PROYECTO_IDEA));
		}
	}

	private void updateCoordinadores(Proyecto entity, Integer[] coordinadores) {
		if (entity.getCoordinadores() == null) {
			entity.setCoordinadores(new HashSet<Persona>());
		} else {
			entity.getCoordinadores().clear();
		}
		for (Integer idCoordinador : coordinadores) {
			entity.getCoordinadores().add(personaService.findById(idCoordinador));
		}
	}

	private void updateAreasTematicas(Proyecto entity, Integer[] areasTematicas) {
		if (entity.getAreasTematicas() == null) {
			entity.setAreasTematicas(new HashSet<AreaTematica>());
		} else {
			entity.getAreasTematicas().clear();
		}
		for (Integer idAreaTematica : areasTematicas) {
			entity.getAreasTematicas().add(areaTematicaService.findById(idAreaTematica));
		}
	}

	@Override
	protected void loadCollections(ProyectoForm form) {
		form.setResponsables(personaService.findAll());
		form.setCoordinadores(personaService.findAll());
		form.setFinanciadores(financiadorService.findAll());
		form.setAreasTematicas(areaTematicaService.findAll());
	}

	@Override
	protected ActionErrors validate(ProyectoForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.proyecto.nombre.label")));
		}
		if (Utils.isNotNullNorEmpty(form.getFechaInicio()) && Utils.isNotNullNorEmpty(form.getFechaFin())) {
			Date fechaInicio = Utils.parseDate(form.getFechaInicio());
			Date fechaFin = Utils.parseDate(form.getFechaFin());
			if (fechaInicio.after(fechaFin)) {
				errors.add("error", new ActionMessage("errors.date.menor", new Object[]{
						Utils.getMessage("sirius.proyecto.fechaInicio.label"),
						Utils.getMessage("sirius.proyecto.fechaFin.label")
				}));
			}
		}
		return errors;
	}

	public ActionForward cambiarEstado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strId = request.getParameter("id");
		String strIdEstado = request.getParameter("idEstado");
		if (Utils.isNotNullNorEmpty(strId) && Utils.isNotNullNorEmpty(strIdEstado)) {
			Proyecto proyecto = service.findById(Integer.parseInt(strId));
			Integer idEstado = new Integer(strIdEstado);
			service.saveCambioEstado(proyecto, idEstado);
		}
		return query(mapping, form, request, response);
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setFinanciadorService(FinanciadorService financiadorService) {
		this.financiadorService = financiadorService;
	}

	public void setAreaTematicaService(AreaTematicaService areaTematicaService) {
		this.areaTematicaService = areaTematicaService;
	}

	public void setEstadoProyectoService(EstadoProyectoService estadoProyectoService) {
		this.estadoProyectoService = estadoProyectoService;
	}

}
