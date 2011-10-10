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
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.antares.commons.util.Utils;
import com.antares.sirius.base.Constants;
import com.antares.sirius.filter.PersonaFilter;
import com.antares.sirius.model.Persona;
import com.antares.sirius.service.PersonaService;
import com.antares.sirius.service.RelacionContractualService;
import com.antares.sirius.view.form.ReportePersonaForm;

/**
 * Controlador correspondiente al reporte de personas 
 * 
 * @version 1.0.0 Created by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com>Pablo Delfino</a>

 * @version 1.0.1 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public class ReportePersonaAction extends ReporteAction {

	private static final String FILENAME = "ReportePersonas";
	private PersonaService personaService;
	private RelacionContractualService relacionContractualService;
	
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
		loadCollections(viewForm);
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

		DynamicReportBuilder drb = getDynamicReport(Utils.getMessage("sirius.organizacion"), "Reporte de Personal", "ReportePersonas");
		addColumns(drb, viewForm);

		DynamicReport dr = drb.build();
		JRDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		Integer reportType = Utils.parseInteger(viewForm.getFormatoReporte());
		this.generateReport(request, response, reportType, jasperPrint);
		return null;
	}

	private void addColumns(DynamicReportBuilder drb, ReportePersonaForm viewForm) throws ColumnBuilderException {
		//Carga columnas por defecto
		AbstractColumn columnaNombre = getColumn("nombre", String.class, Utils.getMessage("sirius.persona.nombre.label"), 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaNombre);
		AbstractColumn columnaApellido = getColumn("apellido", String.class, Utils.getMessage("sirius.persona.apellido.label"), 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaApellido);

		//Carga de columnas seleccionadas por el usuario
		if (viewForm.getVerNumeroDocumento()) {
			AbstractColumn columnaNumeroDocumento = getColumn("numeroDocumento", Integer.class, Utils.getMessage("sirius.persona.numeroDocumento.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);
		}
		if (viewForm.getVerCuit()) {
			AbstractColumn columnaCuit = getColumn("cuit", String.class, Utils.getMessage("sirius.persona.cuit.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCuit);
		}
		if (viewForm.getVerCBU()) {
			AbstractColumn columnaCbu = getColumn("cbu", String.class, Utils.getMessage("sirius.persona.cbu.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCbu);
		}
		if (viewForm.getVerFechaNacimiento()) {
			AbstractColumn columnaFechaNacimiento = getColumn("fechaNacimiento", Date.class, Utils.getMessage("sirius.persona.fechaNacimiento.label"), 80, getHeaderStyle(), getDetailStyle());
			columnaFechaNacimiento.setPattern(Constants.DEFAULT_DATE_FORMAT);
	        drb.addColumn(columnaFechaNacimiento);
		}
		if (viewForm.getVerDireccion()) {
			AbstractColumn columnaDireccion = getColumn("direccion", String.class, Utils.getMessage("sirius.persona.direccion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaDireccion);
		}
		if (viewForm.getVerProfesion()) {
			AbstractColumn columnaProfesion = getColumn("profesion", String.class, Utils.getMessage("sirius.persona.profesion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaProfesion);
		}
		if (viewForm.getVerTelefono()) {
			AbstractColumn columnaTelefono = getColumn("telefono", String.class, Utils.getMessage("sirius.persona.telefono.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
		if (viewForm.getVerEmail()) {
			AbstractColumn columnaEmail = getColumn("email", String.class, Utils.getMessage("sirius.persona.email.label"), 140, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaEmail);
		}
		if (viewForm.getVerFuncion()) {
			AbstractColumn columnaFuncion = getColumn("funcion", String.class, Utils.getMessage("sirius.persona.funcion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaFuncion);
		}
		if (viewForm.getVerRelacionContractual()) {
			AbstractColumn columnaRelacionContractual = getColumn("relacionContractual.descripcion", String.class, Utils.getMessage("sirius.persona.relacionContractual.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaRelacionContractual);
		}
	}

	public PersonaFilter createFilter(ReportePersonaForm form) {
		PersonaFilter filter = new PersonaFilter();
		filter.setApellido(form.getApellido());
		filter.setNombre(form.getNombre());
		if (Utils.isNotNullNorEmpty(form.getIdRelacionContractual())) {
			filter.setRelacionContractual(relacionContractualService.findById(Utils.parseInteger(form.getIdRelacionContractual())));
		}
		return filter;
	}

	protected void loadCollections(ReportePersonaForm form) {
		form.setRelacionesContractuales(relacionContractualService.findAll());
		form.setFormatosReporte(getReportFormatList());
	}

	@Override
	protected String getFileName() {
		return FILENAME;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setRelacionContractualService(RelacionContractualService relacionContractualService) {
		this.relacionContractualService = relacionContractualService;
	}
	
}
