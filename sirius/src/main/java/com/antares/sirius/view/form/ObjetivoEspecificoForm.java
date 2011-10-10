package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;

/**
 * Representacion en la capa de vista de la entidad de modelo ObjetivoEspecifico.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class ObjetivoEspecificoForm extends AbstractForm<ObjetivoEspecifico> {

	private String nombre;
	private String ponderacion;
	private String descripcion;
	private String idObjetivoGeneral;
	private Collection<ObjetivoGeneral> objetivosGenerales;

	private String filtroIdObjetivoGeneral;
	private String filtroNombre;

	private String labelObjetivoGeneral;

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

	public String getIdObjetivoGeneral() {
		return idObjetivoGeneral;
	}

	public void setIdObjetivoGeneral(String idObjetivoGeneral) {
		this.idObjetivoGeneral = idObjetivoGeneral;
	}

	public Collection<ObjetivoGeneral> getObjetivosGenerales() {
		return objetivosGenerales;
	}

	public void setObjetivosGenerales(Collection<ObjetivoGeneral> objetivosGenerales) {
		this.objetivosGenerales = objetivosGenerales;
	}

	public String getLabelObjetivoGeneral() {
		return labelObjetivoGeneral;
	}

	public void setLabelObjetivoGeneral(String labelObjetivoGeneral) {
		this.labelObjetivoGeneral = labelObjetivoGeneral;
	}

	public String getFiltroIdObjetivoGeneral() {
		return filtroIdObjetivoGeneral;
	}

	public void setFiltroIdObjetivoGeneral(String filtroIdObjetivoGeneral) {
		this.filtroIdObjetivoGeneral = filtroIdObjetivoGeneral;
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
		this.ponderacion = "";
		this.descripcion = "";
		this.idObjetivoGeneral = "";
		this.filtroNombre = "";
		this.filtroIdObjetivoGeneral = "";
		this.labelObjetivoGeneral = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.ponderacion = "";
		this.descripcion = "";
		this.idObjetivoGeneral = "";
		this.labelObjetivoGeneral = "";
	}

	@Override
	public void initializeForm(ObjetivoEspecifico entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.ponderacion = entity.getPonderacion().toString();
		this.descripcion = entity.getDescripcion();
		this.idObjetivoGeneral = entity.getObjetivoGeneral().getId().toString();
		this.labelObjetivoGeneral = entity.getObjetivoGeneral().getNombre();
	}

}
