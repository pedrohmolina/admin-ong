/**
 * 
 */
package com.antares.sirius.view.action;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.antares.commons.util.ReportUtils;
import com.antares.sirius.base.Constants;
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
		
		ReportePersonaForm viewForm = (ReportePersonaForm)form;
		PersonaFilter filter = this.createFilter(viewForm);
		Collection<Persona> result = personaService.findByFilter(filter);
		viewForm.setResult(result);
		return mapping.findForward("verResultados");
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

		DynamicReportBuilder drb = getDynamicReport("SAHDES", "Reporte de Personal", "ReportePersonas");

		//Carga columnas por defecto
		AbstractColumn columnaNombre = 
			getColumn("nombre", String.class, "Nombre", 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaNombre);
		
		AbstractColumn columnaApellido = 
		    getColumn("apellido", String.class, "Apellido", 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaApellido);

		//Carga de columnas seleccionadas por el usuario
		if (viewForm.getVerNumeroDocumento()){
			
			AbstractColumn columnaNumeroDocumento =
			    getColumn("numeroDocumento", Integer.class, "Numero Documento", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);

		}

		if (viewForm.getVerCuit()){
			AbstractColumn columnaCuit = 
			    getColumn("cuit", String.class, "Cuit", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCuit);
		}

		if (viewForm.getVerFechaNacimiento()){
			AbstractColumn columnaFechaNacimiento =
			    getColumn("fechaNacimiento", Date.class, "Fecha Nacimiento", 80, getHeaderStyle(), getDetailStyle());
			columnaFechaNacimiento.setPattern(Constants.DEFAULT_DATE_FORMAT);
	        drb.addColumn(columnaFechaNacimiento);
		}

		if (viewForm.getVerCBU()){
			AbstractColumn columnaCbu = 
			    getColumn("cbu", String.class, "CBU", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCbu);
		}
		
		if (viewForm.getVerProfesion()){
			AbstractColumn columnaProfesion =
			    getColumn("profesion", String.class, "Profesion", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaProfesion);
		}

		if (viewForm.getVerFuncion()){
			AbstractColumn columnaFuncion = 
			    getColumn("funcion", String.class, "Funcion", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaFuncion);
		}

		if (viewForm.getVerEmail()){
			AbstractColumn columnaEmail = 
			    getColumn("email", String.class, "Email", 140, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaEmail);
		}

		if (viewForm.getVerTelefono()){
			AbstractColumn columnaTelefono =
			    getColumn("telefono", String.class, "Telefono", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
		
		if (viewForm.getVerRelacionContractual()){
			AbstractColumn columnaRelacionContractual =
			    getColumn("relacionContractual.descripcion", String.class, "Relacion Contractual", 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaRelacionContractual);
		}

		DynamicReport dr = drb.build(); //Build the report
		
		JRDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		String reportType = viewForm.getFormatoReporte();
		
		//JasperPrint jasperPrint = reportePersonaService.generateReportBytes(result);

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
