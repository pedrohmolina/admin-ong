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

	private String nombre;
	private String cuit;
	private String cbu;
	private String idTipoProveedor;
	private String labelTipoProveedor;
	
	private Collection<TipoProveedor> tiposProveedor;
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
	
	public void initialize() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
	}

	public void initializeForm() {
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
	}

	public void initializeForm(Proveedor entity) {
		this.nombre = entity.getNombre();
		this.cuit = entity.getCuit();
		this.cbu = entity.getCbu();
		this.idTipoProveedor = entity.getTipoProveedor().getId().toString();
		this.labelTipoProveedor = entity.getTipoProveedor().getDescripcion();
	}
	

}
