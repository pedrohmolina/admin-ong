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
public class Meta extends Ponderable {

	@ManyToOne(fetch = LAZY)
	@JoinColumns(@JoinColumn(name = "idObjetivoEspecifico"))
	private ObjetivoEspecifico objetivoEspecifico;

	private String nombre;
	private String descripcion;

	@OneToMany(mappedBy = "meta", cascade = CascadeType.ALL)
	private Collection<Actividad> actividades;

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

	public ObjetivoEspecifico getObjetivoEspecifico() {
		return objetivoEspecifico;
	}

	public void setObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico) {
		this.objetivoEspecifico = objetivoEspecifico;
	}

	public Collection<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Collection<Actividad> actividades) {
		this.actividades = actividades;
	}

	@Override
	public void setActivo(Boolean activo) {
		super.setActivo(activo);
		if (!activo) {
			for (Actividad actividad : this.actividades) {
				actividad.setActivo(activo);
			}
		}
	}

	public Proyecto getProyecto() {
		return objetivoEspecifico.getObjetivoGeneral().getProyecto();
	}

	@Override
	public Collection<? extends Ponderable> getPonderables() {
		return this.actividades;
	}

}
