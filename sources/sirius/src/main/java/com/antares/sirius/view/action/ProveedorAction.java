package com.antares.sirius.view.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.util.Utils;
import com.antares.commons.view.action.BaseAction;
import com.antares.sirius.filter.ProveedorFilter;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.model.TipoProveedor;
import com.antares.sirius.service.ProveedorService;
import com.antares.sirius.service.TipoProveedorService;
import com.antares.sirius.view.form.ProveedorForm;

public class ProveedorAction extends BaseAction<Proveedor, ProveedorForm, ProveedorService> {
	
	private TipoProveedorService tipoProveedorService;

	@Override
	public ProveedorFilter createFilter(ProveedorForm form) {
		ProveedorFilter filter = new ProveedorFilter();
		filter.setNombre(form.getFiltroNombre());
		filter.setCuit(form.getFiltroCuit());
		filter.setCbu(form.getFiltroCbu());
		filter.setDireccion(form.getFiltroDireccion());
		filter.setTelefono(form.getFiltroTelefono());
		filter.setContacto(form.getFiltroContacto());
		filter.setCelular(form.getFiltroCelular());
		filter.setEmail(form.getFiltroEmail());

		if (Utils.isNotNullNorEmpty(form.getFiltroIdTipoProveedor())) {
			tipoProveedorService.findById(Integer.parseInt(form.getFiltroIdTipoProveedor()));
		}
		return filter;
	}

	@Override
	public void updateEntity(Proveedor entity, ProveedorForm form) {
		entity.setNombre(form.getNombre());
		entity.setCuit(form.getCuit());
		entity.setCbu(form.getCbu());
		entity.setDireccion(form.getDireccion());
		entity.setTelefono(form.getTelefono());
		entity.setContacto(form.getContacto());
		entity.setCelular(form.getCelular());
		entity.setEmail(form.getEmail());
		entity.setObservaciones(form.getObservaciones());

		TipoProveedor tipoProveedor = tipoProveedorService.findById(Integer.parseInt(form.getIdTipoProveedor()));
		entity.setTipoProveedor(tipoProveedor);
	}

	@Override
	protected void loadCollections(ProveedorForm form) {
		form.setTiposProveedor(tipoProveedorService.findAll());		
	}

	@Override
	protected ActionErrors validate(ProveedorForm form) {
		ActionErrors errors = new ActionErrors();
		if (service.isNombreRepetido(form.getNombre(), form.getId())) {
			errors.add("error", new ActionMessage("errors.unique", Utils.getMessage("sirius.proveedor.nombre.label")));
		}
		return errors;
	}

	public void setTipoProveedorService(TipoProveedorService tipoProveedorService) {
		this.tipoProveedorService = tipoProveedorService;
	}

}
