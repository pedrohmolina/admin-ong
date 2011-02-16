package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.FormaPago;
import com.antares.sirius.model.Perfil;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.RelacionContractual;
import com.antares.sirius.model.TipoDocumento;
import com.antares.sirius.model.Usuario;

/**
 * Representacion en la capa de vista de la entidad de modelo Perfil.
 *
 * @version 1.0.0 Created 14/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class UsuarioForm extends AbstractForm<Usuario> {

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
	private String username;
	private String password;
	private String password2;
	private String idTipoDocumento;
	private String idRelacionContractual;
	private String idFormaPago;
	private String idPersonaFactura;
	private String idPerfil;
	private Collection<TipoDocumento> tiposDocumento;
	private Collection<RelacionContractual> relacionesContractuales;
	private Collection<FormaPago> formasPago;
	private Collection<Persona> personasFactura;
	private Collection<Perfil> perfiles;

	private String filtroApellido;
	private String filtroNombre;
	private String filtroNumeroDocumento;
	private String filtroCuit;
	private String filtroIdRelacionContractual;
	private String filtroIdPerfil;

	private String labelTipoDocumento;
	private String labelRelacionContractual;
	private String labelFormaPago;
	private String labelPersonaFactura;
	private String labelPerfil;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Collection<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Collection<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public String getFiltroIdPerfil() {
		return filtroIdPerfil;
	}

	public void setFiltroIdPerfil(String filtroIdPerfil) {
		this.filtroIdPerfil = filtroIdPerfil;
	}

	public String getLabelPerfil() {
		return labelPerfil;
	}

	public void setLabelPerfil(String labelPerfil) {
		this.labelPerfil = labelPerfil;
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
		this.username = "";
		this.password = "";
		this.password2 = "";
		this.idTipoDocumento = "";
		this.idRelacionContractual = "";
		this.idFormaPago = "";
		this.idPersonaFactura = "";
		this.idPerfil = "";
		this.filtroApellido = "";
		this.filtroNombre = "";
		this.filtroNumeroDocumento = "";
		this.filtroCuit = "";
		this.filtroIdRelacionContractual = "";
		this.filtroIdPerfil = "";
		this.labelTipoDocumento = "";
		this.labelRelacionContractual = "";
		this.labelFormaPago = "";
		this.labelPersonaFactura = "";
		this.labelPerfil = "";
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
		this.username = "";
		this.password = "";
		this.password2 = "";
		this.idTipoDocumento = "";
		this.idRelacionContractual = "";
		this.idFormaPago = "";
		this.idPersonaFactura = "";
		this.idPerfil = "";
		this.labelTipoDocumento = "";
		this.labelRelacionContractual = "";
		this.labelFormaPago = "";
		this.labelPersonaFactura = "";
		this.labelPerfil = "";
	}

	@Override
	public void initializeForm(Usuario entity) {
		this.id = entity.getId();
		this.apellido = entity.getPersona().getApellido();
		this.nombre = entity.getPersona().getNombre();
		this.segundoNombre = entity.getPersona().getSegundoNombre();
		this.numeroDocumento = entity.getPersona().getNumeroDocumento().toString();
		this.cuit = entity.getPersona().getCuit();
		this.cbu = entity.getPersona().getCbu();
		this.nacionalidad = entity.getPersona().getNacionalidad();
		this.fechaNacimiento = Utils.formatDate(entity.getPersona().getFechaNacimiento());
		this.profesion = entity.getPersona().getProfesion();
		this.direccion = entity.getPersona().getDireccion();
		this.telefono = entity.getPersona().getTelefono();
		this.celular = entity.getPersona().getCelular();
		this.email = entity.getPersona().getEmail();
		this.funcion = entity.getPersona().getFuncion();
		this.observaciones = entity.getPersona().getObservaciones();
		this.idTipoDocumento = entity.getPersona().getTipoDocumento().getId().toString();
		this.idRelacionContractual = entity.getPersona().getRelacionContractual().getId().toString();
		this.idFormaPago = entity.getPersona().getFormaPago().getId().toString();
		this.labelTipoDocumento = entity.getPersona().getTipoDocumento().getDescripcion();
		this.labelRelacionContractual = entity.getPersona().getRelacionContractual().getDescripcion();
		this.labelFormaPago = entity.getPersona().getFormaPago().getDescripcion();
		
		if (entity.getPersona().getPersonaFactura() != null) {
			this.idPersonaFactura = entity.getPersona().getPersonaFactura().getId().toString();
			this.labelPersonaFactura = entity.getPersona().getPersonaFactura().getNombreYApellido();
		} else {
			this.idPersonaFactura = "";
			this.labelPersonaFactura = "";
		}
		
		this.username = entity.getUsername();
		this.password = "0123456789"; //TODO revisar, esto es fruta para que no reviente la validacion cuando se hace update
		this.password2 = "0123456789";
		this.idPerfil = entity.getPerfil().getId().toString();
		this.labelPerfil = entity.getPerfil().getNombre();
	}

}
