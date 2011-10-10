package com.antares.sirius.view.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.RubroFilter;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.RubroService;
import com.antares.sirius.view.form.RubroForm;

public class RubroAction extends BaseAction<Rubro, RubroForm, RubroService> {
	
	@Override
	public RubroFilter createFilter(RubroForm form) {
		RubroFilter filter = new RubroFilter();
		filter.setNombre(form.getFiltroNombre());
		return filter;
	}

	@Override
	public void updateEntity(Rubro entity, RubroForm form) {
		entity.setNombre(form.getNombre());
		entity.setDescripcion(form.getDescripcion());
		
		switch (form.getNivel()) {
			case 1: entity.setRubroPrimerNivel(entity); 
				break;
			case 2: entity.setRubroPadre(service.findById(Utils.parseInteger(form.getIdRubroNivelUno())));
				entity.setRubroPrimerNivel(entity.getRubroPadre());
				break;
			case 3: entity.setRubroPadre(service.findById(Utils.parseInteger(form.getIdRubroNivelDos())));
				entity.setRubroPrimerNivel(entity.getRubroPadre().getRubroPadre());
				break;
		}
	}

	@Override
	protected void loadCollections(RubroForm form) {
		form.setRubrosNivelUno(service.findPrimerNivel());
		if (Utils.isNotNullNorEmpty(form.getIdRubroNivelUno())) {
			form.setRubrosNivelDos(service.findHijos(Utils.parseInteger(form.getIdRubroNivelUno())));
		} else {
			form.setRubrosNivelDos(new ArrayList<Rubro>());
		}
	}

	@Override
	protected ActionErrors validate(RubroForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.rubro.nombre.label")));
		}
		return errors;
	}

	@Override
	public ActionForward initCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return initCreateNivelUno(mapping, form, request, response);
	}

	public ActionForward initCreateNivelUno(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward =  super.initCreate(mapping, form, request, response);
		((RubroForm)form).setNivel(1);
		return forward;
	}

	public ActionForward initCreateNivelDos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward =  super.initCreate(mapping, form, request, response);
		((RubroForm)form).setNivel(2);
		return forward;
	}

	public ActionForward initCreateNivelTres(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward =  super.initCreate(mapping, form, request, response);
		((RubroForm)form).setNivel(3);
		return forward;
	}

	public ActionForward cargarComboSubrubros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		String id =(String) request.getParameter("idRubro");
		Collection<Rubro> lista = service.findHijos(Utils.parseInteger(id));
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Rubro rubro : lista) {
			map.put(new Integer(rubro.getId()).toString(), rubro.getNombre());
		}
		((RubroForm)form).setRubrosNivelDos(lista);

		sendJSON(response, map);
		return null;
	}

}
