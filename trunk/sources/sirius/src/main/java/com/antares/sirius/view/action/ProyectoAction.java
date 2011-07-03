package com.antares.sirius.view.action;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.antares.commons.exception.RestrictedAccessException;
import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ProyectoFilter;
import com.antares.sirius.model.Archivo;
import com.antares.sirius.model.AreaTematica;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAgrupamiento;
import com.antares.sirius.service.AreaTematicaService;
import com.antares.sirius.service.EstadoProyectoService;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.TipoAgrupamientoService;
import com.antares.sirius.view.form.ProyectoForm;

public class ProyectoAction extends BaseAction<Proyecto, ProyectoForm, ProyectoService> {
	
	private PersonaService personaService;
	private FinanciadorService financiadorService;
	private AreaTematicaService areaTematicaService;
	private EstadoProyectoService estadoProyectoService;
	private TipoAgrupamientoService tipoAgrupamientoService;

	@Override
	public ProyectoFilter createFilter(ProyectoForm form) {
		ProyectoFilter filter = new ProyectoFilter();
		filter.setNombre(form.getFiltroNombre());
		filter.setFechaInicio(Utils.parseDate(form.getFiltroFechaInicio()));
		filter.setFechaFin(Utils.parseDate(form.getFiltroFechaFin()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdFinanciador())) {
			filter.setFinanciador(financiadorService.findById(Integer.parseInt(form.getFiltroIdFinanciador())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdResponsable())) {
			filter.setResponsable(personaService.findById(Integer.parseInt(form.getFiltroIdResponsable())));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdCoordinador())) {
			filter.setCoordinador(personaService.findById(Integer.parseInt(form.getFiltroIdCoordinador())));
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
		entity.setPresupuestoTotal(Utils.parseDouble(form.getPresupuestoTotal()));

		Financiador financiador = financiadorService.findById(Integer.parseInt(form.getIdFinanciador()));
		entity.setFinanciador(financiador);
		Persona responsable = personaService.findById(Integer.parseInt(form.getIdResponsable()));
		entity.setResponsable(responsable);
		TipoAgrupamiento tipoAgrupamiento = tipoAgrupamientoService.findById(Integer.parseInt(form.getIdTipoAgrupamiento()));
		entity.setTipoAgrupamiento(tipoAgrupamiento);
		updateCoordinadores(entity, form.getIdCoordinadores());
		updateAreasTematicas(entity, form.getIdAreaTematica());
		updateArchivo(entity, form.getArchivo());

		if (entity.getEstadoProyecto() == null) {
			entity.setEstadoProyecto(estadoProyectoService.findDefault());
		}
	}

	private void updateCoordinadores(Proyecto entity, Integer[] coordinadores) {
		if (entity.getCoordinadores() == null) {
			entity.setCoordinadores(new HashSet<Persona>());
		} else {
			entity.getCoordinadores().clear();
		}
		if (coordinadores != null) {
			for (Integer idCoordinador : coordinadores) {
				entity.getCoordinadores().add(personaService.findById(idCoordinador));
			}
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

	private void updateArchivo(Proyecto entity, FormFile formFile) {
		if (formFile != null) {
			Blob contenido = Utils.createBlob(formFile);
			if (contenido != null) {
				if (entity.getArchivo() == null) {
					entity.setArchivo(new Archivo());
				}
				entity.getArchivo().setHash(Utils.encode(new Date().getTime() + "-" + formFile.getFileName()));
				entity.getArchivo().setNombre(formFile.getFileName());
				entity.getArchivo().setContenido(contenido);
			}
		}
	}

	@Override
	protected void loadCollections(ProyectoForm form) {
		form.setResponsables(personaService.findAll());
		form.setCoordinadores(personaService.findAll());
		form.setFinanciadores(financiadorService.findAll());
		form.setAreasTematicas(areaTematicaService.findAll());
		form.setTiposAgrupamiento(tipoAgrupamientoService.findAll());
	}

	@Override
	protected void completeCollections(Proyecto entity, ProyectoForm form) {
		if (!entity.getFinanciador().isActivo()) {
			form.getFinanciadores().add(entity.getFinanciador());
		}
		if (!entity.getResponsable().isActivo()) {
			form.getResponsables().add(entity.getResponsable());
		}
		for (Persona persona : entity.getCoordinadores()) {
			if (!persona.isActivo()) {
				form.getCoordinadores().add(persona);
			}
		}
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
		ActionForward forward;
		try {
			if (Utils.isNotNullNorEmpty(strId) && Utils.isNotNullNorEmpty(strIdEstado)) {
				Proyecto proyecto = service.findById(Integer.parseInt(strId));
				Integer idEstado = new Integer(strIdEstado);
					service.saveCambioEstado(proyecto, idEstado);
			}
			forward = query(mapping, form, request, response);
		} catch (RestrictedAccessException e) {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
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

	public void setTipoAgrupamientoService(TipoAgrupamientoService tipoAgrupamientoService) {
		this.tipoAgrupamientoService = tipoAgrupamientoService;
	}

}
