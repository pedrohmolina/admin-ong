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
import com.antares.sirius.base.Constants;
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
		
		ReporteProveedorForm viewForm = (ReporteProveedorForm)form;
		ProveedorFilter filter = this.createFilter(viewForm);
		Collection<Proveedor> result = proveedorService.findByFilter(filter);
		viewForm.setResult(result);
		return mapping.findForward("verResultados");
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

		DynamicReportBuilder drb = getDynamicReport("SAHDES", "Reporte de Proveedores", "ReporteProveedores");
		
		MessageResources messageResources = getResources(request);
		
		//Carga columnas por defecto
		AbstractColumn columnaNombre = 
			getColumn("nombre", String.class, 
					messageResources.getMessage("sirius.proveedor.nombre.label"), 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaNombre);
		
		//Carga de columnas seleccionadas por el usuario
		if (viewForm.getVerTipoProveedor()){
			
			AbstractColumn columnaNumeroDocumento =
			    getColumn("tipoProveedor.descripcion", String.class, 
			    	messageResources.getMessage("sirius.proveedor.tipoProveedor.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);

		}

		if (viewForm.getVerCuit()){
			AbstractColumn columnaCuit = 
			    getColumn("cuit", String.class, 
			    	messageResources.getMessage("sirius.proveedor.cuit.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCuit);
		}

		if (viewForm.getVerCBU()){
			AbstractColumn columnaCbu = 
			    getColumn("cbu", String.class, 
			    	messageResources.getMessage("sirius.proveedor.cbu.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCbu);
		}

		if (viewForm.getVerContacto()){
			AbstractColumn columnaContacto = 
			    getColumn("contacto", String.class, 
			    	messageResources.getMessage("sirius.proveedor.contacto.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaContacto);
		}
		
		if (viewForm.getVerDireccion()){
			AbstractColumn columnaDireccion = 
			    getColumn("direccion", String.class, 
			    	messageResources.getMessage("sirius.proveedor.direccion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaDireccion);
		}

		if (viewForm.getVerTelefono()){
			AbstractColumn columnaTelefono = 
			    getColumn("telefono", String.class, 
			    	messageResources.getMessage("sirius.proveedor.telefono.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}

		if (viewForm.getVerEmail()){
			AbstractColumn columnaTelefono = 
			    getColumn("email", String.class, 
			    	messageResources.getMessage("sirius.proveedor.email.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}

		if (viewForm.getVerObservaciones()){
			AbstractColumn columnaTelefono = 
			    getColumn("observaciones", String.class, 
			    	messageResources.getMessage("sirius.proveedor.observaciones.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}

		DynamicReport dr = drb.build(); //Build the report
		
		JRDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		String reportType = viewForm.getFormatoReporte();
		
		this.generateReport(request, response, reportType, jasperPrint, Constants.REPORTE_PROVEEDORES);
	
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
