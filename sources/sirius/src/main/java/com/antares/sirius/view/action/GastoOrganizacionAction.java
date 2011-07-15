package com.antares.sirius.view.action;

import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.view.form.GastoForm;

public class GastoOrganizacionAction extends GastoAction {

	@Override
	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoOrganizacion());
		filter.setFecha(Utils.parseDate(form.getFiltroFecha()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRubro())) {
			filter.setRubro(rubroService.findById(Utils.parseInteger(form.getFiltroIdRubro())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
		entity.setTipoGasto(tipoGastoService.findTipoGastoOrganizacion());
		super.updateEntity(entity, form);
		entity.setProveedor(proveedorService.findById(Utils.parseInteger(form.getIdProveedor())));
		entity.setTipoComprobante(tipoComprobanteService.findById(Utils.parseInteger(form.getIdTipoComprobante())));
		entity.setNumeroComprobante(form.getNumeroComprobante());
	}

}
