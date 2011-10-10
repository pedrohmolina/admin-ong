package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.EstadoActividad;
import com.antares.sirius.model.Meta;

/**
 * Filter para la endidad Actividad.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class ActividadFilter extends Filter<Actividad> {

	private Meta meta;
	private String nombre;
	private EstadoActividad estadoActividad;

	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EstadoActividad getEstadoActividad() {
		return estadoActividad;
	}
	public void setEstadoActividad(EstadoActividad estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

}
