package com.antares.sirius.filter;

import java.util.Date;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Origen;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.model.TipoGasto;

/**
 * Filter para la endidad Gasto.
 *
 * @version 1.0.0 Created 16/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class GastoFilter extends Filter<Gasto> {

	private TipoGasto tipoGasto;
	private Proyecto proyecto;
	private Proyecto proyectoActividad;
	private Actividad actividad;
	private Persona persona;
	private Rubro rubro;
	private Origen origen;
	private Proveedor proveedor;
	private Date fecha;
	private Date fechaDesde;
	private Date fechaHasta;

	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
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
	public Rubro getRubro() {
		return rubro;
	}
	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	public Origen getOrigen() {
		return origen;
	}
	public void setOrigen(Origen origen) {
		this.origen = origen;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}
	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}
	public Proyecto getProyectoActividad() {
		return proyectoActividad;
	}
	public void setProyectoActividad(Proyecto proyectoActividad) {
		this.proyectoActividad = proyectoActividad;
	}
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

}
