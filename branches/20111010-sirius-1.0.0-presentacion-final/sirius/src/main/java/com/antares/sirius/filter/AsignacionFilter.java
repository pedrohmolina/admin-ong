package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAsignacion;

/**
 * Filter para la endidad Actividad.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class AsignacionFilter extends Filter<Asignacion> {

	private Proyecto proyecto;
	private Actividad actividad;
	private Persona persona;
	private TipoAsignacion tipoAsignacion;

	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public TipoAsignacion getTipoAsignacion() {
		return tipoAsignacion;
	}
	public void setTipoAsignacion(TipoAsignacion tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
