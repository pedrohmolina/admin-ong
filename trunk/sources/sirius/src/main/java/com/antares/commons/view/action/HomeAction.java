package com.antares.commons.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.antares.sirius.base.Constants;

public class HomeAction extends Action implements Constants {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward result = null;

		//En caso de redirigir a otra pantalla al loguearse, hacerlo desde acá
		result = mapping.findForward("adminHome");

		return result;
	}

}
