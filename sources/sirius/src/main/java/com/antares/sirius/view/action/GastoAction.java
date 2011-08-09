package com.antares.sirius.view.action;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.OrigenService;
import com.antares.sirius.service.ParametroService;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.ProveedorService;
import com.antares.sirius.service.RubroService;
import com.antares.sirius.service.TipoComprobanteService;
import com.antares.sirius.service.TipoGastoService;
import com.antares.sirius.service.UsuarioService;
import com.antares.sirius.view.form.GastoForm;

public abstract class GastoAction extends BaseAction<Gasto, GastoForm, GastoService> {

	protected ParametroService parametroService;
	protected RubroService rubroService;
	protected OrigenService origenService;
	protected ProveedorService proveedorService;
	protected TipoComprobanteService tipoComprobanteService;
	protected TipoGastoService tipoGastoService;
	protected PersonaService personaService;
	protected UsuarioService usuarioService;

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
		entity.setFecha(Utils.parseDate(form.getFecha()));
		entity.setRubro(rubroService.findById(Utils.parseInteger(form.getIdRubro())));
		entity.setOrigen(origenService.findById(Utils.parseInteger(form.getIdOrigen())));
		entity.setImporte(Utils.parseDouble(form.getImporte()));
		entity.setObservaciones(form.getObservaciones());

		if (Utils.isNullOrEmpty(form.getIdPersona())) {
			entity.setPersona(personaService.findPersonaLogueada());
		} else {
			entity.setPersona(personaService.findById(Utils.parseInteger(form.getIdPersona())));
		}
	}

	@Override
	protected void loadCollections(GastoForm form) {
		form.setRubros(rubroService.findAll());
		form.setOrigenes(origenService.findAll());
		form.setProveedores(proveedorService.findAll());
		form.setTiposComprobante(tipoComprobanteService.findAll());
	}

	@Override
	protected void completeCollections(Gasto entity, GastoForm form) {
		if (!entity.getRubro().isActivo()) {
			form.getRubros().add(entity.getRubro());
		}
		if (entity.getProveedor() != null && !entity.getProveedor().isActivo()) {
			form.getProveedores().add(entity.getProveedor());
		}
	}

	public void setRubroService(RubroService rubroService) {
		this.rubroService = rubroService;
	}

	public void setOrigenService(OrigenService origenService) {
		this.origenService = origenService;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public void setTipoComprobanteService(TipoComprobanteService tipoComprobanteService) {
		this.tipoComprobanteService = tipoComprobanteService;
	}

	public void setTipoGastoService(TipoGastoService tipoGastoService) {
		this.tipoGastoService = tipoGastoService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
