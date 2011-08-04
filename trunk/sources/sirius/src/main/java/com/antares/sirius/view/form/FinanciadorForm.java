package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.EstadoFinanciador;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.TipoFinanciador;

/**
 * Representacion en la capa de vista de la entidad de modelo Financiador.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class FinanciadorForm extends AbstractForm<Financiador> {

	private String nombre;
	private String cuit;
	private String cbu;
	private String direccion;
	private String telefono;
	private String contacto;
	private String celular;
	private String email;
	private String observaciones;
	private String idEstadoFinanciador;
	private String idTipoFinanciador;
	private Collection<EstadoFinanciador> estadosFinanciador;
	private Collection<TipoFinanciador> tiposFinanciador;
	
	private String filtroNombre;
	private String filtroIdEstadoFinanciador;
	private String filtroIdTipoFinanciador;

	private String labelEstadoFinanciador;
	private String labelTipoFinanciador;

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

	public void setEstadosFinanciador(Collection<EstadoFinanciador> estadosFinanciador) {
		this.estadosFinanciador = estadosFinanciador;
	}

	public Collection<TipoFinanciador> getTiposFinanciador() {
		return tiposFinanciador;
	}

	public void setTiposFinanciador(Collection<TipoFinanciador> tiposFinanciador) {
		this.tiposFinanciador = tiposFinanciador;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroIdEstadoFinanciador() {
		return filtroIdEstadoFinanciador;
	}

	public void setFiltroIdEstadoFinanciador(String filtroIdEstadoFinanciador) {
		this.filtroIdEstadoFinanciador = filtroIdEstadoFinanciador;
	}

	public String getFiltroIdTipoFinanciador() {
		return filtroIdTipoFinanciador;
	}

	public void setFiltroIdTipoFinanciador(String filtroIdTipoFinanciador) {
		this.filtroIdTipoFinanciador = filtroIdTipoFinanciador;
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
		this.idEstadoFinanciador = "";
		this.idTipoFinanciador = "";
		this.filtroNombre = "";
		this.filtroIdEstadoFinanciador = "";
		this.filtroIdTipoFinanciador = "";
		this.labelEstadoFinanciador = "";
		this.labelTipoFinanciador = "";
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
		this.idEstadoFinanciador = "";
		this.idTipoFinanciador = "";
		this.labelEstadoFinanciador = "";
		this.labelTipoFinanciador = "";
	}

	@Override
	public void initializeForm(Financiador entity) {
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
		this.idEstadoFinanciador = entity.getEstadoFinanciador().getId().toString();
		this.idTipoFinanciador = entity.getTipoFinanciador().getId().toString();
		this.labelEstadoFinanciador = entity.getEstadoFinanciador().getDescripcion();
		this.labelTipoFinanciador = entity.getTipoFinanciador().getDescripcion();
	}

}
