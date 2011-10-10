package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAsignacion;

/**
 * Representacion en la capa de vista de la entidad de modelo Asignacion.
 *
 * @version 1.0.0 Created 12/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class AsignacionForm extends AbstractForm<Asignacion> {

	private String cantidad;
	private String idTipoAsignacion;
	private String idProyecto;
	private String idActividad;
	private String idPersona;
	private Collection<TipoAsignacion> tiposAsignacion;
	private Collection<Proyecto> proyectos;
	private Collection<Actividad> actividades;
	private Collection<Persona> personas;

	private String filtroIdProyecto;
	private String filtroIdActividad;
	private String filtroIdPersona;

	private String labelTipoAsignacion;
	private String labelActividad;
	private String labelPersona;

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdTipoAsignacion() {
		return idTipoAsignacion;
	}

	public void setIdTipoAsignacion(String idTipoAsignacion) {
		this.idTipoAsignacion = idTipoAsignacion;
	}

	public String getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public Collection<TipoAsignacion> getTiposAsignacion() {
		return tiposAsignacion;
	}

	public void setTiposAsignacion(Collection<TipoAsignacion> tiposAsignacion) {
		this.tiposAsignacion = tiposAsignacion;
	}

	public Collection<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Collection<Actividad> actividades) {
		this.actividades = actividades;
	}

	public Collection<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Collection<Persona> personas) {
		this.personas = personas;
	}

	public String getFiltroIdActividad() {
		return filtroIdActividad;
	}

	public void setFiltroIdActividad(String filtroIdActividad) {
		this.filtroIdActividad = filtroIdActividad;
	}

	public String getFiltroIdPersona() {
		return filtroIdPersona;
	}

	public void setFiltroIdPersona(String filtroIdPersona) {
		this.filtroIdPersona = filtroIdPersona;
	}

	public String getLabelTipoAsignacion() {
		return labelTipoAsignacion;
	}

	public void setLabelTipoAsignacion(String labelTipoAsignacion) {
		this.labelTipoAsignacion = labelTipoAsignacion;
	}

	public String getLabelActividad() {
		return labelActividad;
	}

	public void setLabelActividad(String labelActividad) {
		this.labelActividad = labelActividad;
	}

	public String getLabelPersona() {
		return labelPersona;
	}

	public void setLabelPersona(String labelPersona) {
		this.labelPersona = labelPersona;
	}

	public String getFiltroIdProyecto() {
		return filtroIdProyecto;
	}

	public void setFiltroIdProyecto(String filtroIdProyecto) {
		this.filtroIdProyecto = filtroIdProyecto;
	}

	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.cantidad = "";
		this.idTipoAsignacion = "";
		this.idProyecto = "";
		this.idActividad = "";
		this.idPersona = "";
		this.filtroIdProyecto = "";
		this.filtroIdActividad = "";
		this.filtroIdPersona = "";
		this.labelTipoAsignacion = "";
		this.labelActividad = "";
		this.labelPersona = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.cantidad = "";
		this.idTipoAsignacion = "";
		this.idProyecto = "";
		this.idActividad = "";
		this.idPersona = "";
		this.labelTipoAsignacion = "";
		this.labelActividad = "";
		this.labelPersona = "";
	}

	@Override
	public void initializeForm(Asignacion entity) {
		this.id = entity.getId();
		this.cantidad = entity.getCantidad().toString();
		this.idTipoAsignacion = entity.getTipoAsignacion().getId().toString();
		this.idProyecto = "";
		this.idActividad = entity.getActividad().getId().toString();
		this.idPersona = entity.getPersona().getId().toString();
		this.labelTipoAsignacion = entity.getTipoAsignacion().getDescripcion();
		this.labelActividad = entity.getActividad().getNombre();
		this.labelPersona = entity.getPersona().getNombreYApellido();
	}

}
