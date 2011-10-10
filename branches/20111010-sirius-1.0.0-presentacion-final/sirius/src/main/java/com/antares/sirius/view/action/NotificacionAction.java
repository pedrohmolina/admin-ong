package com.antares.sirius.view.action;

import static java.lang.Boolean.TRUE;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.antares.commons.filter.Filter;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Notificacion;
import com.antares.sirius.service.NotificacionService;
import com.antares.sirius.view.form.GastoForm;
import com.antares.sirius.view.form.NotificacionForm;

public class NotificacionAction extends DispatchAction {

	private NotificacionService notificacionService;

	public ActionForward initQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotificacionForm viewForm = (NotificacionForm)form;
		viewForm.initialize();
		return queryNoLeidas(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotificacionForm viewForm = (NotificacionForm)form;
		Collection<Notificacion> notificaciones = viewForm.getResult();
		return query(mapping, form, notificaciones);
	}

	public ActionForward queryTodas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection<Notificacion> notificaciones = notificacionService.findAll();
		return query(mapping, form, notificaciones);
	}

	public ActionForward queryNoLeidas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection<Notificacion> notificaciones = notificacionService.findAllNoLeidas();
		return query(mapping, form, notificaciones);
	}

	public ActionForward queryLeidas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection<Notificacion> notificaciones = notificacionService.findAllLeidas();
		return query(mapping, form, notificaciones);
	}

	@SuppressWarnings("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form, Collection<Notificacion> notificaciones) throws Exception {
		NotificacionForm viewForm = (NotificacionForm)form;
		viewForm.setResult(notificaciones);

		Set<Integer> idsNotificacionesLeidas = viewForm.getIdsNotificaciones(); 
		for (Notificacion notificacion : notificaciones) {
			if (idsNotificacionesLeidas.contains(notificacion.getId())) {
				notificacion.setLeida(TRUE);
			}
		}
		return mapping.findForward("query");
	}

	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setConfirmado(Boolean.FALSE);
		return filter;
	}

	@SuppressWarnings("unchecked")
	public ActionForward marcarLeido(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = new Integer(request.getParameter("id"));
		NotificacionForm viewForm = (NotificacionForm)form;
		Set<Integer> idsNotificacionesLeidas = viewForm.getIdsNotificaciones(); 
		idsNotificacionesLeidas.add(id);
		return query(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotificacionForm viewForm = (NotificacionForm)form;
		Set<Integer> idsNotificacionesLeidas = viewForm.getIdsNotificaciones(); 
		for (Integer id : idsNotificacionesLeidas) {
			Notificacion notificacion = notificacionService.findById(id);
			if (notificacion != null) {
				notificacionService.ejecutarMarcarLeida(notificacion);
			}
		}
		return initQuery(mapping, form, request, response);
	}

	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

}
