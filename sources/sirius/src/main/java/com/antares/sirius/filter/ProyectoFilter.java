package com.antares.sirius.filter;

import java.util.Date;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.AreaTematica;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;

/**
 * Filter para la endidad Proyecto.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class ProyectoFilter extends Filter<Proyecto> {

	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private Persona responsable;
	private Financiador financiador;
	private AreaTematica areaTematica;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Persona getResponsable() {
		return responsable;
	}
	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}
	public Financiador getFinanciador() {
		return financiador;
	}
	public void setFinanciador(Financiador financiador) {
		this.financiador = financiador;
	}
	public AreaTematica getAreaTematica() {
		return areaTematica;
	}
	public void setAreaTematica(AreaTematica areaTematica) {
		this.areaTematica = areaTematica;
	}
	
}
