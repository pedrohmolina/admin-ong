package com.antares.sirius.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;



@Entity
@SuppressWarnings("serial")
public class Gasto extends BusinessObject {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idRubro"))
	private Rubro rubro;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idOrigen"))
	private Origen origen;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idProveedor"))
	private Proveedor proveedor;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idTipoComprobante"))
	private TipoComprobante tipoComprobante;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idPersona"))
	private Persona persona;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idProyecto"))
    private Proyecto proyecto;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idActividad"))
	private Actividad actividad;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idTipoGasto"))
	private TipoGasto tipoGasto;

	private Date fecha;
	private String numeroComprobante;
	private String paquete;
	private String observaciones;
	private Double importe;
	private String referencia;
	private Boolean confirmado = Boolean.FALSE;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

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

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
