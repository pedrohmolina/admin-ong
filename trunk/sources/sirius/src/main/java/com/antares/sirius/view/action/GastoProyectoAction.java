package com.antares.sirius.view.action;

import static com.antares.commons.enums.ActionEnum.CREATE;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.NotificacionService;
import com.antares.sirius.service.PresupuestoService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.view.form.GastoForm;

public class GastoProyectoAction extends GastoAction {

	private ProyectoService proyectoService;
	private PresupuestoService presupuestoService;
	private NotificacionService notificacionService;

	@Override
	protected Filter<Gasto> createFilter(GastoForm form) {
		GastoFilter filter = new GastoFilter();
		filter.setTipoGasto(tipoGastoService.findTipoGastoProyecto());
		if (Utils.isNotNullNorEmpty(form.getFiltroIdProyecto())) {
			filter.setProyecto(proyectoService.findById(Utils.parseInteger(form.getFiltroIdProyecto())));
		}
		filter.setFechaDesde(Utils.parseDate(form.getFiltroFechaDesde()));
		filter.setFechaHasta(Utils.parseDate(form.getFiltroFechaHasta()));
		if (Utils.isNotNullNorEmpty(form.getFiltroIdRubro())) {
			filter.setRubro(rubroService.findById(Utils.parseInteger(form.getFiltroIdRubro())));
		}
		return filter;
	}

	@Override
	public void updateEntity(Gasto entity, GastoForm form) {
		entity.setTipoGasto(tipoGastoService.findTipoGastoProyecto());
		super.updateEntity(entity, form);
		entity.setProyecto(proyectoService.findById(Utils.parseInteger(form.getIdProyecto())));
		if (proyectoService.isIndividual(entity.getProyecto())) {
			entity.setProveedor(proveedorService.findById(Utils.parseInteger(form.getIdProveedor())));
			entity.setTipoComprobante(tipoComprobanteService.findById(Utils.parseInteger(form.getIdTipoComprobante())));
			entity.setNumeroComprobante(form.getNumeroComprobante());
		} else {
			entity.setProveedor(null);
			entity.setTipoComprobante(null);
			entity.setNumeroComprobante(null);
		}
	}

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = super.save(mapping, form, request, response);
		GastoForm viewForm = (GastoForm)form;

		// Si la creacion fue exitosa, se envia una notificacion
		if (viewForm.getAction().equals(CREATE) && forward.equals(mapping.findForward("successCreate"))) {
			Rubro rubro = rubroService.findById(Utils.parseInteger(viewForm.getIdRubro())).getRubroPrimerNivel();
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(viewForm.getIdProyecto()));
			Double montoPresupuestado = presupuestoService.presupuestoProyecto(proyecto, rubro);
			Double montoGastado = service.gastoProyecto(proyecto, rubro);
			if (montoGastado > montoPresupuestado) {
				Double diff = new BigDecimal(montoGastado).subtract(new BigDecimal(montoPresupuestado)).doubleValue();
				String montoExcedido = Utils.formatDouble(diff);
				String msg = getResources(request).getMessage("sirius.notificacion.gastoExcedido.proyecto", proyecto.getNombre(), rubro.getNombre(), montoExcedido);
				notificacionService.ejecutarNotificacionGastoProyectoExcedido(proyecto, msg);
			}
		}
		return forward;
	}
	
	@Override
	protected void loadCollections(GastoForm form) {
		super.loadCollections(form);
		if (CREATE.equals(form.getAction())) {
			form.setProyectos(proyectoService.findAllNoFinalizados());
		} else{
			form.setProyectos(proyectoService.findAll());
		}
	}

	@Override
	protected void postLoadEntity(Gasto entity, GastoForm viewForm) {
		viewForm.setIndividual(service.isIndividual(entity));
		viewForm.setAgrupado(service.isAgrupado(entity));
	}

	@Override
	protected ActionErrors validate(GastoForm form) {
		ActionErrors errors = new ActionErrors();
		Proyecto proyecto = proyectoService.findById(Utils.parseInteger(form.getIdProyecto()));
		if (proyectoService.isFinalizado(proyecto)) {
			errors.add("error", new ActionMessage("errors.proyectoFinalizado"));
		} else if (proyectoService.isIndividual(proyecto)) {
			if (Utils.isNullOrEmpty(form.getIdProveedor())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.proveedor.label")));
			}
			if (Utils.isNullOrEmpty(form.getIdTipoComprobante())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.tipoComprobante.label")));
			}
			if (Utils.isNullOrEmpty(form.getNumeroComprobante())) {
				errors.add("error", new ActionMessage("errors.required", Utils.getMessage("sirius.gasto.numeroComprobante.label")));
			}
		}
		return errors;
	}

	public ActionForward isIndividual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String idProyecto =(String) request.getParameter("idProyecto");
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (Utils.isNotNullNorEmpty(idProyecto)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(idProyecto));
			map.put("isIndividual", proyectoService.isIndividual(proyecto) ? "true" : "false");
		} else {
			map.put("isIndividual", "true");
		}

		sendJSON(response, map);
		return null;
	}
	
	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setPresupuestoService(PresupuestoService presupuestoService) {
		this.presupuestoService = presupuestoService;
	}

	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

}
