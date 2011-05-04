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

	private String apellido;
	private String nombre;
	private String numeroDocumento;
	private String cuit;
	private String nacionalidad;

	
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
	}

	public void initializeForm() {
		this.apellido = "";
		this.nombre = "";
		this.numeroDocumento = "";
		this.cuit = "";
		this.nacionalidad = "";
	}

	public void initializeForm(Persona entity) {
		this.apellido = entity.getApellido();
		this.nombre = entity.getNombre();
		this.numeroDocumento = entity.getNumeroDocumento().toString();
		this.cuit = entity.getCbu();
		this.nacionalidad = entity.getNacionalidad();
		}

}
