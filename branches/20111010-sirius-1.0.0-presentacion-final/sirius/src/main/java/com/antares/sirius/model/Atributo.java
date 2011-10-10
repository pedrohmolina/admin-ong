package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.antares.commons.util.Utils;


@Entity
@SuppressWarnings("serial")
public class Atributo extends PersistentObject {

	private String descripcion;
	private String nombreAtributo;
	private String optionBean;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idEntidad"))
	private Entidad entidad;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idTipoAtributo"))
	private TipoAtributo tipoAtributo;

	public String getDisplayName() {
		return Utils.getMessage(descripcion);
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreAtributo() {
		return nombreAtributo;
	}
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
	public TipoAtributo getTipoAtributo() {
		return tipoAtributo;
	}
	public void setTipoAtributo(TipoAtributo tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}
	public Entidad getEntidad() {
		return entidad;
	}
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	public String getOptionBean() {
		return optionBean;
	}
	public void setOptionBean(String optionBean) {
		this.optionBean = optionBean;
	}

}
