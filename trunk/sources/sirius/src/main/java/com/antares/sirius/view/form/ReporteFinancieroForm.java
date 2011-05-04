package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.sirius.model.FormatoReporte;
import com.antares.sirius.model.Proveedor;

/**
 * Formulario que contendra los filtros a partir de los cuales se generara el reporte de Finanzas.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
@SuppressWarnings("serial")
public class ReporteFinancieroForm extends ActionForm{

	private Collection<FormatoReporte> formatosReporte;
	private String formatoReporte;
	
	//TODO - Definir valores necesarios para este formulario
	
	/**
	 * @return the formatosReporte
	 */
	public Collection<FormatoReporte> getFormatosReporte() {
		return formatosReporte;
	}
	/**
	 * @param formatosReporte the formatosReporte to set
	 */
	public void setFormatosReporte(Collection<FormatoReporte> formatosReporte) {
		this.formatosReporte = formatosReporte;
	}
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}
	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public void initialize() {
	}

	public void initializeForm() {
	}

}
