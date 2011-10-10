package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Acceso;
import com.antares.sirius.model.Rol;

/**
 * Representacion en la capa de vista de la entidad de modelo Rol.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class RolForm extends AbstractForm<Rol> {

	private String nombre;
	private String descripcion;
	private Collection<Acceso> accesos;
	private Integer[] idAccesos;

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

	public Collection<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(Collection<Acceso> accesos) {
		this.accesos = accesos;
	}

	public Integer[] getIdAccesos() {
		return idAccesos;
	}

	public void setIdAccesos(Integer[] idAccesos) {
		this.idAccesos = idAccesos;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.descripcion = "";
		this.idAccesos = null;
		this.filtroNombre = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.descripcion = "";
		this.idAccesos = null;
	}

	@Override
	public void initializeForm(Rol entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.descripcion = entity.getDescripcion();
		
		int cantAccesos = entity.getAccesos().size();
		this.idAccesos = new Integer[cantAccesos];
		for (Acceso acceso : entity.getAccesos()) {
			this.idAccesos[--cantAccesos] = acceso.getId();
		}
	}

}
