package com.antares.sirius.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Persona extends BusinessObject {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idTipoDocumento"))
	private TipoDocumento tipoDocumento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idRelacionContractual"))
	private RelacionContractual relacionContractual;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idFormaPago"))
	private FormaPago formaPago;

	@Transient //TODO cambiar por @OneToOne cuando se mapee el Usuario
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(@JoinColumn(name = "idPersonaFactura"))
	private Persona personaFactura;

	private String apellido;
	private String nombre;
	private String segundoNombre;
	private Integer numeroDocumento;
	private String cuit;
	private String cbu;
	private String nacionalidad;
	private Date fechaNacimiento;
	private String profesion;
	private String direccion;
	private String telefono;
	private String celular;
	private String email;
	private String funcion;
	private String observaciones;

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
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

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public RelacionContractual getRelacionContractual() {
		return relacionContractual;
	}

	public void setRelacionContractual(RelacionContractual relacionContractual) {
		this.relacionContractual = relacionContractual;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Persona getPersonaFactura() {
		return personaFactura;
	}

	public void setPersonaFactura(Persona personaFactura) {
		this.personaFactura = personaFactura;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreYApellido() {
		return nombre + " " + apellido;
	}

}
