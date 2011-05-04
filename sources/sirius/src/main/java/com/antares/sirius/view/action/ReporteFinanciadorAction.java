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
import com.antares.sirius.filter.FinanciadorFilter;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.service.ReporteFinanciadorService;
import com.antares.sirius.view.form.ReporteFinanciadorForm;

/**
 * @author PDelfino
 *
 */
public class ReporteFinanciadorAction extends ReporteAction{

	private ReporteFinanciadorService reporteFinanciadorService;
	private FinanciadorService financiadorService;
	

	
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
		ReporteFinanciadorForm viewForm = (ReporteFinanciadorForm)form;
		viewForm.initialize();
		loadCollections(viewForm);
		return mapping.findForward("init");
	}
	

	protected void loadCollections(ReporteFinanciadorForm form) {
		form.setFormatosReporte(ReportUtils.getReportFormatList());
	}
	
	/**
	 * Genera el reporte de Financiadores acorde a los parametros ingresados
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generarReporteFinanciador(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReporteFinanciadorForm viewForm = (ReporteFinanciadorForm)form;
		FinanciadorFilter filter = this.createFilter(viewForm);
		Collection<Financiador> result = financiadorService.findByFilter(filter);

		String reportType = viewForm.getFormatoReporte();
		JasperPrint jasperPrint = reporteFinanciadorService.generateReportBytes(result);

		this.generateReport(request, response, reportType, jasperPrint);

		return mapping.findForward("null");
	}


	public FinanciadorFilter createFilter(ReporteFinanciadorForm form) {
		FinanciadorFilter filter = new FinanciadorFilter();
		filter.setNombre(form.getNombre());
		filter.setCuit(form.getCuit());
		filter.setCbu(form.getCbu());
		return filter;
	}


	/**
	 * @return the reporteFinanciadorService
	 */
	public ReporteFinanciadorService getReporteFinanciadorService() {
		return reporteFinanciadorService;
	}


	/**
	 * @param reporteFinanciadorService the reporteFinanciadorService to set
	 */
	public void setReporteFinanciadorService(
			ReporteFinanciadorService reporteFinanciadorService) {
		this.reporteFinanciadorService = reporteFinanciadorService;
	}


	/**
	 * @return the financiadorService
	 */
	public FinanciadorService getFinanciadorService() {
		return financiadorService;
	}


	/**
	 * @param financiadorService the financiadorService to set
	 */
	public void setFinanciadorService(FinanciadorService financiadorService) {
		this.financiadorService = financiadorService;
	}

	
}
