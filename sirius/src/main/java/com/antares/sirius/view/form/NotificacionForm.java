package com.antares.sirius.view.form;

import java.util.HashSet;
import java.util.Set;

import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Notificacion;

/**
 * Representacion en la capa de vista de la entidad de modelo Notificacion.
 *
 * @version 1.0.0 Created 14/08/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class NotificacionForm extends AbstractForm<Notificacion> {

	private Set<Integer> idsNotificaciones = new HashSet<Integer>();

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
	}

	public Set<Integer> getIdsNotificaciones() {
		return idsNotificaciones;
	}

	public void setIdsNotificaciones(Set<Integer> idsNotificaciones) {
		this.idsNotificaciones = idsNotificaciones;
	}

	@Override
	public void initializeForm() {
	}

	@Override
	public void initializeForm(Notificacion entity) {
	}

}
