/**
 * 
 */
package com.antares.sirius.view.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

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
	 * Genera una vista previa de los resultados a ser visualizados en el reporte
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward verResultados(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReporteFinanciadorForm viewForm = (ReporteFinanciadorForm)form;
		FinanciadorFilter filter = this.createFilter(viewForm);
		Collection<Financiador> result = financiadorService.findByFilter(filter);
		viewForm.setResult(result);
		return mapping.findForward("verResultados");
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

		DynamicReportBuilder drb = getDynamicReport("SAHDES", "Reporte de Financiadores", "ReporteFinanciadores");
		
		MessageResources messageResources = getResources(request);
		
		//Carga columnas por defecto
		AbstractColumn columnaNombre = 
			getColumn("nombre", String.class, 
					messageResources.getMessage("sirius.financiador.nombre.label"), 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaNombre);
		
		//Carga de columnas seleccionadas por el usuario
		if (viewForm.getVerTipoFinanciador()){
			
			AbstractColumn columnaNumeroDocumento =
			    getColumn("tipoFinanciador.descripcion", String.class, 
			    	messageResources.getMessage("sirius.financiador.tipoFinanciador.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);

		}

		if (viewForm.getVerEstadoFinanciador()){
			
			AbstractColumn columnaNumeroDocumento =
			    getColumn("estadoFinanciador.descripcion", String.class, 
			    	messageResources.getMessage("sirius.financiador.estadoFinanciador.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);

		}

		if (viewForm.getVerCuit()){
			AbstractColumn columnaCuit = 
			    getColumn("cuit", String.class, 
			    	messageResources.getMessage("sirius.financiador.cuit.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCuit);
		}

		if (viewForm.getVerCBU()){
			AbstractColumn columnaCbu = 
			    getColumn("cbu", String.class, 
			    	messageResources.getMessage("sirius.financiador.cbu.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCbu);
		}

		if (viewForm.getVerContacto()){
			AbstractColumn columnaContacto = 
			    getColumn("contacto", String.class, 
			    	messageResources.getMessage("sirius.financiador.contacto.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaContacto);
		}
		
		if (viewForm.getVerDireccion()){
			AbstractColumn columnaDireccion = 
			    getColumn("direccion", String.class, 
			    	messageResources.getMessage("sirius.financiador.direccion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaDireccion);
		}

		if (viewForm.getVerTelefono()){
			AbstractColumn columnaTelefono = 
			    getColumn("telefono", String.class, 
			    	messageResources.getMessage("sirius.financiador.telefono.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}

		if (viewForm.getVerEmail()){
			AbstractColumn columnaTelefono = 
			    getColumn("email", String.class, 
			    	messageResources.getMessage("sirius.financiador.email.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}

		if (viewForm.getVerObservaciones()){
			AbstractColumn columnaTelefono = 
			    getColumn("observaciones", String.class, 
			    	messageResources.getMessage("sirius.financiador.observaciones.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}

		DynamicReport dr = drb.build(); //Build the report
		
		JRDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		String reportType = viewForm.getFormatoReporte();
		
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
