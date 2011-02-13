package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Proveedor extends BusinessObject {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idTipoProveedor"))
	private TipoProveedor tipoProveedor;

	private String nombre;
	private String cuit;
	private String cbu;
	private String direccion;
	private String telefono;
	private String contacto;
	private String celular;
	private String email;
	private String observaciones;

	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
