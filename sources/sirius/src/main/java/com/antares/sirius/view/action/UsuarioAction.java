package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.CREATE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.UsuarioFilter;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Usuario;
import com.antares.sirius.service.FormaPagoService;
import com.antares.sirius.service.PerfilService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.RelacionContractualService;
import com.antares.sirius.service.TipoDocumentoService;
import com.antares.sirius.service.UsuarioService;
import com.antares.sirius.view.form.UsuarioForm;
import com.antares.sirius.view.form.UsuarioPersonaForm;

public class UsuarioAction extends BaseAction<Usuario, UsuarioForm, UsuarioService> {

	private PersonaService personaService;
	private PerfilService perfilService;
	private RelacionContractualService relacionContractualService;
	private TipoDocumentoService tipoDocumentoService;
	private FormaPagoService formaPagoService;

	@Override
	public UsuarioFilter createFilter(UsuarioForm form) {
		//TODO revisar el tema del filtro
		UsuarioFilter filter = new UsuarioFilter();
		filter.setApellido(form.getFiltroApellido());
		filter.setNombre(form.getFiltroNombre());
		filter.setCuit(form.getFiltroCuit());
		if (Utils.isNotNullNorEmpty(form.getFiltroNumeroDocumento())) {
			filter.setNumeroDocumento(Utils.parseInteger(form.getFiltroNumeroDocumento()));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRelacionContractual())) {
			filter.setRelacionContractual(relacionContractualService.findById(Integer.parseInt(form.getFiltroIdRelacionContractual())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Usuario entity, UsuarioForm form) {
		
		// Datos de persona
		if (entity.getPersona() == null) {
			entity.setPersona(new Persona());
		}
		
		entity.getPersona().setApellido(form.getApellido());
		entity.getPersona().setNombre(form.getNombre());
		entity.getPersona().setSegundoNombre(form.getSegundoNombre());
		entity.getPersona().setNumeroDocumento(Integer.parseInt(form.getNumeroDocumento()));
		entity.getPersona().setCuit(form.getCuit());
		entity.getPersona().setCbu(form.getCbu());
		entity.getPersona().setNacionalidad(form.getNacionalidad());
		entity.getPersona().setFechaNacimiento(Utils.parseDate(form.getFechaNacimiento()));
		entity.getPersona().setProfesion(form.getProfesion());
		entity.getPersona().setDireccion(form.getDireccion());
		entity.getPersona().setTelefono(form.getTelefono());
		entity.getPersona().setCelular(form.getCelular());
		entity.getPersona().setEmail(form.getEmail());
		entity.getPersona().setFuncion(form.getFuncion());
		entity.getPersona().setObservaciones(form.getObservaciones());
		entity.getPersona().setTipoDocumento(tipoDocumentoService.findById(Integer.parseInt(form.getIdTipoDocumento())));
		entity.getPersona().setRelacionContractual(relacionContractualService.findById(Integer.parseInt(form.getIdRelacionContractual())));
		entity.getPersona().setFormaPago(formaPagoService.findById(Integer.parseInt(form.getIdFormaPago())));
		
		if (Utils.isNotNullNorEmpty(form.getIdPersonaFactura())) {
			entity.getPersona().setPersonaFactura(personaService.findById(Integer.parseInt(form.getIdPersonaFactura())));
		}

		// Datos de usuario
		entity.setUsername(form.getUsername());
		entity.setPerfil(perfilService.findById(Integer.parseInt(form.getIdPerfil())));

		if (form.getAction().equals(CREATE)) {
			entity.setPassword(Utils.encode(form.getUsername() + form.getPassword()));
		}
	}

	@Override
	protected void loadCollections(UsuarioForm form) {
		form.setRelacionesContractuales(relacionContractualService.findAll());
		form.setTiposDocumento(tipoDocumentoService.findAll());
		form.setFormasPago(formaPagoService.findAll());
		form.setPersonasFactura(personaService.findAllOthers(form.getId()));
		form.setPerfiles(perfilService.findAll());
	}

	@Override
	protected ActionErrors validate(UsuarioForm form) {
		return validateNombreRepetido(form.getUsername(), form.getId());
	}

	public ActionForward initCreateUsuarioPersona(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		Integer idPersona = new Integer(request.getParameter("idPersona"));
		Persona persona = personaService.findById(idPersona);
		if (persona.isActivo() && persona.getUsuario() == null) {
			UsuarioPersonaForm viewForm = (UsuarioPersonaForm)form;
			viewForm.initializeForm();
			viewForm.setIdPersona(persona.getId());
			viewForm.setAction(CREATE);
			viewForm.setPerfiles(perfilService.findAll());
			forward = mapping.findForward("form");
		} else {
			//TODO Se deberia mostrar un error diciendo que no se puede crear un usuario para esa persona
			forward = mapping.findForward("error");
		}

		return forward;
	}

	public ActionForward initFormUsuarioPersona(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("form");
	}

	public ActionForward saveUsuarioPersona(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsuarioPersonaForm viewForm = (UsuarioPersonaForm)form;
		ActionForward forward = null;
		if (viewForm.getAction().equals(CREATE)) {
			ActionErrors errors = validateNombreRepetido(viewForm.getUsername(), viewForm.getId());
			if (errors.isEmpty()) {
				Usuario usuario = new Usuario();
				Persona persona = personaService.findById(viewForm.getIdPersona());
				usuario.setPersona(persona);
				usuario.setUsername(viewForm.getUsername());
				usuario.setPassword(Utils.encode(viewForm.getUsername() + viewForm.getPassword()));
				usuario.setPerfil(perfilService.findById(Integer.parseInt(viewForm.getIdPerfil())));
				service.save(usuario);

				persona.setUsuario(usuario);
				personaService.save(persona);
			} else {
				saveErrors(request, errors);
				forward = mapping.findForward("error"); 
			}
		}
		if (forward == null) {
			forward = mapping.findForward("success");
		}
		return forward;
	}

	private ActionErrors validateNombreRepetido(String nombre, Integer id) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(nombre, id)) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.usuario.username.label")));
		}
		return errors;
	}

	@Override
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Esto es para que, aun conociendo la url para borrar un usuario, no se pueda borrar desde la aplicacion
		return mapping.findForward("success");
	}

	public ActionForward bloquear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = new Integer(request.getParameter("id"));
		Usuario entity = service.findById(id);
		service.ejecutarBloqueo(entity);
		return mapping.findForward("success");
	}

	public ActionForward desbloquear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = new Integer(request.getParameter("id"));
		Usuario entity = service.findById(id);
		service.ejecutarDesbloqueo(entity);
		return mapping.findForward("success");
	}

	public void setRelacionContractualService(RelacionContractualService relacionContractualService) {
		this.relacionContractualService = relacionContractualService;
	}

	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public void setFormaPagoService(FormaPagoService formaPagoService) {
		this.formaPagoService = formaPagoService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

}
