package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;

/**
 * Representacion en la capa de vista de la entidad de modelo Meta.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class MetaForm extends AbstractForm<Meta> {

	private String nombre;
	private String ponderacion;
	private String descripcion;
	private String idObjetivoEspecifico;
	private Collection<ObjetivoEspecifico> objetivosEspecificos;

	private String filtroIdObjetivoEspecifico;
	private String filtroNombre;

	private String labelObjetivoEspecifico;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(String ponderacion) {
		this.ponderacion = ponderacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdObjetivoEspecifico() {
		return idObjetivoEspecifico;
	}

	public void setIdObjetivoEspecifico(String idObjetivoEspecifico) {
		this.idObjetivoEspecifico = idObjetivoEspecifico;
	}

	public Collection<ObjetivoEspecifico> getObjetivosEspecificos() {
		return objetivosEspecificos;
	}

	public void setObjetivosEspecificos(
			Collection<ObjetivoEspecifico> objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}

	public String getFiltroIdObjetivoEspecifico() {
		return filtroIdObjetivoEspecifico;
	}

	public void setFiltroIdObjetivoEspecifico(String filtroIdObjetivoEspecifico) {
		this.filtroIdObjetivoEspecifico = filtroIdObjetivoEspecifico;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getLabelObjetivoEspecifico() {
		return labelObjetivoEspecifico;
	}

	public void setLabelObjetivoEspecifico(String labelObjetivoEspecifico) {
		this.labelObjetivoEspecifico = labelObjetivoEspecifico;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.ponderacion = "";
		this.descripcion = "";
		this.idObjetivoEspecifico = "";
		this.filtroNombre = "";
		this.filtroIdObjetivoEspecifico = "";
		this.labelObjetivoEspecifico = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.ponderacion = "";
		this.descripcion = "";
		this.idObjetivoEspecifico = "";
		this.labelObjetivoEspecifico = "";
	}

	@Override
	public void initializeForm(Meta entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.ponderacion = entity.getPonderacion().toString();
		this.descripcion = entity.getDescripcion();
		this.idObjetivoEspecifico = entity.getObjetivoEspecifico().getId().toString();
		this.labelObjetivoEspecifico = entity.getObjetivoEspecifico().getNombre();
	}

}
