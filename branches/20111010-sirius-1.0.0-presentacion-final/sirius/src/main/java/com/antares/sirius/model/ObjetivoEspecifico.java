package com.antares.sirius.model;

import static javax.persistence.FetchType.LAZY;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class ObjetivoEspecifico extends Ponderable {

	@ManyToOne(fetch = LAZY)
	@JoinColumns(@JoinColumn(name = "idObjetivoGeneral"))
	private ObjetivoGeneral objetivoGeneral;

	private String nombre;
	private String descripcion;

	@OneToMany(mappedBy = "objetivoEspecifico", cascade = CascadeType.ALL)
	private Collection<Meta> metas;

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

	public ObjetivoGeneral getObjetivoGeneral() {
		return objetivoGeneral;
	}

	public void setObjetivoGeneral(ObjetivoGeneral objetivoGeneral) {
		this.objetivoGeneral = objetivoGeneral;
	}

	public Collection<Meta> getMetas() {
		return metas;
	}

	public void setMetas(Collection<Meta> metas) {
		this.metas = metas;
	}

	@Override
	public void setActivo(Boolean activo) {
		super.setActivo(activo);
		if (!activo) {
			for (Meta meta : this.metas) {
				meta.setActivo(activo);
			}
		}
	}

	public Proyecto getProyecto() {
		return objetivoGeneral.getProyecto();
	}

	@Override
	public Collection<? extends Ponderable> getPonderables() {
		return this.metas;
	}

}
