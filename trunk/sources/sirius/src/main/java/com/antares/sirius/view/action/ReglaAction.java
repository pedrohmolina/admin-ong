package com.antares.sirius.view.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.antares.commons.dao.GenericDAO;
import com.antares.commons.predicate.PropertyType;
import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ReglaFilter;
import com.antares.sirius.model.Atributo;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.Operador;
import com.antares.sirius.model.PersistentObject;
import com.antares.sirius.model.Regla;
import com.antares.sirius.service.AtributoService;
import com.antares.sirius.service.EntidadService;
import com.antares.sirius.service.OperadorService;
import com.antares.sirius.service.ReglaService;
import com.antares.sirius.service.UsuarioService;
import com.antares.sirius.view.form.ReglaForm;

public class ReglaAction extends BaseAction<Regla, ReglaForm, ReglaService> implements BeanFactoryAware {

	private BeanFactory beanFactory;
	private UsuarioService usuarioService;
	private EntidadService entidadService;
	private AtributoService atributoService;
	private OperadorService operadorService;

	@Override
	public ReglaFilter createFilter(ReglaForm form) {
		ReglaFilter filter = new ReglaFilter();
		if (Utils.isNotNullNorEmpty(form.getFiltroIdUsuario())) {
			filter.setUsuario(usuarioService.findById(Utils.parseInteger(form.getFiltroIdUsuario())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Regla entity, ReglaForm form) {
		if (Utils.isNotNullNorEmpty(form.getIdUsuario())) {
			entity.setUsuario(usuarioService.findById(Utils.parseInteger(form.getIdUsuario())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdEntidad())) {
			entity.setEntidad(entidadService.findById(Utils.parseInteger(form.getIdEntidad())));
		}
		if (Utils.isNotNullNorEmpty(form.getIdAtributo())) {
			entity.setAtributo(atributoService.findById(Utils.parseInteger(form.getIdAtributo())));

			PropertyType tipo = PropertyType.findById(entity.getAtributo().getTipoAtributo().getId());
			if (PropertyType.TEXT.equals(tipo)) {
				entity.setValor(form.getValor());
				entity.setValorDescripcion(form.getValor());
			} else if (PropertyType.NUMERIC.equals(tipo)) {
				entity.setValor(form.getValorNumerico());
				entity.setValorDescripcion(form.getValorNumerico());
			} else if (PropertyType.DATE.equals(tipo)) {
				entity.setValor(form.getValorFecha());
				entity.setValorDescripcion(form.getValorFecha());
			} else if (PropertyType.OPTION.equals(tipo)) {
				entity.setValor(form.getValorCombo());
				entity.setValorDescripcion(getValorComboDescripcion(entity.getAtributo(), form.getValorCombo()));
			}
		}
		if (Utils.isNotNullNorEmpty(form.getIdOperador())) {
			entity.setOperador(operadorService.findById(Utils.parseInteger(form.getIdOperador())));
		}
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
		if (form.getValores() == null) {
			form.setValores(new ArrayList<PersistentObject>());
		}
	}

	@Override
	protected void postLoadEntity(Regla entity, ReglaForm viewForm) {
		if (entity.getAtributo() != null) {
			viewForm.setAtributos(entity.getAtributo().getEntidad().getAtributos());

			PropertyType tipo = PropertyType.findById(entity.getAtributo().getTipoAtributo().getId());
			if (PropertyType.TEXT.equals(tipo)) {
				viewForm.setValor(entity.getValor());
			} else if (PropertyType.NUMERIC.equals(tipo)) {
				viewForm.setValorNumerico(entity.getValor());
			} else if (PropertyType.DATE.equals(tipo)) {
				viewForm.setValorFecha(entity.getValor());
			} else if (PropertyType.OPTION.equals(tipo)) {
				viewForm.setValorCombo(entity.getValor());
				viewForm.setValores(getValores(entity.getAtributo()));
			}

			if (entity.getOperador() != null) {
				viewForm.setOperadores(entity.getOperador().getTipoAtributo().getOperadores());
			}
		}
	}

	public ActionForward cargarComboAtributos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	
		String idEntidad =(String) request.getParameter("idEntidad");
		Entidad entidad = entidadService.findById(Utils.parseInteger(idEntidad));
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
		Atributo atributo = atributoService.findById(Utils.parseInteger(idAtributo));
		((ReglaForm)form).setOperadores(atributo.getTipoAtributo().getOperadores());
		Map<String, String> map = new HashMap<String, String>();
		for (Operador operador : atributo.getTipoAtributo().getOperadores()) {
			map.put(new Integer(operador.getId()).toString(), operador.getDescripcion());
		}
		
		sendJSON(response, map);
		return null;
	}
		
	public ActionForward cargarComboValores(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idAtributo =(String) request.getParameter("idAtributo");
		Atributo atributo = atributoService.findById(Utils.parseInteger(idAtributo));

		Map<String, String> map = new HashMap<String, String>();
		Collection<PersistentObject> opciones = getValores(atributo);
		if (!opciones.isEmpty()) {
			((ReglaForm)form).setValores(opciones);
			for (Object opcion : opciones) {
				String id = ((PersistentObject)opcion).getId().toString();
				String descripcion = (String)Utils.getPropertyValue((PersistentObject)opcion, "descripcion");
				map.put(id, descripcion);
			}
		}

		sendJSON(response, map);
		return null;
	}
		
	public ActionForward getTipoAtributo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idAtributo =(String) request.getParameter("idAtributo");
		Atributo atributo = atributoService.findById(Utils.parseInteger(idAtributo));

		Map<String, String> map = new HashMap<String, String>();
		map.put("tipoAtributo", atributo.getTipoAtributo().getId().toString());
		
		sendJSON(response, map);
		return null;
	}

	@SuppressWarnings("unchecked")
	private Collection<PersistentObject> getValores(Atributo atributo) {
		Collection<PersistentObject> opciones = new ArrayList<PersistentObject>();
		GenericDAO<?> dao = getDAO(atributo);
		if (dao != null) {
			opciones.addAll((Collection<PersistentObject>)dao.findAll());
		}
		return opciones;
	}

	private GenericDAO<?> getDAO(Atributo atributo) {
		GenericDAO<?> dao = null;
		if (atributo.getOptionBean() != null) {
			dao = (GenericDAO<?>)beanFactory.getBean(atributo.getOptionBean());
		}
		return dao;
	}

	private String getValorComboDescripcion(Atributo atributo, String valorCombo) {
		String descripcion = "";
		if (Utils.isNotNullNorEmpty(valorCombo)) {
			GenericDAO<?> dao = getDAO(atributo);
			PersistentObject obj = dao.findById(Utils.parseInteger(valorCombo));
			descripcion = (String)Utils.getPropertyValue(obj, "descripcion");
		}
		return descripcion;
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

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

}
