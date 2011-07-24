package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.PersonaFilter;
import com.antares.sirius.model.Persona;
import com.antares.sirius.service.FormaPagoService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.RelacionContractualService;
import com.antares.sirius.service.TipoDocumentoService;
import com.antares.sirius.view.form.PersonaForm;

public class PersonaAction extends BaseAction<Persona, PersonaForm, PersonaService> {

	private RelacionContractualService relacionContractualService;
	private TipoDocumentoService tipoDocumentoService;
	private FormaPagoService formaPagoService;

	@Override
	public PersonaFilter createFilter(PersonaForm form) {
		PersonaFilter filter = new PersonaFilter();
		filter.setApellido(form.getFiltroApellido());
		filter.setNombre(form.getFiltroNombre());
		filter.setCuit(form.getFiltroCuit());
		if (Utils.isNotNullNorEmpty(form.getFiltroNumeroDocumento())) {
			filter.setNumeroDocumento(Utils.parseInteger(form.getFiltroNumeroDocumento()));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRelacionContractual())) {
			filter.setRelacionContractual(relacionContractualService.findById(Utils.parseInteger(form.getFiltroIdRelacionContractual())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Persona entity, PersonaForm form) {
		entity.setApellido(form.getApellido());
		entity.setNombre(form.getNombre());
		entity.setSegundoNombre(form.getSegundoNombre());
		entity.setNumeroDocumento(Utils.parseInteger(form.getNumeroDocumento()));
		entity.setCuit(form.getCuit());
		entity.setCbu(form.getCbu());
		entity.setNacionalidad(form.getNacionalidad());
		entity.setFechaNacimiento(Utils.parseDate(form.getFechaNacimiento()));
		entity.setProfesion(form.getProfesion());
		entity.setDireccion(form.getDireccion());
		entity.setTelefono(form.getTelefono());
		entity.setCelular(form.getCelular());
		entity.setEmail(form.getEmail());
		entity.setFuncion(form.getFuncion());
		entity.setObservaciones(form.getObservaciones());
		entity.setTipoDocumento(tipoDocumentoService.findById(Utils.parseInteger(form.getIdTipoDocumento())));
		entity.setRelacionContractual(relacionContractualService.findById(Utils.parseInteger(form.getIdRelacionContractual())));
		entity.setFormaPago(formaPagoService.findById(Utils.parseInteger(form.getIdFormaPago())));
		
		if (Utils.isNotNullNorEmpty(form.getIdPersonaFactura())) {
			entity.setPersonaFactura(service.findById(Utils.parseInteger(form.getIdPersonaFactura())));
		} else {
			entity.setPersonaFactura(null);
		}
	}

	@Override
	protected void loadCollections(PersonaForm form) {
		form.setRelacionesContractuales(relacionContractualService.findAll());
		form.setTiposDocumento(tipoDocumentoService.findAll());
		form.setFormasPago(formaPagoService.findAll());
		form.setPersonasFactura(service.findAllOthers(form.getId()));
	}

	@Override
	protected void completeCollections(Persona entity, PersonaForm form) {
		if (!entity.getRelacionContractual().isActivo()) {
			form.getRelacionesContractuales().add(entity.getRelacionContractual());
		}
		if (entity.getPersonaFactura() != null && !entity.getPersonaFactura().isActivo()) {
			form.getPersonasFactura().add(entity.getPersonaFactura());
		}
	}

	@Override
	protected ActionErrors validate(PersonaForm form) {
		return validateNombreApellidoRepetido(form.getNombre(), form.getApellido(), form.getId());
	}

	private ActionErrors validateNombreApellidoRepetido(String nombre, String apellido, Integer id) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreApellidoRepetido(nombre, apellido, id)) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.persona.apellidoYNombre.label")));
		}
		return errors;
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

}
