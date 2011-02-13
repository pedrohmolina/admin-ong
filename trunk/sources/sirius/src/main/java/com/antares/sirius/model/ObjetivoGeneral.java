package com.antares.sirius.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class ObjetivoGeneral extends Ponderable {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idProyecto"))
	private Proyecto proyecto;

	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy = "objetivoGeneral", cascade = CascadeType.ALL)
	private Collection<ObjetivoEspecifico> objetivosEspecificos;

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

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Collection<ObjetivoEspecifico> getObjetivosEspecificos() {
		return objetivosEspecificos;
	}

	public void setObjetivosEspecificos(Collection<ObjetivoEspecifico> objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}

	@Override
	public void setActivo(Boolean activo) {
		super.setActivo(activo);
		if (!activo) {
			for (ObjetivoEspecifico objetivoEspecifico : this.objetivosEspecificos) {
				objetivoEspecifico.setActivo(activo);
			}
		}
	}

	@Override
	public Double getCompletitud() {
		return calcularCompletitud(this.objetivosEspecificos).doubleValue();
	}

}
