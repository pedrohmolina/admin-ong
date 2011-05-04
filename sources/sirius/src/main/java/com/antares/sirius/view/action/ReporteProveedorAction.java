/**
 * 
 */
package com.antares.sirius.view.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.antares.commons.util.ReportUtils;
import com.antares.sirius.filter.ProveedorFilter;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.service.ProveedorService;
import com.antares.sirius.service.ReporteProveedorService;
import com.antares.sirius.view.form.ReporteProveedorForm;

/**
 * @author PDelfino
 *
 */
public class ReporteProveedorAction extends ReporteAction{

	private ReporteProveedorService reporteProveedorService;
	private ProveedorService proveedorService;
	

	
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
	public ActionForward initForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReporteProveedorForm viewForm = (ReporteProveedorForm)form;
		viewForm.initialize();
		loadCollections(viewForm);
		return mapping.findForward("init");
	}
	

	protected void loadCollections(ReporteProveedorForm form) {
		form.setFormatosReporte(ReportUtils.getReportFormatList());
	}
	
	/**
	 * Genera el reporte de Proveedores acorde a los parametros ingresados
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generarReporteProveedor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReporteProveedorForm viewForm = (ReporteProveedorForm)form;
		ProveedorFilter filter = this.createFilter(viewForm);
		Collection<Proveedor> result = proveedorService.findByFilter(filter);

		String reportType = viewForm.getFormatoReporte();
		JasperPrint jasperPrint = reporteProveedorService.generateReportBytes(result);

		this.generateReport(request, response, reportType, jasperPrint);

		return mapping.findForward("null");
	}


	public ProveedorFilter createFilter(ReporteProveedorForm form) {
		ProveedorFilter filter = new ProveedorFilter();
		filter.setNombre(form.getNombre());
		filter.setCuit(form.getCuit());
		filter.setCbu(form.getCbu());
		return filter;
	}


	/**
	 * @return the reporteProveedorService
	 */
	public ReporteProveedorService getReporteProveedorService() {
		return reporteProveedorService;
	}


	/**
	 * @param reporteProveedorService the reporteProveedorService to set
	 */
	public void setReporteProveedorService(
			ReporteProveedorService reporteProveedorService) {
		this.reporteProveedorService = reporteProveedorService;
	}


	/**
	 * @return the proveedorService
	 */
	public ProveedorService getProveedorService() {
		return proveedorService;
	}


	/**
	 * @param proveedorService the proveedorService to set
	 */
	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	
	
}
