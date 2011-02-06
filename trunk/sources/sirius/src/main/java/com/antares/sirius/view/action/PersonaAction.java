package com.antares.sirius.view.action;

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
			filter.setRelacionContractual(relacionContractualService.findById(Integer.parseInt(form.getFiltroIdRelacionContractual())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Persona entity, PersonaForm form) {
		entity.setApellido(form.getApellido());
		entity.setNombre(form.getNombre());
		entity.setSegundoNombre(form.getSegundoNombre());
		entity.setNumeroDocumento(Integer.parseInt(form.getNumeroDocumento()));
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
		entity.setCuitPersonaFactura(form.getCuitPersonaFactura());
		entity.setObservaciones(form.getObservaciones());
		entity.setTipoDocumento(tipoDocumentoService.findById(Integer.parseInt(form.getIdTipoDocumento())));
		entity.setRelacionContractual(relacionContractualService.findById(Integer.parseInt(form.getIdRelacionContractual())));
		entity.setFormaPago(formaPagoService.findById(Integer.parseInt(form.getIdFormaPago())));
	}

	@Override
	protected void loadCollections(PersonaForm form) {
		form.setRelacionesContractuales(relacionContractualService.findAll());
		form.setTiposDocumento(tipoDocumentoService.findAll());
		form.setFormasPago(formaPagoService.findAll());
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
