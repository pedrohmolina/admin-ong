package com.antares.sirius.view.form;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.commons.enums.FormatoReporteEnum;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.model.TipoProveedor;

/**
 * Formulario que contendra los filtros a partir de los cuales se generara el reporte de Proveedores.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
@SuppressWarnings("serial")
public class ReporteProveedorForm extends ActionForm{

	private Collection<FormatoReporteEnum> formatosReporte;
	private String formatoReporte;

	//Filtros
	private String nombre;
	private String cuit;
	private String cbu;
	private String idTipoProveedor;
	private String labelTipoProveedor;
	
	private Collection<TipoProveedor> tiposProveedor;
	
	//Lista Previa de resultados
	private Collection<Proveedor> result;
	
	//Columnas a visualizar en el reporte
	private Boolean verTipoProveedor;
	private Boolean verCuit;
	private Boolean verCBU;
	private Boolean verContacto;
	private Boolean verDireccion;
	private Boolean verTelefono;
	private Boolean verEmail;
	private Boolean verObservaciones;

	public Collection<FormatoReporteEnum> getFormatosReporte() {
		return formatosReporte;
	}

	public void setFormatosReporte(Collection<FormatoReporteEnum> formatosReporte) {
		this.formatosReporte = formatosReporte;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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

	public String getIdTipoProveedor() {
		return idTipoProveedor;
	}

	public void setIdTipoProveedor(String idTipoProveedor) {
		this.idTipoProveedor = idTipoProveedor;
	}

	public Collection<TipoProveedor> getTiposProveedor() {
		return tiposProveedor;
	}

	public void setTiposProveedor(Collection<TipoProveedor> tiposProveedor) {
		this.tiposProveedor = tiposProveedor;
	}

	public String getLabelTipoProveedor() {
		return labelTipoProveedor;
	}

	public void setLabelTipoProveedor(String labelTipoProveedor) {
		this.labelTipoProveedor = labelTipoProveedor;
	}
	
	public Collection<Proveedor> getResult() {
		return result;
	}

	public void setResult(Collection<Proveedor> result) {
		this.result = result;
	}

	public Boolean getVerTipoProveedor() {
		return verTipoProveedor;
	}

	public void setVerTipoProveedor(Boolean verTipoProveedor) {
		this.verTipoProveedor = verTipoProveedor;
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
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
		this.verTipoProveedor = TRUE;
		this.verCuit = FALSE;
		this.verCBU = FALSE;
		this.verContacto = FALSE;
		this.verDireccion = FALSE;
		this.verTelefono = TRUE;
		this.verEmail = FALSE;
		this.verObservaciones = FALSE;
	}

	public void initializeForm() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
		this.verTipoProveedor = TRUE;
		this.verCuit = FALSE;
		this.verCBU = FALSE;
		this.verContacto = FALSE;
		this.verDireccion = FALSE;
		this.verTelefono = TRUE;
		this.verEmail = FALSE;
		this.verObservaciones = FALSE;
	}

	public void initializeForm(Proveedor entity) {
		this.nombre = entity.getNombre();
		this.cuit = entity.getCuit();
		this.cbu = entity.getCbu();
		this.idTipoProveedor = entity.getTipoProveedor().getId().toString();
		this.labelTipoProveedor = entity.getTipoProveedor().getDescripcion();
	}

}
