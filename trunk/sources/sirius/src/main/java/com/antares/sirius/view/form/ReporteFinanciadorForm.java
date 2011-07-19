package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.commons.enums.FormatoReporteEnum;
import com.antares.sirius.model.EstadoFinanciador;
import com.antares.sirius.model.Financiador;
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

	private Collection<FormatoReporteEnum> formatosReporte;
	private String formatoReporte;

	//Filtros
	private String nombre;
	private String cuit;
	private String cbu;
	private String idEstadoFinanciador;
	private String idTipoFinanciador;

	private String labelEstadoFinanciador;
	private String labelTipoFinanciador;
	
	private Collection<EstadoFinanciador> estadosFinanciador;
	private Collection<TipoFinanciador> tiposFinanciador;

	//Lista Previa de resultados
	private Collection<Financiador> result;
		
	//Columnas a visualizar en el reporte
	private Boolean verTipoFinanciador;
	private Boolean verEstadoFinanciador;
	private Boolean verCuit;
	private Boolean verCBU;
	private Boolean verContacto;
	private Boolean verDireccion;
	private Boolean verTelefono;
	private Boolean verEmail;
	private Boolean verObservaciones;
	
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public Collection<FormatoReporteEnum> getFormatosReporte() {
		return formatosReporte;
	}

	public void setFormatosReporte(Collection<FormatoReporteEnum> formatosReporte) {
		this.formatosReporte = formatosReporte;
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

	public String getIdEstadoFinanciador() {
		return idEstadoFinanciador;
	}

	public void setIdEstadoFinanciador(String idEstadoFinanciador) {
		this.idEstadoFinanciador = idEstadoFinanciador;
	}

	public String getIdTipoFinanciador() {
		return idTipoFinanciador;
	}

	public void setIdTipoFinanciador(String idTipoFinanciador) {
		this.idTipoFinanciador = idTipoFinanciador;
	}

	public Collection<EstadoFinanciador> getEstadosFinanciador() {
		return estadosFinanciador;
	}

	public void setEstadosFinanciador(
			Collection<EstadoFinanciador> estadosFinanciador) {
		this.estadosFinanciador = estadosFinanciador;
	}

	public Collection<TipoFinanciador> getTiposFinanciador() {
		return tiposFinanciador;
	}

	public void setTiposFinanciador(Collection<TipoFinanciador> tiposFinanciador) {
		this.tiposFinanciador = tiposFinanciador;
	}

	public String getLabelEstadoFinanciador() {
		return labelEstadoFinanciador;
	}

	public void setLabelEstadoFinanciador(String labelEstadoFinanciador) {
		this.labelEstadoFinanciador = labelEstadoFinanciador;
	}

	public String getLabelTipoFinanciador() {
		return labelTipoFinanciador;
	}

	public void setLabelTipoFinanciador(String labelTipoFinanciador) {
		this.labelTipoFinanciador = labelTipoFinanciador;
	}

	public Collection<Financiador> getResult() {
		return result;
	}

	public void setResult(Collection<Financiador> result) {
		this.result = result;
	}

	public Boolean getVerTipoFinanciador() {
		return verTipoFinanciador;
	}

	public void setVerTipoFinanciador(Boolean verTipoFinanciador) {
		this.verTipoFinanciador = verTipoFinanciador;
	}

	public Boolean getVerEstadoFinanciador() {
		return verEstadoFinanciador;
	}

	public void setVerEstadoFinanciador(Boolean verEstadoFinanciador) {
		this.verEstadoFinanciador = verEstadoFinanciador;
	}

	public Boolean getVerCuit() {
		return verCuit;
	}

	public void setVerCuit(Boolean verCuit) {
		this.verCuit = verCuit;
	}

	public Boolean getVerCBU() {
		return verCBU;
	}

	public void setVerCBU(Boolean verCBU) {
		this.verCBU = verCBU;
	}

	public Boolean getVerContacto() {
		return verContacto;
	}

	public void setVerContacto(Boolean verContacto) {
		this.verContacto = verContacto;
	}

	public Boolean getVerDireccion() {
		return verDireccion;
	}

	public void setVerDireccion(Boolean verDireccion) {
		this.verDireccion = verDireccion;
	}

	public Boolean getVerTelefono() {
		return verTelefono;
	}

	public void setVerTelefono(Boolean verTelefono) {
		this.verTelefono = verTelefono;
	}

	public Boolean getVerEmail() {
		return verEmail;
	}

	public void setVerEmail(Boolean verEmail) {
		this.verEmail = verEmail;
	}

	public Boolean getVerObservaciones() {
		return verObservaciones;
	}

	public void setVerObservaciones(Boolean verObservaciones) {
		this.verObservaciones = verObservaciones;
	}

	public void initialize() {
		this.result = null;
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idEstadoFinanciador = "";
		this.idTipoFinanciador = "";
		this.labelEstadoFinanciador = "";
		this.labelTipoFinanciador = "";
		this.verTipoFinanciador = false;
		this.verEstadoFinanciador = false;
		this.verCuit = false;
		this.verCBU = false;
		this.verContacto = false;
		this.verDireccion = false;
		this.verTelefono = false;
		this.verEmail = false;
		this.verObservaciones = false;
	}

	public void initializeForm() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idEstadoFinanciador = "";
		this.idTipoFinanciador = "";
		this.labelEstadoFinanciador = "";
		this.labelTipoFinanciador = "";
		this.verTipoFinanciador = false;
		this.verEstadoFinanciador = false;
		this.verCuit = false;
		this.verCBU = false;
		this.verContacto = false;
		this.verDireccion = false;
		this.verTelefono = false;
		this.verEmail = false;
		this.verObservaciones = false;
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
