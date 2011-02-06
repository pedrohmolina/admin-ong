package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Rubro;

/**
 * Representacion en la capa de vista de la entidad de modelo Rubro.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class RubroForm extends AbstractForm<Rubro> {

	private Integer nivel;
	private String nombre;
	private String descripcion;
	private String idRubroNivelUno;
	private String idRubroNivelDos;
	private Collection<Rubro> rubrosNivelUno;
	private Collection<Rubro> rubrosNivelDos;

	private String filtroNombre;

	private String labelRubroNivelUno;
	private String labelRubroNivelDos;
	
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

	public String getIdRubroNivelUno() {
		return idRubroNivelUno;
	}

	public void setIdRubroNivelUno(String idRubroNivelUno) {
		this.idRubroNivelUno = idRubroNivelUno;
	}

	public String getIdRubroNivelDos() {
		return idRubroNivelDos;
	}

	public void setIdRubroNivelDos(String idRubroNivelDos) {
		this.idRubroNivelDos = idRubroNivelDos;
	}

	public Collection<Rubro> getRubrosNivelUno() {
		return rubrosNivelUno;
	}

	public void setRubrosNivelUno(Collection<Rubro> rubrosNivelUno) {
		this.rubrosNivelUno = rubrosNivelUno;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getLabelRubroNivelUno() {
		return labelRubroNivelUno;
	}

	public void setLabelRubroNivelUno(String labelRubroNivelUno) {
		this.labelRubroNivelUno = labelRubroNivelUno;
	}

	public String getLabelRubroNivelDos() {
		return labelRubroNivelDos;
	}

	public void setLabelRubroNivelDos(String labelRubroNivelDos) {
		this.labelRubroNivelDos = labelRubroNivelDos;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Collection<Rubro> getRubrosNivelDos() {
		return rubrosNivelDos;
	}

	public void setRubrosNivelDos(Collection<Rubro> rubrosNivelDos) {
		this.rubrosNivelDos = rubrosNivelDos;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.descripcion = "";
		this.idRubroNivelUno = "";
		this.idRubroNivelDos = "";
		this.filtroNombre = "";
		this.labelRubroNivelUno = "";
		this.labelRubroNivelDos = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.descripcion = "";
		this.idRubroNivelUno = "";
		this.idRubroNivelDos = "";
		this.labelRubroNivelUno = "";
		this.labelRubroNivelDos = "";
	}

	@Override
	public void initializeForm(Rubro entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.descripcion = entity.getDescripcion();
		if (entity.getRubroPadre() != null) {
			if (entity.getRubroPadre().getRubroPadre() != null) {
				this.nivel = 3;
				this.idRubroNivelUno = entity.getRubroPadre().getRubroPadre().getId().toString();
				this.labelRubroNivelUno = entity.getRubroPadre().getRubroPadre().getNombre();
				this.idRubroNivelDos = entity.getRubroPadre().getId().toString();
				this.labelRubroNivelDos = entity.getRubroPadre().getNombre();
			} else {
				this.nivel = 2;
				this.idRubroNivelUno = entity.getRubroPadre().getId().toString();
				this.labelRubroNivelUno = entity.getRubroPadre().getNombre();
				this.idRubroNivelDos = "";
				this.labelRubroNivelDos = "";
			}
		} else {
			this.nivel = 1;
			this.idRubroNivelUno = "";
			this.idRubroNivelDos = "";
			this.labelRubroNivelUno = "";
			this.labelRubroNivelDos = "";
		}
	}

}
