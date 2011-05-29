package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.sirius.model.FormatoReporte;
import com.antares.sirius.model.Persona;

/**
 * Formulario que contendra los filtros a partir de los cuales se generara el reporte de Personas.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
@SuppressWarnings("serial")
public class ReportePersonaForm extends ActionForm{

	private Collection<FormatoReporte> formatosReporte;
	private String formatoReporte;

	//Filtros 
	private String apellido;
	private String nombre;
	private String numeroDocumento;
	private String cuit;
	private String nacionalidad;
	
	//Lista previa de resultados
	private Collection<Persona> result;
	
	//Columnas a visualizar en el reporte
	private Boolean verNumeroDocumento;
	private Boolean verCuit;
	private Boolean verCBU;
	private Boolean verFechaNacimiento;
	private Boolean verProfesion;
	private Boolean verDireccion;
	private Boolean verTelefono;
	private Boolean verEmail;
	private Boolean verFuncion;
	private Boolean verRelacionContractual;
	
	
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public Collection<FormatoReporte> getFormatosReporte() {
		return formatosReporte;
	}

	public void setFormatosReporte(Collection<FormatoReporte> formatosReporte) {
		this.formatosReporte = formatosReporte;
	}

	
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

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void initialize() {
		this.apellido = "";
		this.nombre = "";
		this.numeroDocumento = "";
		this.cuit = "";
		this.nacionalidad = "";
		this.verNumeroDocumento = false;
		this.verCuit = false;
		this.verCBU = false;
		this.verFechaNacimiento = false;
		this.verProfesion = false;
		this.verDireccion = false;
		this.verTelefono = false;
		this.verEmail = false;
		this.verFuncion = false;
		this.verRelacionContractual = false;

	}

	public void initializeForm() {
		this.apellido = "";
		this.nombre = "";
		this.numeroDocumento = "";
		this.cuit = "";
		this.nacionalidad = "";
		this.verNumeroDocumento = false;
		this.verCuit = false;
		this.verCBU = false;
		this.verFechaNacimiento = false;
		this.verProfesion = false;
		this.verDireccion = false;
		this.verTelefono = false;
		this.verEmail = false;
		this.verFuncion = false;
		this.verRelacionContractual = false;

	}

	public void initializeForm(Persona entity) {
		this.apellido = entity.getApellido();
		this.nombre = entity.getNombre();
		this.numeroDocumento = entity.getNumeroDocumento().toString();
		this.cuit = entity.getCbu();
		this.nacionalidad = entity.getNacionalidad();
		}

	/**
	 * @return the verNumeroDocumento
	 */
	public Boolean getVerNumeroDocumento() {
		return verNumeroDocumento;
	}

	/**
	 * @param verNumeroDocumento the verNumeroDocumento to set
	 */
	public void setVerNumeroDocumento(Boolean verNumeroDocumento) {
		this.verNumeroDocumento = verNumeroDocumento;
	}

	/**
	 * @return the verCuit
	 */
	public Boolean getVerCuit() {
		return verCuit;
	}

	/**
	 * @param verCuit the verCuit to set
	 */
	public void setVerCuit(Boolean verCuit) {
		this.verCuit = verCuit;
	}

	/**
	 * @return the verCBU
	 */
	public Boolean getVerCBU() {
		return verCBU;
	}

	/**
	 * @param verCBU the verCBU to set
	 */
	public void setVerCBU(Boolean verCBU) {
		this.verCBU = verCBU;
	}

	/**
	 * @return the verFechaNacimiento
	 */
	public Boolean getVerFechaNacimiento() {
		return verFechaNacimiento;
	}

	/**
	 * @param verFechaNacimiento the verFechaNacimiento to set
	 */
	public void setVerFechaNacimiento(Boolean verFechaNacimiento) {
		this.verFechaNacimiento = verFechaNacimiento;
	}

	/**
	 * @return the verProfesion
	 */
	public Boolean getVerProfesion() {
		return verProfesion;
	}

	/**
	 * @param verProfesion the verProfesion to set
	 */
	public void setVerProfesion(Boolean verProfesion) {
		this.verProfesion = verProfesion;
	}

	/**
	 * @return the verDireccion
	 */
	public Boolean getVerDireccion() {
		return verDireccion;
	}

	/**
	 * @param verDireccion the verDireccion to set
	 */
	public void setVerDireccion(Boolean verDireccion) {
		this.verDireccion = verDireccion;
	}

	/**
	 * @return the verTelefono
	 */
	public Boolean getVerTelefono() {
		return verTelefono;
	}

	/**
	 * @param verTelefono the verTelefono to set
	 */
	public void setVerTelefono(Boolean verTelefono) {
		this.verTelefono = verTelefono;
	}

	/**
	 * @return the verEmail
	 */
	public Boolean getVerEmail() {
		return verEmail;
	}

	/**
	 * @param verEmail the verEmail to set
	 */
	public void setVerEmail(Boolean verEmail) {
		this.verEmail = verEmail;
	}

	/**
	 * @return the verFuncion
	 */
	public Boolean getVerFuncion() {
		return verFuncion;
	}

	/**
	 * @param verFuncion the verFuncion to set
	 */
	public void setVerFuncion(Boolean verFuncion) {
		this.verFuncion = verFuncion;
	}

	/**
	 * @return the verRelacionContractual
	 */
	public Boolean getVerRelacionContractual() {
		return verRelacionContractual;
	}

	/**
	 * @param verRelacionContractual the verRelacionContractual to set
	 */
	public void setVerRelacionContractual(Boolean verRelacionContractual) {
		this.verRelacionContractual = verRelacionContractual;
	}

	/**
	 * @return the result
	 */
	public Collection<Persona> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Collection<Persona> result) {
		this.result = result;
	}



	
}
