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
import com.antares.sirius.filter.ProveedorFilter;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.service.ProveedorService;
import com.antares.sirius.service.TipoProveedorService;
import com.antares.sirius.view.form.ReporteProveedorForm;

/**
 * Controlador correspondiente al reporte de proveedores 
 * 
 * @version 1.0.0 Created by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com>Pablo Delfino</a>

 * @version 1.0.1 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public class ReporteProveedorAction extends ReporteAction {

	private static final String FILENAME = "ReporteProveedores";

	private ProveedorService proveedorService;
	private TipoProveedorService tipoProveedorService;
	
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
		viewForm.setFormatosReporte(getReportFormatList());
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
		ReporteProveedorForm viewForm = (ReporteProveedorForm)form;
		ProveedorFilter filter = this.createFilter(viewForm);
		Collection<Proveedor> result = proveedorService.findByFilter(filter);
		viewForm.setResult(result);
		loadCollections(viewForm);
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

		DynamicReportBuilder drb = getDynamicReport(Utils.getMessage("sirius.organizacion"), "Reporte de Proveedores", "ReporteProveedores");
		addColumns(drb, viewForm);

		DynamicReport dr = drb.build();
		JRDataSource ds = new JRBeanCollectionDataSource(result);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		Integer reportType = Utils.parseInteger(viewForm.getFormatoReporte());
		this.generateReport(request, response, reportType, jasperPrint);
		return null;
	}

	private void addColumns(DynamicReportBuilder drb, ReporteProveedorForm viewForm) throws ColumnBuilderException {
		//Carga columnas por defecto
		AbstractColumn columnaNombre = getColumn("nombre", String.class, Utils.getMessage("sirius.proveedor.nombre.label"), 80, getHeaderStyle(), getDetailStyle());
		drb.addColumn(columnaNombre);
		
		//Carga de columnas seleccionadas por el usuario
		if (viewForm.getVerTipoProveedor()) {
			AbstractColumn columnaNumeroDocumento = getColumn("tipoProveedor.descripcion", String.class, Utils.getMessage("sirius.proveedor.tipoProveedor.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaNumeroDocumento);
		}
		if (viewForm.getVerCuit()) {
			AbstractColumn columnaCuit = getColumn("cuit", String.class, Utils.getMessage("sirius.proveedor.cuit.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCuit);
		}
		if (viewForm.getVerCBU()) {
			AbstractColumn columnaCbu = getColumn("cbu", String.class, Utils.getMessage("sirius.proveedor.cbu.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaCbu);
		}
		if (viewForm.getVerContacto()) {
			AbstractColumn columnaContacto = getColumn("contacto", String.class, Utils.getMessage("sirius.proveedor.contacto.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaContacto);
		}
		if (viewForm.getVerDireccion()) {
			AbstractColumn columnaDireccion = getColumn("direccion", String.class, Utils.getMessage("sirius.proveedor.direccion.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaDireccion);
		}
		if (viewForm.getVerTelefono()) {
			AbstractColumn columnaTelefono = getColumn("telefono", String.class, Utils.getMessage("sirius.proveedor.telefono.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
		if (viewForm.getVerEmail()) {
			AbstractColumn columnaTelefono = getColumn("email", String.class, Utils.getMessage("sirius.proveedor.email.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
		if (viewForm.getVerObservaciones()) {
			AbstractColumn columnaTelefono = getColumn("observaciones", String.class, Utils.getMessage("sirius.proveedor.observaciones.label"), 80, getHeaderStyle(), getDetailStyle());
			drb.addColumn(columnaTelefono);
		}
	}

	public ProveedorFilter createFilter(ReporteProveedorForm form) {
		ProveedorFilter filter = new ProveedorFilter();
		filter.setNombre(form.getNombre());

		if (Utils.isNotNullNorEmpty(form.getIdTipoProveedor())) {
			filter.setTipoProveedor(tipoProveedorService.findById(Utils.parseInteger(form.getIdTipoProveedor())));
		}
		return filter;
	}

	protected void loadCollections(ReporteProveedorForm form) {
		form.setTiposProveedor(tipoProveedorService.findAll());		
	}

	@Override
	protected String getFileName() {
		return FILENAME;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public void setTipoProveedorService(TipoProveedorService tipoProveedorService) {
		this.tipoProveedorService = tipoProveedorService;
	}
	
}
