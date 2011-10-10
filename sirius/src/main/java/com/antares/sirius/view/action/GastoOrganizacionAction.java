package com.antares.sirius.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
		filter.setFechaDesde(Utils.parseDate(form.getFiltroFechaDesde()));
		filter.setFechaHasta(Utils.parseDate(form.getFiltroFechaHasta()));
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

	public ActionForward confirmar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("restrictedAccess");
	}

}
