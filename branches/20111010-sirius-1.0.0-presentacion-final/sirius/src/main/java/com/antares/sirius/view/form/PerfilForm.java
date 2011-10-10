package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Perfil;
import com.antares.sirius.model.Rol;

/**
 * Representacion en la capa de vista de la entidad de modelo Perfil.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class PerfilForm extends AbstractForm<Perfil> {

	private String nombre;
	private String descripcion;
	private Collection<Rol> roles;
	private Integer[] idRoles;

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

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public Integer[] getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(Integer[] idRoles) {
		this.idRoles = idRoles;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.descripcion = "";
		this.idRoles = null;
		this.filtroNombre = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.descripcion = "";
		this.idRoles = null;
	}

	@Override
	public void initializeForm(Perfil entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.descripcion = entity.getDescripcion();
		
		int cantRoles = entity.getRoles().size();
		this.idRoles = new Integer[cantRoles];
		for (Rol rol : entity.getRoles()) {
			this.idRoles[--cantRoles] = rol.getId();
		}
	}

}
