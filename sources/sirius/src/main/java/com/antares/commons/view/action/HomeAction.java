package com.antares.commons.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.antares.sirius.base.Constants;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.NotificacionService;
import com.antares.sirius.service.UsuarioService;

public class HomeAction extends Action implements Constants {

	private NotificacionService notificacionService;
	private UsuarioService usuarioService;
	private GastoService gastoService;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward result = mapping.findForward("adminHome");

		//En caso de redirigir a otra pantalla al loguearse, hacerlo desde acá
		if (notificacionService.findAllNoLeidas().size() > 0) {
			result = mapping.findForward("notificaciones");
		} else if ((usuarioService.userHasAccess(ACCESO_CONFIRMAR_GASTOS_ACTIVIDAD) || usuarioService.userHasAccess(ACCESO_CONFIRMAR_GASTOS_PROYECTO))) {
			if (gastoService.hayGastosSinConfirmar()) {
				result = mapping.findForward("verificarGastos");
			}
		}

		return result;
	}

	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setGastoService(GastoService gastoService) {
		this.gastoService = gastoService;
	}

}
