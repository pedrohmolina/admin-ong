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
import com.antares.sirius.filter.PersonaFilter;
import com.antares.sirius.model.Persona;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.ReportePersonaService;
import com.antares.sirius.view.form.ReportePersonaForm;

/**
 * @author PDelfino
 *
 */
public class ReportePersonaAction extends ReporteAction{

	private ReportePersonaService reportePersonaService;
	private PersonaService personaService;
	
	public ReportePersonaService getReportePersonaService() {
		return reportePersonaService;
	}
	public void setReportePersonaService(ReportePersonaService reportePersonaService) {
		this.reportePersonaService = reportePersonaService;
	}
	public PersonaService getPersonaService() {
		return personaService;
	}
	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}
	
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
		ReportePersonaForm viewForm = (ReportePersonaForm)form;
		viewForm.initialize();
		loadCollections(viewForm);
		return mapping.findForward("init");
	}
	

	protected void loadCollections(ReportePersonaForm form) {
		form.setFormatosReporte(ReportUtils.getReportFormatList());
	}
	
	/**
	 * Genera el reporte de Personas acorde a los parametros ingresados
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generarReportePersona(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReportePersonaForm viewForm = (ReportePersonaForm)form;
		PersonaFilter filter = this.createFilter(viewForm);
		Collection<Persona> result = personaService.findByFilter(filter);

		String reportType = viewForm.getFormatoReporte();
		JasperPrint jasperPrint = reportePersonaService.generateReportBytes(result);

		this.generateReport(request, response, reportType, jasperPrint);

		return mapping.findForward("null");
	}


	public PersonaFilter createFilter(ReportePersonaForm form) {
		PersonaFilter filter = new PersonaFilter();
		filter.setApellido(form.getApellido());
		filter.setNombre(form.getNombre());
		filter.setCuit(form.getCuit());
		if (form.getNumeroDocumento()!=null && !form.getNumeroDocumento().equals("")){
			filter.setNumeroDocumento(new Integer(form.getNumeroDocumento()));
		}
		return filter;
	}

}
