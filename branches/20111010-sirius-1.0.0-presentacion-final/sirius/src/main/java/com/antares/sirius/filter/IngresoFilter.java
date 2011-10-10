package com.antares.sirius.filter;

import java.util.Date;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Ingreso;
import com.antares.sirius.model.TipoIngreso;

/**
 * Filter para la endidad Ingreso.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class IngresoFilter extends Filter<Ingreso> {

	private Date fechaDesde;
	private Date fechaHasta;
	private TipoIngreso tipoIngreso;

	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public TipoIngreso getTipoIngreso() {
		return tipoIngreso;
	}
	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

}
