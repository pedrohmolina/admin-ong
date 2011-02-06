package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.EstadoFinanciador;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.TipoFinanciador;

/**
 * Filter para la endidad Financiador.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class FinanciadorFilter extends Filter<Financiador> {

	private String nombre;
	private String cuit;
	private String cbu;
	private String direccion;
	private String telefono;
	private String contacto;
	private String celular;
	private String email;
	private EstadoFinanciador estadoFinanciador;
	private TipoFinanciador tipoFinanciador;

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
	public EstadoFinanciador getEstadoFinanciador() {
		return estadoFinanciador;
	}
	public void setEstadoFinanciador(EstadoFinanciador estadoFinanciador) {
		this.estadoFinanciador = estadoFinanciador;
	}
	public TipoFinanciador getTipoFinanciador() {
		return tipoFinanciador;
	}
	public void setTipoFinanciador(TipoFinanciador tipoFinanciador) {
		this.tipoFinanciador = tipoFinanciador;
	}
	
}
