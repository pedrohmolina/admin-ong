package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.FormaPago;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.RelacionContractual;
import com.antares.sirius.model.TipoDocumento;

/**
 * Representacion en la capa de vista de la entidad de modelo Perfil.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class PersonaForm extends AbstractForm<Persona> {

	private String apellido;
	private String nombre;
	private String segundoNombre;
	private String numeroDocumento;
	private String cuit;
	private String cbu;
	private String nacionalidad;
	private String fechaNacimiento;
	private String profesion;
	private String direccion;
	private String telefono;
	private String celular;
	private String email;
	private String funcion;
	private String observaciones;
	private String idTipoDocumento;
	private String idRelacionContractual;
	private String idFormaPago;
	private String idPersonaFactura;
	private Collection<TipoDocumento> tiposDocumento;
	private Collection<RelacionContractual> relacionesContractuales;
	private Collection<FormaPago> formasPago;
	private Collection<Persona> personasFactura;

	private String filtroApellido;
	private String filtroNombre;
	private String filtroNumeroDocumento;
	private String filtroCuit;
	private String filtroIdRelacionContractual;

	private String labelTipoDocumento;
	private String labelRelacionContractual;
	private String labelFormaPago;
	private String labelPersonaFactura;

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

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
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

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Collection<TipoDocumento> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(Collection<TipoDocumento> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public Collection<RelacionContractual> getRelacionesContractuales() {
		return relacionesContractuales;
	}

	public void setRelacionesContractuales(Collection<RelacionContractual> relacionesContractuales) {
		this.relacionesContractuales = relacionesContractuales;
	}

	public Collection<FormaPago> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(Collection<FormaPago> formasPago) {
		this.formasPago = formasPago;
	}

	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getIdRelacionContractual() {
		return idRelacionContractual;
	}

	public void setIdRelacionContractual(String idRelacionContractual) {
		this.idRelacionContractual = idRelacionContractual;
	}

	public String getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdFormaPago(String idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public String getFiltroApellido() {
		return filtroApellido;
	}

	public void setFiltroApellido(String filtroApellido) {
		this.filtroApellido = filtroApellido;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroNumeroDocumento() {
		return filtroNumeroDocumento;
	}

	public void setFiltroNumeroDocumento(String filtroNumeroDocumento) {
		this.filtroNumeroDocumento = filtroNumeroDocumento;
	}

	public String getFiltroCuit() {
		return filtroCuit;
	}

	public void setFiltroCuit(String filtroCuit) {
		this.filtroCuit = filtroCuit;
	}

	public String getFiltroIdRelacionContractual() {
		return filtroIdRelacionContractual;
	}

	public void setFiltroIdRelacionContractual(String filtroIdRelacionContractual) {
		this.filtroIdRelacionContractual = filtroIdRelacionContractual;
	}

	public String getLabelTipoDocumento() {
		return labelTipoDocumento;
	}

	public void setLabelTipoDocumento(String labelTipoDocumento) {
		this.labelTipoDocumento = labelTipoDocumento;
	}

	public String getLabelRelacionContractual() {
		return labelRelacionContractual;
	}

	public void setLabelRelacionContractual(String labelRelacionContractual) {
		this.labelRelacionContractual = labelRelacionContractual;
	}

	public String getLabelFormaPago() {
		return labelFormaPago;
	}

	public void setLabelFormaPago(String labelFormaPago) {
		this.labelFormaPago = labelFormaPago;
	}

	public String getIdPersonaFactura() {
		return idPersonaFactura;
	}

	public void setIdPersonaFactura(String idPersonaFactura) {
		this.idPersonaFactura = idPersonaFactura;
	}

	public Collection<Persona> getPersonasFactura() {
		return personasFactura;
	}

	public void setPersonasFactura(Collection<Persona> personasFactura) {
		this.personasFactura = personasFactura;
	}

	public String getLabelPersonaFactura() {
		return labelPersonaFactura;
	}

	public void setLabelPersonaFactura(String labelPersonaFactura) {
		this.labelPersonaFactura = labelPersonaFactura;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.apellido = "";
		this.nombre = "";
		this.segundoNombre = "";
		this.numeroDocumento = "";
		this.cuit = "";
		this.cbu = "";
		this.nacionalidad = "";
		this.fechaNacimiento = "";
		this.profesion = "";
		this.direccion = "";
		this.telefono = "";
		this.celular = "";
		this.email = "";
		this.funcion = "";
		this.observaciones = "";
		this.idTipoDocumento = "";
		this.idRelacionContractual = "";
		this.idFormaPago = "";
		this.idPersonaFactura = "";
		this.labelTipoDocumento = "";
		this.labelRelacionContractual = "";
		this.labelFormaPago = "";
		this.labelPersonaFactura = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.apellido = "";
		this.nombre = "";
		this.segundoNombre = "";
		this.numeroDocumento = "";
		this.cuit = "";
		this.cbu = "";
		this.nacionalidad = "";
		this.fechaNacimiento = "";
		this.profesion = "";
		this.direccion = "";
		this.telefono = "";
		this.celular = "";
		this.email = "";
		this.funcion = "";
		this.observaciones = "";
		this.idTipoDocumento = "";
		this.idRelacionContractual = "";
		this.idFormaPago = "";
		this.idPersonaFactura = "";
		this.labelTipoDocumento = "";
		this.labelRelacionContractual = "";
		this.labelFormaPago = "";
		this.labelPersonaFactura = "";
	}

	@Override
	public void initializeForm(Persona entity) {
		this.id = entity.getId();
		this.apellido = entity.getApellido();
		this.nombre = entity.getNombre();
		this.segundoNombre = entity.getSegundoNombre();
		this.numeroDocumento = entity.getNumeroDocumento().toString();
		this.cuit = entity.getCuit();
		this.cbu = entity.getCbu();
		this.nacionalidad = entity.getNacionalidad();
		this.fechaNacimiento = Utils.formatDate(entity.getFechaNacimiento());
		this.profesion = entity.getProfesion();
		this.direccion = entity.getDireccion();
		this.telefono = entity.getTelefono();
		this.celular = entity.getCelular();
		this.email = entity.getEmail();
		this.funcion = entity.getFuncion();
		this.observaciones = entity.getObservaciones();
		this.idTipoDocumento = entity.getTipoDocumento().getId().toString();
		this.idRelacionContractual = entity.getRelacionContractual().getId().toString();
		this.idFormaPago = entity.getFormaPago().getId().toString();
		this.labelTipoDocumento = entity.getTipoDocumento().getDescripcion();
		this.labelRelacionContractual = entity.getRelacionContractual().getDescripcion();
		this.labelFormaPago = entity.getFormaPago().getDescripcion();
		
		if (entity.getPersonaFactura() != null) {
			this.idPersonaFactura = entity.getPersonaFactura().getId().toString();
			this.labelPersonaFactura = entity.getPersonaFactura().getNombreYApellido();
		} else {
			this.idPersonaFactura = "";
			this.labelPersonaFactura = "";
		}
	}

}
