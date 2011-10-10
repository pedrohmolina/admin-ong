package com.antares.commons.view.action;

import static com.antares.commons.enums.ActionEnum.CREATE;
import static com.antares.commons.enums.ActionEnum.UPDATE;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.antares.commons.exception.RestrictedAccessException;
import com.antares.commons.exception.ViewException;
import com.antares.commons.filter.Filter;
import com.antares.commons.service.BusinessEntityService;
import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.BusinessObject;

/**
 * DispatchAction base que contiene el comportamiento común de controlador usado por los ABMs.
 *
 * @version 1.0.0 Created 22/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 * @param <V> clase del formulario que representa la entidad en el capa de vista
 * @param <S> clase del servicio que contiene la lógica de negocio de la entidad
 */
public abstract class BaseAction<T extends BusinessObject, V extends AbstractForm<T>, S extends BusinessEntityService<T>> extends DispatchAction {

	protected S service;

	/**
	 * Inicializa la pantalla de consulta.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward initQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		viewForm.initialize();
		loadCollections(viewForm);
		return mapping.findForward("query");
	}

	/**
	 * Realiza la consulta y devuelve la pantalla con el resultado.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		Filter<T> filter = createFilter(viewForm);
		loadCollections(viewForm);
		viewForm.setResult(service.findByFilter(filter));
		return mapping.findForward("query");
	}

	/**
	 * Realiza un forward directo a la pantalla de query.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		loadCollections(viewForm);
		if (!getErrors(request).isEmpty()) {
			viewForm.setResult(null);
		}
		return mapping.findForward("query");
	}

	/**
	 * Realiza la consulta y devuelve la pantalla con el resultado.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward lastQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		loadCollections(viewForm);
		return mapping.findForward("query");
	}

	/**
	 * Prepara la pantalla de creacion.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward initCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		viewForm.initializeForm();
		viewForm.setAction(CREATE);
		loadCollections(viewForm);
		return mapping.findForward("form");
	}

	/**
	 * Prepara la pantalla de actualizacion.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		V viewForm = (V)form;
		if (loadEntity(viewForm, request)) {
			viewForm.setAction(UPDATE);
			forward = mapping.findForward("form");
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	/**
	 * Realiza un forward directo a la pantalla de formulario.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		loadCollections(viewForm);
		return mapping.findForward("form");
	}

	/**
	 * Inicializa la pantalla de visualizacion.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		V viewForm = (V)form;
		if (loadEntity(viewForm, request)) {
			forward = mapping.findForward("view");
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	/**
	 * Inicializa el formulario con los datos de la entidad a la que se quiere acceder, recuperandola a partir del parametro "id"
	 * del request
	 * 
	 * @param viewForm form cuyos campos se deben completar con los atributos de la entidad
	 * @param request
	 * @return true si pudo inicializarse correctamente el formulario o false si no lo pudo cargar 
	 * 				(tanto en caso que no exista la entidad para el id como si el acceso a la misma se encuentra restringido) 
	 */
	protected boolean loadEntity(V viewForm, HttpServletRequest request) {
		boolean loaded = false;
		Integer id = new Integer(request.getParameter("id"));
		T entity = service.findById(id);
		if (entity != null && entity.isActivo()) {
			viewForm.initializeForm(entity);
			loadCollections(viewForm);
			postLoadEntity(entity, viewForm);
			completeCollections(entity, viewForm);
			loaded = true;
		}
		return loaded;
	}
	
	/**
	 * Guarda el resultado de la creacion o actualizacion de los datos.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		V viewForm = (V)form;
		ActionErrors errors = validate(viewForm);
		ActionForward forward;
		if (errors.isEmpty()) {
			try {
				if (viewForm.getAction().equals(CREATE)) {
					service.save(createEntity(viewForm));
					forward = mapping.findForward("successCreate");
				} else if (viewForm.getAction().equals(UPDATE)) {
					T entity = service.findById(viewForm.getId());
					if (entity != null && entity.isActivo()) {
						updateEntity(entity, viewForm);
						service.update(entity);
						forward = mapping.findForward("successUpdate");
					} else {
						forward = mapping.findForward("restrictedAccess"); 
					}
				} else {
					forward = mapping.findForward("success");
				}
			} catch (RestrictedAccessException e) {
				forward = mapping.findForward("restrictedAccess"); 
			}
		} else {
			saveErrors(request, errors);
			forward = mapping.getInputForward(); 
		}
		return forward;
	}

	/**
	 * Elimina la entidad seleccionada. Para identificarla, se utiliza el campo "id" del request
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer id = new Integer(request.getParameter("id"));
		T entity = service.findById(id);
		if (entity != null) {
			service.delete(entity);
			forward = query(mapping, form, request, response);
		} else {
			forward = mapping.findForward("restrictedAccess"); 
		}
		return forward;
	}

	/**
	 * Crea una entidad a partir de los datos del formulario.
	 * 
	 * @param form form con los datos a partir de los cuales se va a crear la entidad
	 * @return nueva entidad creada a partir del form
	 * @throws ViewException
	 */
	@SuppressWarnings("unchecked")
	protected T createEntity(V form) throws ViewException {
		T entity = null;
		try {
			// Obtengo una nueva instancia de la entidad usando reflection
	        ParameterizedType paramType = Utils.findParameterizedType(getClass());
	        Class<T> clazz = (Class<T>) paramType.getActualTypeArguments()[0];
	        entity = clazz.newInstance();

	        updateEntity(entity, form);
		} catch (Exception e) {
			throw new ViewException("Ha ocurrido un error al crear la entidad a partir del formulario", e);
		}
        return entity;
	}

