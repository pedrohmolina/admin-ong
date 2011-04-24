package com.antares.sirius.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ReglaFilter;
import com.antares.sirius.model.Atributo;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.Operador;
import com.antares.sirius.model.Regla;
import com.antares.sirius.service.AtributoService;
import com.antares.sirius.service.EntidadService;
import com.antares.sirius.service.OperadorService;
import com.antares.sirius.service.ReglaService;
import com.antares.sirius.service.UsuarioService;
import com.antares.sirius.view.form.ReglaForm;

public class ReglaAction extends BaseAction<Regla, ReglaForm, ReglaService> {

	private UsuarioService usuarioService;
	private EntidadService entidadService;
	private AtributoService atributoService;
	private OperadorService operadorService;

	@Override
	public ReglaFilter createFilter(ReglaForm form) {
		ReglaFilter filter = new ReglaFilter();
		if (Utils.isNotNullNorEmpty(form.getFiltroIdUsuario())) {
			filter.setUsuario(usuarioService.findById(Integer.parseInt(form.getFiltroIdUsuario())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Regla entity, ReglaForm form) {
		if (Utils.isNotNullNorEmpty(form.getIdUsuario())) {
			entity.setUsuario(usuarioService.findById(Integer.parseInt(form.getIdUsuario())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdEntidad())) {
			entity.setEntidad(entidadService.findById(Integer.parseInt(form.getIdEntidad())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdAtributo())) {
			entity.setAtributo(atributoService.findById(Integer.parseInt(form.getIdAtributo())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdOperador())) {
			entity.setOperador(operadorService.findById(Integer.parseInt(form.getIdOperador())));
		}
		entity.setValor(form.getValor());
	}

	@Override
	protected void loadCollections(ReglaForm form) {
		form.setUsuarios(usuarioService.findAll());
		form.setEntidades(entidadService.findAll());
		if (form.getAtributos() == null) {
			form.setAtributos(new ArrayList<Atributo>());
		}
		if (form.getOperadores() == null) {
			form.setOperadores(new ArrayList<Operador>());
		}
	}

	@Override
	protected void postLoadEntity(Regla entity, ReglaForm viewForm) {
		if (entity.getAtributo() != null) {
			viewForm.setAtributos(entity.getAtributo().getEntidad().getAtributos());
		}
		if (entity.getOperador() != null) {
			viewForm.setOperadores(entity.getOperador().getTipoAtributo().getOperadores());
		}
	}

	public ActionForward cargarComboAtributos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	
		String idEntidad =(String) request.getParameter("idEntidad");
		Entidad entidad = entidadService.findById(Integer.parseInt(idEntidad));
		((ReglaForm)form).setAtributos(entidad.getAtributos());
		Map<String, String> map = new HashMap<String, String>();
		for (Atributo atributo : entidad.getAtributos()) {
			map.put(new Integer(atributo.getId()).toString(), Utils.getMessage(atributo.getDescripcion()));
		}
		
		sendJSON(response, map);
		return null;
	}

	public ActionForward cargarComboOperadores(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idAtributo =(String) request.getParameter("idAtributo");
		Atributo atributo = atributoService.findById(Integer.parseInt(idAtributo));
		((ReglaForm)form).setOperadores(atributo.getTipoAtributo().getOperadores());
		Map<String, String> map = new HashMap<String, String>();
		for (Operador operador : atributo.getTipoAtributo().getOperadores()) {
			map.put(new Integer(operador.getId()).toString(), operador.getDescripcion());
		}
		
		sendJSON(response, map);
		return null;
	}
		
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public EntidadService getEntidadService() {
		return entidadService;
	}

	public void setEntidadService(EntidadService entidadService) {
		this.entidadService = entidadService;
	}

	public AtributoService getAtributoService() {
		return atributoService;
	}

	public void setAtributoService(AtributoService atributoService) {
		this.atributoService = atributoService;
	}

	public OperadorService getOperadorService() {
		return operadorService;
	}

	public void setOperadorService(OperadorService operadorService) {
		this.operadorService = operadorService;
	}

}
