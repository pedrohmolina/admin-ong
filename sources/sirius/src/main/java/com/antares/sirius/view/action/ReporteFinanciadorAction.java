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

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.antares.commons.util.Utils;
import com.antares.sirius.filter.FinanciadorFilter;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.service.FinanciadorService;
import com.antares.sirius.view.form.ReporteFinanciadorForm;

/**
 * Controlador correspondiente al reporte de financiadores 
 * 
 * @version 1.0.0 Created by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com>Pablo Delfino</a>

 * @version 1.0.1 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public class ReporteFinanciadorAction extends ReporteAction {

	private static final String FILENAME = "ReporteFinanciadores";

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
		viewForm.setFormatosReporte(getReportFormatList());
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

		DynamicReportBuilder drb = getDynamicReport(Utils.getMessage("sirius.organizacion"), "Reporte de Financiadores", "ReporteFinanciadores");
		addColumns(drb, viewForm);

		DynamicReport dr = drb.build();
		JRDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		Integer reportType = Utils.parseInteger(viewForm.getFormatoReporte());
		this.generateReport(request, response, reportType, jasperPrint);
		return null;
	}

	private void addColumns(DynamicReportBuilder drb, ReporteFinanciadorForm viewForm) throws ColumnBuilderException {
		//Carga columnas por defecto
		AbstractColumn columnaNombre = getColumn("nombre", String.class, Utils.getMessage("sirius.financiador.nombre.label"), 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaNombre);
		
		//Carga de columnas seleccionadas por el usuario
		if (viewForm.getVerTipoFinanciador()) {
			AbstractColumn columnaNumeroDocumento = getColumn("tipoFinanciador.descripcion", String.class, Utils.getMessage("sirius.financiador.tipoFinanciador.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);
		}
		if (viewForm.getVerEstadoFinanciador()) {
			AbstractColumn columnaNumeroDocumento = getColumn("estadoFinanciador.descripcion", String.class, Utils.getMessage("sirius.financiador.estadoFinanciador.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);
		}
		if (viewForm.getVerCuit()) {
			AbstractColumn columnaCuit = getColumn("cuit", String.class, Utils.getMessage("sirius.financiador.cuit.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCuit);
		}
		if (viewForm.getVerCBU()) {
			AbstractColumn columnaCbu = getColumn("cbu", String.class, Utils.getMessage("sirius.financiador.cbu.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCbu);
		}
		if (viewForm.getVerContacto()) {
			AbstractColumn columnaContacto = getColumn("contacto", String.class, Utils.getMessage("sirius.financiador.contacto.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaContacto);
		}
		if (viewForm.getVerDireccion()) {
			AbstractColumn columnaDireccion = getColumn("direccion", String.class, Utils.getMessage("sirius.financiador.direccion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaDireccion);
		}
		if (viewForm.getVerTelefono()) {
			AbstractColumn columnaTelefono = getColumn("telefono", String.class, Utils.getMessage("sirius.financiador.telefono.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
		if (viewForm.getVerEmail()) {
			AbstractColumn columnaTelefono = getColumn("email", String.class, Utils.getMessage("sirius.financiador.email.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
		if (viewForm.getVerObservaciones()) {
			AbstractColumn columnaTelefono = getColumn("observaciones", String.class, Utils.getMessage("sirius.financiador.observaciones.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
	}

	public FinanciadorFilter createFilter(ReporteFinanciadorForm form) {
		FinanciadorFilter filter = new FinanciadorFilter();
		filter.setNombre(form.getNombre());
		filter.setCuit(form.getCuit());
		filter.setCbu(form.getCbu());
		return filter;
	}

	@Override
	protected String getFileName() {
		return FILENAME;
	}

	public void setFinanciadorService(FinanciadorService financiadorService) {
		this.financiadorService = financiadorService;
	}

}
