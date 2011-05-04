/**
 * 
 */
package com.antares.sirius.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.antares.commons.util.ReportUtils;
import com.antares.sirius.service.ReporteFinancieroService;
import com.antares.sirius.view.form.ReporteFinancieroForm;

/**
 * @author PDelfino
 *
 */
public class ReporteFinancieroAction extends ReporteAction{

	private ReporteFinancieroService reporteFinancieroService;
	
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
		ReporteFinancieroForm viewForm = (ReporteFinancieroForm)form;
		viewForm.initialize();
		loadCollections(viewForm);
		return mapping.findForward("init");
	}
	

	protected void loadCollections(ReporteFinancieroForm form) {
		form.setFormatosReporte(ReportUtils.getReportFormatList());
	}
	
	/**
	 * Genera el reporte de Finanzas acorde a los parametros ingresados
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generarReporteFinanciero(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String reportType = null;
		JasperPrint jasperPrint = null;
		//TODO - Completar
		this.generateReport(request, response, reportType, jasperPrint);

		return mapping.findForward("null");
	}


	/**
	 * @return the reporteFinancieroService
	 */
	public ReporteFinancieroService getReporteFinancieroService() {
		return reporteFinancieroService;
	}


	/**
	 * @param reporteFinancieroService the reporteFinancieroService to set
	 */
	public void setReporteFinancieroService(
			ReporteFinancieroService reporteFinancieroService) {
		this.reporteFinancieroService = reporteFinancieroService;
	}

	

}
