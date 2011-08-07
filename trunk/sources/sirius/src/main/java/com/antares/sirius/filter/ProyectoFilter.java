package com.antares.sirius.filter;

import java.util.Collection;
import java.util.Date;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Financiador;
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
	private Date fechaInicioDesde;
	private Date fechaInicioHasta;
	private EstadoProyecto estadoProyecto;
	private Financiador financiador;
	private Collection<Integer> idsAreasTematicas;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Financiador getFinanciador() {
		return financiador;
	}
	public void setFinanciador(Financiador financiador) {
		this.financiador = financiador;
	}
	public Collection<Integer> getIdsAreasTematicas() {
		return idsAreasTematicas;
	}
	public void setIdsAreasTematicas(Collection<Integer> idsAreasTematicas) {
		this.idsAreasTematicas = idsAreasTematicas;
	}
	public EstadoProyecto getEstadoProyecto() {
		return estadoProyecto;
	}
	public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}
	public Date getFechaInicioDesde() {
		return fechaInicioDesde;
	}
	public void setFechaInicioDesde(Date fechaInicioDesde) {
		this.fechaInicioDesde = fechaInicioDesde;
	}
	public Date getFechaInicioHasta() {
		return fechaInicioHasta;
	}
	public void setFechaInicioHasta(Date fechaInicioHasta) {
		this.fechaInicioHasta = fechaInicioHasta;
	}
	
}
