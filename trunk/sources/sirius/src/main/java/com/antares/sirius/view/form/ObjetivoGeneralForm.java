package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;

/**
 * Representacion en la capa de vista de la entidad de modelo ObjetivoGeneral.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class ObjetivoGeneralForm extends AbstractForm<ObjetivoGeneral> {

	private String nombre;
	private String ponderacion;
	private String descripcion;
	private String idProyecto;
	private Collection<Proyecto> proyectos;

	private String filtroIdProyecto;
	private String filtroNombre;

	private String labelProyecto;

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

	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public String getLabelProyecto() {
		return labelProyecto;
	}

	public void setLabelProyecto(String labelProyecto) {
		this.labelProyecto = labelProyecto;
	}

	public String getFiltroIdProyecto() {
		return filtroIdProyecto;
	}

	public void setFiltroIdProyecto(String filtroIdProyecto) {
		this.filtroIdProyecto = filtroIdProyecto;
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
		this.idProyecto = "";
		this.filtroNombre = "";
		this.filtroIdProyecto = "";
		this.labelProyecto = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.ponderacion = "";
		this.descripcion = "";
		this.idProyecto = "";
		this.labelProyecto = "";
	}

	@Override
	public void initializeForm(ObjetivoGeneral entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.ponderacion = entity.getPonderacion().toString();
		this.descripcion = entity.getDescripcion();
		this.idProyecto = entity.getProyecto().getId().toString();
		this.labelProyecto = entity.getProyecto().getNombre();
	}

}
