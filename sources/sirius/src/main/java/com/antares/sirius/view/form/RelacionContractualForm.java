package com.antares.sirius.view.form;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.RelacionContractual;

/**
 * Representacion en la capa de vista de la entidad de modelo RelacionContractual.
 *
 * @version 1.0.0 Created 22/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class RelacionContractualForm extends AbstractForm<RelacionContractual> {

	private String nombre;
	private String descripcion;

	private String filtroNombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.descripcion = "";
		this.filtroNombre = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.descripcion = "";
	}

	@Override
	public void initializeForm(RelacionContractual entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.descripcion = entity.getDescripcion();
	}

}
