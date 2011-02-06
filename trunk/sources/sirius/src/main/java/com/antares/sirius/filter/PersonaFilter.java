package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.RelacionContractual;

/**
 * Filter para la endidad Persona.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class PersonaFilter extends Filter<Persona> {

	private String apellido;
	private String nombre;
	private String cuit;
	private Integer numeroDocumento;
	private RelacionContractual relacionContractual;

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
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public RelacionContractual getRelacionContractual() {
		return relacionContractual;
	}
	public void setRelacionContractual(RelacionContractual relacionContractual) {
		this.relacionContractual = relacionContractual;
	}
	
}
