package com.antares.sirius.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@SuppressWarnings("serial")
public class Ingreso extends BusinessObject {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idTipoIngreso"))
	private TipoIngreso tipoIngreso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idFinanciador"))
	private Financiador financiador;

	private Double monto;
	private Date fecha;
	private String descripcion;

	public TipoIngreso getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Financiador getFinanciador() {
		return financiador;
	}

	public void setFinanciador(Financiador financiador) {
		this.financiador = financiador;
	}

}
