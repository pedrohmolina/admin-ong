package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.sirius.model.FormatoReporte;
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

	private Collection<FormatoReporte> formatosReporte;
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

	public void initialize() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
		this.verTipoProveedor = false;
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
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
		this.verTipoProveedor = false;
		this.verCuit = false;
		this.verCBU = false;
		this.verContacto = false;
		this.verDireccion = false;
		this.verTelefono = false;
		this.verEmail = false;
		this.verObservaciones = false;

	}

	public void initializeForm(Proveedor entity) {
		this.nombre = entity.getNombre();
		this.cuit = entity.getCuit();
		this.cbu = entity.getCbu();
		this.idTipoProveedor = entity.getTipoProveedor().getId().toString();
		this.labelTipoProveedor = entity.getTipoProveedor().getDescripcion();
	}

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
	 * @return the idTipoProveedor
	 */
	public String getIdTipoProveedor() {
		return idTipoProveedor;
	}
	/**
	 * @param idTipoProveedor the idTipoProveedor to set
	 */
	public void setIdTipoProveedor(String idTipoProveedor) {
		this.idTipoProveedor = idTipoProveedor;
	}
	/**
	 * @return the tiposProveedor
	 */
	public Collection<TipoProveedor> getTiposProveedor() {
		return tiposProveedor;
	}
	/**
	 * @param tiposProveedor the tiposProveedor to set
	 */
	public void setTiposProveedor(Collection<TipoProveedor> tiposProveedor) {
		this.tiposProveedor = tiposProveedor;
	}
	/**
	 * @return the labelTipoProveedor
	 */
	public String getLabelTipoProveedor() {
		return labelTipoProveedor;
	}
	/**
	 * @param labelTipoProveedor the labelTipoProveedor to set
	 */
	public void setLabelTipoProveedor(String labelTipoProveedor) {
		this.labelTipoProveedor = labelTipoProveedor;
	}
	
	
	/**
	 * @return the result
	 */
	public Collection<Proveedor> getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Collection<Proveedor> result) {
		this.result = result;
	}
	
	
	/**
	 * @return the verTipoProveedor
	 */
	public Boolean getVerTipoProveedor() {
		return verTipoProveedor;
	}
	/**
	 * @param verTipoProveedor the verTipoProveedor to set
	 */
	public void setVerTipoProveedor(Boolean verTipoProveedor) {
		this.verTipoProveedor = verTipoProveedor;
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
	 * @return the verContacto
	 */
	public Boolean getVerContacto() {
		return verContacto;
	}
	/**
	 * @param verContacto the verContacto to set
	 */
	public void setVerContacto(Boolean verContacto) {
		this.verContacto = verContacto;
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
	 * @return the verObservaciones
	 */
	public Boolean getVerObservaciones() {
		return verObservaciones;
	}
	/**
	 * @param verObservaciones the verObservaciones to set
	 */
	public void setVerObservaciones(Boolean verObservaciones) {
		this.verObservaciones = verObservaciones;
	}

}