	/**
	 * Crea un objecto Filter a partir de los campos de filtro del formulario de búsquueda.
	 * 
	 * @param form form que contiene los datos de búsqueda 
	 * @return objeto filter para envia a la capa de servicio
	 */
	protected abstract Filter<T> createFilter(V form);

	/**
	 * Actualiza los datos de la entidad con el contenido del form
	 * 
	 * @param entity entidad a actualizar
	 * @param form form con los datos ingresados por el usuario
	 */
	protected abstract void updateEntity(T entity, V form);

	/**
	 * Permite que los actions hijos puedan especificar como cargar las colecciones de elementos que se 
	 * relacionan con sus entidades
	 * 
	 * @param form objeto que representa el formulario de alta, actualizacion o visualizacion 
	 */
	protected void loadCollections(V form) {
		// Por defecto, vacio. Se deberá redefinir en las clases hijas que lo requieran
	}

	/**
	 * Permite que los actions hijos puedan especificar como completar las colecciones con elementos que fueron dados
	 * de baja (logica) pero con los que aun mantienen relacion
	 * 
	 * @param entity entidad cuyos datos se muestran en el form
	 * @param form objeto que representa el formulario de alta, actualizacion o visualizacion 
	 */
	protected void completeCollections(T entity, V form) {
		// Por defecto, vacio. Se deberá redefinir en las clases hijas que lo requieran
	}

	/**
	 * Permite que los actions hijos puedan asignar valores extra del form usando reglas de negocio  
	 * 
	 * @param entity entidad cuyos datos se muestran en el form
	 * @param viewForm form cuyos campos se deben completar con los atributos de la entidad
	 */
	protected void postLoadEntity(T entity, V viewForm) {
		// Por defecto, vacio. Se deberá redefinir en las clases hijas que lo requieran
	}

	/**
	 * Permite que los actions hijos puedan definir validaciones propias accediendo a demas servicios 
	 * 
	 * @param form objeto que representa el formulario de alta, actualizacion o visualizacion
	 */
	protected ActionErrors validate(V form) {
		// Por defecto, vacio. Se deberá redefinir en las clases hijas que requieran hacer validaciones extra
		return new ActionErrors(); 
	}

	/**
	 * Crea un forward a la pantalla de mensajes.
	 * 
	 * @param request request
	 * @param mapping mapping
	 * @param messageKey key del mensaje a mostrar
	 * @param backUrl url de retorno
	 * @return
	 */
	protected ActionForward sendMessage(HttpServletRequest request, ActionMapping mapping, String messageKey, String backUrl) {
		if (messageKey != null) {
			ActionMessages messages = new ActionMessages();
			messages.add("message", new ActionMessage(messageKey));
			saveMessages(request, messages);
		}
		if (backUrl != null) {
			request.setAttribute("backUrl", backUrl);
		}
		return mapping.findForward("message");
	}

	protected ActionForward sendDefaultMessage(HttpServletRequest request, ActionMapping mapping) {
		return sendMessage(request, mapping, null, null);
	}

	/**
	 * Convierte el mapa clave-valor pasar por parametro en un objeto JSON para enviar a la pagina.
	 * Usado para cargar datos en los combos.
	 * 
	 * @param response response http
	 * @param map mapa clave-valor con los elementos a enviar
	 * @throws IOException
	 */
	protected void sendJSON(HttpServletResponse response, Map<String, String> map) throws IOException {
		JSONObject jsonMap = JSONObject.fromObject(map);
		response.setContentType("text/html; charset=iso-8859-1");
		response.getOutputStream().print(jsonMap.toString());
	}

	public void setService(S service) {
		this.service = service;
	}

}
