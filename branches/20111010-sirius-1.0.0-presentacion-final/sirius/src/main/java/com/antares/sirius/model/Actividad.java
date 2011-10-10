package com.antares.sirius.model;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Actividad extends Ponderable {

	@ManyToOne(fetch = LAZY)
	@JoinColumns(@JoinColumn(name = "idMeta"))
	private Meta meta;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idFinanciador"))
	private Financiador financiador;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idEstadoActividad"))
	private EstadoActividad estadoActividad;

	private String nombre;
	private String observaciones;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaFinalizacion;
	private Double completitud;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Financiador getFinanciador() {
		return financiador;
	}

	public void setFinanciador(Financiador financiador) {
		this.financiador = financiador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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

	public Double getCompletitud() {
		return completitud;
	}

	public void setCompletitud(Double completitud) {
		this.completitud = completitud;
	}

	public EstadoActividad getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(EstadoActividad estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Proyecto getProyecto() {
		return meta.getObjetivoEspecifico().getObjetivoGeneral().getProyecto();
	}

	@Override
	public Collection<? extends Ponderable> getPonderables() {
		return new ArrayList<Ponderable>();
	}

}
