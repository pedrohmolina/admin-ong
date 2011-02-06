package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.model.TipoProveedor;

/**
 * Representacion en la capa de vista de la entidad de modelo Proveedor.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class ProveedorForm extends AbstractForm<Proveedor> {

	private String nombre;
	private String cuit;
	private String cbu;
	private String direccion;
	private String telefono;
	private String contacto;
	private String celular;
	private String email;
	private String observaciones;
	private String idTipoProveedor;
	private Collection<TipoProveedor> tiposProveedor;

	private String filtroNombre;
	private String filtroCuit;
	private String filtroCbu;
	private String filtroDireccion;
	private String filtroTelefono;
	private String filtroContacto;
	private String filtroCelular;
	private String filtroEmail;
	private String filtroIdTipoProveedor;
	
	private String labelTipoProveedor;

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

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroCuit() {
		return filtroCuit;
	}

	public void setFiltroCuit(String filtroCuit) {
		this.filtroCuit = filtroCuit;
	}

	public String getFiltroCbu() {
		return filtroCbu;
	}

	public void setFiltroCbu(String filtroCbu) {
		this.filtroCbu = filtroCbu;
	}

	public String getFiltroDireccion() {
		return filtroDireccion;
	}

	public void setFiltroDireccion(String filtroDireccion) {
		this.filtroDireccion = filtroDireccion;
	}

	public String getFiltroTelefono() {
		return filtroTelefono;
	}

	public void setFiltroTelefono(String filtroTelefono) {
		this.filtroTelefono = filtroTelefono;
	}

	public String getFiltroContacto() {
		return filtroContacto;
	}

	public void setFiltroContacto(String filtroContacto) {
		this.filtroContacto = filtroContacto;
	}

	public String getFiltroCelular() {
		return filtroCelular;
	}

	public void setFiltroCelular(String filtroCelular) {
		this.filtroCelular = filtroCelular;
	}

	public String getFiltroEmail() {
		return filtroEmail;
	}

	public void setFiltroEmail(String filtroEmail) {
		this.filtroEmail = filtroEmail;
	}

	public String getFiltroIdTipoProveedor() {
		return filtroIdTipoProveedor;
	}

	public void setFiltroIdTipoProveedor(String filtroIdTipoProveedor) {
		this.filtroIdTipoProveedor = filtroIdTipoProveedor;
	}

	public String getLabelTipoProveedor() {
		return labelTipoProveedor;
	}

	public void setLabelTipoProveedor(String labelTipoProveedor) {
		this.labelTipoProveedor = labelTipoProveedor;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.direccion = "";
		this.telefono = "";
		this.contacto = "";
		this.celular = "";
		this.email = "";
		this.observaciones = "";
		this.idTipoProveedor = "";
		this.filtroNombre = "";
		this.filtroCuit = "";
		this.filtroCbu = "";
		this.filtroDireccion = "";
		this.filtroTelefono = "";
		this.filtroContacto = "";
		this.filtroCelular = "";
		this.filtroEmail = "";
		this.filtroIdTipoProveedor = "";
		this.labelTipoProveedor = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.cuit = "";
		this.cbu = "";
		this.direccion = "";
		this.telefono = "";
		this.contacto = "";
		this.celular = "";
		this.email = "";
		this.observaciones = "";
		this.idTipoProveedor = "";
		this.labelTipoProveedor = "";
	}

	@Override
	public void initializeForm(Proveedor entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.cuit = entity.getCuit();
		this.cbu = entity.getCbu();
		this.direccion = entity.getDireccion();
		this.telefono = entity.getTelefono();
		this.contacto = entity.getContacto();
		this.celular = entity.getCelular();
		this.email = entity.getEmail();
		this.observaciones = entity.getObservaciones();
		this.idTipoProveedor = entity.getTipoProveedor().getId().toString();
		this.labelTipoProveedor = entity.getTipoProveedor().getDescripcion();
	}

}
