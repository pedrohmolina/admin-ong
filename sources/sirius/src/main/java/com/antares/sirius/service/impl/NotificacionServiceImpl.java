package com.antares.sirius.service.impl;

import static com.antares.sirius.base.Constants.ID_ACCESO_CONFIRMAR_GASTOS_PROYECTO;
import static com.antares.sirius.base.Constants.NOTIFICACION_MAXLENGTH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.commons.util.Utils;
import com.antares.sirius.base.Constants;
import com.antares.sirius.dao.NotificacionDAO;
import com.antares.sirius.model.Acceso;
import com.antares.sirius.model.Notificacion;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Usuario;
import com.antares.sirius.service.AccesoService;
import com.antares.sirius.service.NotificacionService;
import com.antares.sirius.service.UsuarioService;

/**
 * Implementacion de la interfaz NotificacionService.
 *
 * @version 1.0.0 Created 14/08/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class NotificacionServiceImpl extends BusinessEntityServiceImpl<Notificacion, NotificacionDAO> implements NotificacionService {

	UsuarioService usuarioService;
	AccesoService accesoService;

	@Override
	public Collection<Notificacion> findAll() {
		return dao.findByUsername(Utils.getUsername());
	}
	
	public Collection<Notificacion> findAllNoLeidas() {
		return dao.findByUsername(Utils.getUsername(), FALSE);
	}
	
	public Collection<Notificacion> findAllLeidas() {
		return dao.findByUsername(Utils.getUsername(), TRUE);
	}
	
	public void ejecutarMarcarLeida(Notificacion entity) {
		entity.setLeida(TRUE);
		dao.save(entity);
	}

	public void ejecutarNotificacionGastoProyectoExcedido(Proyecto proyecto, String mensaje) {
		Acceso acceso = accesoService.findById(ID_ACCESO_CONFIRMAR_GASTOS_PROYECTO);
		Collection<Usuario> usuarios = usuarioService.usuariosByAcceso(acceso);
		for (Usuario usuario : usuarios) {
			ejecutarNotificacion(usuario, proyecto, mensaje);
		}
	}

	public void ejecutarNotificacionGastoActividadExcedido(Proyecto proyecto, String mensaje) {
		Acceso acceso = accesoService.findById(Constants.ID_ACCESO_CONFIRMAR_GASTOS_ACTIVIDAD);
		Collection<Usuario> usuarios = usuarioService.usuariosByAcceso(acceso);
		for (Usuario usuario : usuarios) {
			ejecutarNotificacion(usuario, proyecto, mensaje);
		}
	}

	public void ejecutarNotificacion(Usuario usuario, Proyecto proyecto, String mensaje) {
		Notificacion notificacion = new Notificacion();
		notificacion.setFecha(new Date());
		notificacion.setUsuario(usuario);
		notificacion.setProyecto(proyecto);
		notificacion.setMensaje(StringUtils.abbreviate(mensaje, NOTIFICACION_MAXLENGTH));
		dao.save(notificacion);
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setAccesoService(AccesoService accesoService) {
		this.accesoService = accesoService;
	}

}
