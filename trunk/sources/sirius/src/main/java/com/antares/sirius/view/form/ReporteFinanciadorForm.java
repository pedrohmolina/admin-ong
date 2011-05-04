package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.sirius.model.EstadoFinanciador;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.FormatoReporte;
import com.antares.sirius.model.TipoFinanciador;

/**
 * Formulario que contendra los filtros a partir de los cuales se generara el reporte de Financiadores.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
@SuppressWarnings("serial")
public class ReporteFinanciadorForm extends ActionForm{

	private Collection<FormatoReporte> formatosReporte;
	private String formatoReporte;

	private String nombre;
	private String cuit;
	private String cbu;
	private String idEstadoFinanciador;
	private String idTipoFinanciador;

	private String labelEstadoFinanciador;
	private String labelTipoFinanciador;
	
	private Collection<EstadoFinanciador> estadosFinanciador;
	private Collection<TipoFinanciador> tiposFinanciador;

	
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

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * @param cbu the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * @return the idEstadoFinanciador
	 */
	public String getIdEstadoFinanciador() {
		return idEstadoFinanciador;
	}

	/**
	 * @param idEstadoFinanciador the idEstadoFinanciador to set
	 */
	public void setIdEstadoFinanciador(String idEstadoFinanciador) {
		this.idEstadoFinanciador = idEstadoFinanciador;
	}

	/**
	 * @return the idTipoFinanciador
	 */
	public String getIdTipoFinanciador() {
		return idTipoFinanciador;
	}

	/**
	 * @param idTipoFinanciador the idTipoFinanciador to set
	 */
	public void setIdTipoFinanciador(String idTipoFinanciador) {
		this.idTipoFinanciador = idTipoFinanciador;
	}

	/**
	 * @return the estadosFinanciador
	 */
	public Collection<EstadoFinanciador> getEstadosFinanciador() {
		return estadosFinanciador;
	}

	/**
	 * @param estadosFinanciador the estadosFinanciador to set
	 */
	public void setEstadosFinanciador(
			Collection<EstadoFinanciador> estadosFinanciador) {
		this.estadosFinanciador = estadosFinanciador;
	}

	/**
	 * @return the tiposFinanciador
	 */
	public Collection<TipoFinanciador> getTiposFinanciador() {
		return tiposFinanciador;
	}

	/**
	 * @param tiposFinanciador the tiposFinanciador to set
	 */
	public void setTiposFinanciador(Collection<TipoFinanciador> tiposFinanciador) {
		this.tiposFinanciador = tiposFinanciador;
	}

	
	/**
	 * @return the labelEstadoFinanciador
	 */
	public String getLabelEstadoFinanciador() {
		return labelEstadoFinanciador;
	}

	/**
	 * @param labelEstadoFinanciador the labelEstadoFinanciador to set
	 */
	public void setLabelEstadoFinanciador(String labelEstadoFinanciador) {
		this.labelEstadoFinanciador = labelEstadoFinanciador;
	}

	/**
	 * @return the labelTipoFinanciador
	 */
	public String getLabelTipoFinanciador() {
		return labelTipoFinanciador;
	}

	/**
	 * @param labelTipoFinanciador the labelTipoFinanciador to set
	 */
	public void setLabelTipoFinanciador(String labelTipoFinanciador) {
		this.labelTipoFinanciador = labelTipoFinanciador;
	}

	public void initialize() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idEstadoFinanciador = "";
		this.idTipoFinanciador = "";
		this.labelEstadoFinanciador = "";
		this.labelTipoFinanciador = "";
	}

	public void initializeForm() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idEstadoFinanciador = "";
		this.idTipoFinanciador = "";
		this.labelEstadoFinanciador = "";
		this.labelTipoFinanciador = "";

	}

	public void initializeForm(Financiador entity) {
		this.nombre = entity.getNombre();
		this.cuit = entity.getCuit();
		this.cbu = entity.getCbu();
		this.idEstadoFinanciador = entity.getEstadoFinanciador().getId().toString();
		this.idTipoFinanciador = entity.getTipoFinanciador().getId().toString();
		this.labelEstadoFinanciador = entity.getEstadoFinanciador().getDescripcion();
		this.labelTipoFinanciador = entity.getTipoFinanciador().getDescripcion();

	}

}
