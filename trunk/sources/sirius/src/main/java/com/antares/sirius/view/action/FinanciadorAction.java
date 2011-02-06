package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.FinanciadorFilter;
import com.antares.sirius.model.EstadoFinanciador;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.TipoFinanciador;
import com.antares.sirius.service.EstadoFinanciadorService;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.TipoFinanciadorService;
import com.antares.sirius.view.form.FinanciadorForm;

public class FinanciadorAction extends BaseAction<Financiador, FinanciadorForm, FinanciadorService> {

	private EstadoFinanciadorService estadoFinanciadorService;
	private TipoFinanciadorService tipoFinanciadorService;

	@Override
	public FinanciadorFilter createFilter(FinanciadorForm form) {
		FinanciadorFilter filter = new FinanciadorFilter();
		filter.setNombre(form.getFiltroNombre());
		filter.setCuit(form.getFiltroCuit());
		filter.setCbu(form.getFiltroCbu());
		filter.setDireccion(form.getFiltroDireccion());
		filter.setTelefono(form.getFiltroTelefono());
		filter.setContacto(form.getFiltroContacto());
		filter.setCelular(form.getFiltroCelular());
		filter.setEmail(form.getFiltroEmail());

		if (Utils.isNotNullNorEmpty(form.getFiltroIdEstadoFinanciador())) {
			estadoFinanciadorService.findById(Integer.parseInt(form.getFiltroIdEstadoFinanciador()));
		}
		if (Utils.isNotNullNorEmpty(form.getFiltroIdTipoFinanciador())) {
			tipoFinanciadorService.findById(Integer.parseInt(form.getFiltroIdTipoFinanciador()));
		}
		return filter;
	}

	@Override
	public void updateEntity(Financiador entity, FinanciadorForm form) {
		entity.setNombre(form.getNombre());
		entity.setCuit(form.getCuit());
		entity.setCbu(form.getCbu());
		entity.setDireccion(form.getDireccion());
		entity.setTelefono(form.getTelefono());
		entity.setContacto(form.getContacto());
		entity.setCelular(form.getCelular());
		entity.setEmail(form.getEmail());
		entity.setObservaciones(form.getObservaciones());

		EstadoFinanciador estadoFinanciador = estadoFinanciadorService.findById(Integer.parseInt(form.getIdEstadoFinanciador()));
		entity.setEstadoFinanciador(estadoFinanciador);
		TipoFinanciador tipoFinanciador = tipoFinanciadorService.findById(Integer.parseInt(form.getIdTipoFinanciador()));
		entity.setTipoFinanciador(tipoFinanciador);
	}

	@Override
	protected void loadCollections(FinanciadorForm form) {
		form.setEstadosFinanciador(estadoFinanciadorService.findAll());		
		form.setTiposFinanciador(tipoFinanciadorService.findAll());		
	}

	@Override
	protected ActionErrors validate(FinanciadorForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.financiador.nombre.label")));
		}
		return errors;
	}

	public EstadoFinanciadorService getEstadoFinanciadorService() {
		return estadoFinanciadorService;
	}

	public void setEstadoFinanciadorService(EstadoFinanciadorService estadoFinanciadorService) {
		this.estadoFinanciadorService = estadoFinanciadorService;
	}

	public TipoFinanciadorService getTipoFinanciadorService() {
		return tipoFinanciadorService;
	}

	public void setTipoFinanciadorService(TipoFinanciadorService tipoFinanciadorService) {
		this.tipoFinanciadorService = tipoFinanciadorService;
	}

}
