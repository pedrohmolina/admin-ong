/**
 * 
 */
package com.antares.sirius.view.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DJValueFormatter;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabColumnBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabRowBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;

import com.antares.commons.util.ReportUtils;
import com.antares.sirius.base.Constants;
import com.antares.sirius.filter.ActividadFilter;
import com.antares.sirius.filter.MetaFilter;
import com.antares.sirius.filter.ObjetivoEspecificoFilter;
import com.antares.sirius.filter.ObjetivoGeneralFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.MetaService;
import com.antares.sirius.service.ObjetivoEspecificoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.PresupuestoService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.RubroService;
import com.antares.sirius.view.form.ReporteFinancieroForm;

/**
 * @author PDelfino
 *
 */
public class ReporteFinancieroAction extends ReporteAction{

	private ProyectoService proyectoService;
	private ObjetivoGeneralService objetivoGeneralService;
	private ObjetivoEspecificoService objetivoEspecificoService;
	private MetaService metaService;
	private ActividadService actividadService;
	private RubroService rubroService;
	private PresupuestoService presupuestoService;

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
		form.setProyectos(proyectoService.findAll());
		form.setObjetivosGenerales(objetivoGeneralService.findAll());
		form.setObjetivosEspecificos(objetivoEspecificoService.findAll());
		form.setMetas(metaService.findAll());
		form.setActividades(actividadService.findAll());
		form.setRubros(rubroService.findPrimerNivel());
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
		JasperReport jasperReport = null;
		DynamicReport dr = null;
		Map params = new HashMap();

		ReporteFinancieroForm reporteFinancieroForm = (ReporteFinancieroForm)form;
		
		DynamicReportBuilder drb = getDynamicReport("SAHDES", "Reporte de Finanzas", "ReporteFinanzas");
		
		Proyecto proyecto = proyectoService.findById(new Integer(reporteFinancieroForm.getIdProyecto()));
		drb.setSubtitle("Reporte de Finanzas"+" - "+" Proyecto:"+proyecto.getNombre());
		DJCrosstab djcross = createCrosstab(request);
		
		Collection<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		
		//Obtenemos la lista de actividades
		Collection<Actividad> actividades = this.obtenerListaActividades(reporteFinancieroForm);
		
		Iterator<Actividad> it = actividades.iterator();
		
		while (it.hasNext()){
			Actividad actividad = it.next();
			int size = reporteFinancieroForm.getRubrosSeleccionados().length;
		    for (int i=0; i<size; i++){
		    	//Obtener Rubros seleccionados
		    	String rubroSeleccionadoId = reporteFinancieroForm.getRubrosSeleccionados()[i];
		    	Rubro rubro = rubroService.findById(new Integer(rubroSeleccionadoId));
		    	//Obtener los presupuestos por actividad y rubro
		    	Presupuesto presupuesto = presupuestoService.findPresupuestoByActividadRubro(actividad, rubro);
		    	presupuestos.add(presupuesto);
		    }
			
		}

		drb.addHeaderCrosstab(djcross);
		
		dr = drb.build();
		
		params.put("sr", presupuestos);

		JRDataSource ds = new JRBeanCollectionDataSource(presupuestos);
		
		jasperReport = DynamicJasperHelper.generateJasperReport(dr, new ClassicLayoutManager(), params);
		
		jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds);

		reportType = reporteFinancieroForm.getFormatoReporte();
		
		this.generateReport(request, response, reportType, jasperPrint, Constants.REPORTE_FINANCIERO);

		return mapping.findForward("null");
	}


	private DJCrosstab createCrosstab(HttpServletRequest request) {
		
		MessageResources messageResources = getResources(request);
		
		DJValueFormatter valueFormatter = new DJValueFormatter() {

			DecimalFormat dc = new DecimalFormat("#0.0");
 			
 			public String getClassName() {
 				return String.class.getName();
 			}
 			
 			public Object evaluate(Object value, Map fields, Map variables, Map parameters) {
 				return "" + dc.format(value) + " %";
 			}
 		};

		CrosstabBuilder djcross = new CrosstabBuilder();
		djcross.setHeight(200);
		djcross.setWidth(500);
		djcross.useMainReportDatasource(false);
		djcross.setHeaderStyle(getHeaderStyle());
		djcross.setDatasource("sr",DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true);
		djcross.setUseFullWidth(true);
		djcross.setAutomaticTitle(true);
		djcross.setColumnStyles(getHeaderStyle(), getTotalStyle(), getHeaderStyle());
		djcross.setCellBorder(Border.THIN);
		
		CrosstabRowBuilder row = new CrosstabRowBuilder();
		row.setProperty("actividad.nombre",String.class.getName());
		row.setHeaderWidth(100).setHeight(20);
		row.setTitle(messageResources.getMessage("sirius.entidad.actividad.label")).setShowTotals(true);
		row.setShowTotals(true).setTotalStyle(getTotalStyle());
		row.setTotalHeaderStyle(getHeaderStyle()).setHeaderStyle(getColHeaderStyle());
		
		djcross.addRow(row.build());
		
		CrosstabColumnBuilder col = new CrosstabColumnBuilder().setProperty("rubro.nombre",String.class.getName());
		col.setHeaderHeight(60).setWidth(80);
		col.setTitle(messageResources.getMessage("sirius.entidad.rubro.label")).setShowTotals(true);
		col.setTotalStyle(getTotalStyle()).setTotalHeaderStyle(getHeaderStyle());
		col.setHeaderStyle(getColHeaderStyle());
		djcross.addColumn(col.build());

		djcross.addMeasure("monto",Double.class.getName(), DJCalculation.SUM , 
				messageResources.getMessage("sirius.ingreso.monto.label"),
				new StyleBuilder(false).setPattern("#,###.##")
	 			.setHorizontalAlign(HorizontalAlign.RIGHT)
	 			.setFont(Font.ARIAL_MEDIUM)
				.build());
	

		return djcross.build();
	}


	private Collection<Actividad> obtenerListaActividades(ReporteFinancieroForm reporteFinancieroForm){
	
		Collection<Actividad> actividades = new ArrayList<Actividad>();
		
		if (reporteFinancieroForm.getIdActividad()!=null 
				&& !reporteFinancieroForm.getIdActividad().equals("")){
			
			//Se ha seleccionado una actividad especifica
			Actividad actividad = this.actividadService.findById(
					new Integer(reporteFinancieroForm.getIdActividad()));
			actividades.add(actividad);
			
		}else{
			
			//Se trabajara con todas las actividades de un proyecto/meta/actividad
			if (reporteFinancieroForm.getIdProyecto()!=null 
					&& !reporteFinancieroForm.getIdProyecto().equals("")){
				
				//Se trabajara con las actividades vinculadas a un proyecto especifico
				Proyecto proyecto = this.proyectoService.findById(
						new Integer(reporteFinancieroForm.getIdProyecto()));
				Collection<Actividad> actividadesProyecto = 
					this.actividadService.findAllByProyecto(proyecto);
				
				Iterator<Actividad> it = actividadesProyecto.iterator();
				
				if (reporteFinancieroForm.getIdMeta()!=null 
						&& !reporteFinancieroForm.getIdMeta().equals("")){
					//Se trabajara con las actividades de una meta especifica
					while (it.hasNext()){
						Actividad actividadProyecto = it.next();
						if (actividadProyecto.getMeta().getId().equals(
								reporteFinancieroForm.getIdMeta())){
							actividades.add(actividadProyecto);
						}
					}
					
				}else if (reporteFinancieroForm.getIdObjetivoEspecifico()!=null 
						&& !reporteFinancieroForm.getIdObjetivoEspecifico().equals("")){
					//Se trabajara con las actividades de todas las metas de un objetivo especifico
					while (it.hasNext()){
						Actividad actividadProyecto = it.next();
						if (actividadProyecto.getMeta().getObjetivoEspecifico().getId().equals(
								reporteFinancieroForm.getIdObjetivoEspecifico())){
							actividades.add(actividadProyecto);
						}
					}
					
				}else if (reporteFinancieroForm.getIdObjetivoGeneral()!=null 
						&& !reporteFinancieroForm.getIdObjetivoGeneral().equals("")){
					//Se trabajara con las actividades de todas las metas y objetivos especificos de un objetivo general
					while (it.hasNext()){
						Actividad actividadProyecto = it.next();
						if (actividadProyecto.getMeta().getObjetivoEspecifico().getObjetivoGeneral().getId().equals(
								reporteFinancieroForm.getIdObjetivoGeneral())){
							actividades.add(actividadProyecto);
						}
					}
					
				}else{
					//Se trabajara con todas las actividades del proyecto
					actividades.addAll(actividadesProyecto);
				}
				
			}else {
				//Se trabajara a nivel general de la organizacion, es decir, se mostraran los gastos 
				//generales de todos los proyectos
			}

 		}
		
		return actividades;
	}

	/**
	 * Metodo para cargar los posibles objetivos generales de un proyecto especifico
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboObjetivoGeneral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {

		String id =(String) request.getParameter("idProyecto");
		Collection<ObjetivoGeneral> lista = null;
		if (!"".equals(id)) {
			ObjetivoGeneralFilter filter = this.createObjetivoGeneralFilter(id);
			lista = objetivoGeneralService.findByFilter(filter);
		}
		((ReporteFinancieroForm)form).setObjetivosGenerales(lista);
		
		Map<String, String> map = new HashMap<String, String>();
		for (ObjetivoGeneral objetivoGeneral : lista) {
			map.put(new Integer(objetivoGeneral.getId()).toString(), objetivoGeneral.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	/**
	 * Metodo para cargar los posibles objetivos especificos de un objetivo general especifico
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboObjetivoEspecifico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {

		String id =(String) request.getParameter("idObjetivoGeneral");
		Collection<ObjetivoEspecifico> lista = null;
		if (!"".equals(id)) {
			ObjetivoEspecificoFilter filter = this.createObjetivoEspecificoFilter(id);
			lista = objetivoEspecificoService.findByFilter(filter);
		}
		((ReporteFinancieroForm)form).setObjetivosEspecificos(lista);
		
		Map<String, String> map = new HashMap<String, String>();
		for (ObjetivoEspecifico objetivoEspecifico : lista) {
			map.put(new Integer(objetivoEspecifico.getId()).toString(), objetivoEspecifico.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	/**
	 * Metodo para cargar las posibles metas de una objectivo especifico
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboMeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {

		String id =(String) request.getParameter("idObjetivoEspecifico");
		Collection<Meta> lista = null;
		if (!"".equals(id)) {
			MetaFilter filter = this.createMetaFilter(id);
			lista = metaService.findByFilter(filter);
		}
		((ReporteFinancieroForm)form).setMetas(lista);
		
		Map<String, String> map = new HashMap<String, String>();
		for (Meta meta : lista) {
			map.put(new Integer(meta.getId()).toString(), meta.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	/**
	 * Metodo para cargar las posibles actividades de una meta especifica
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboActividad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {

		String id =(String) request.getParameter("idMeta");
		Collection<Actividad> lista = null;
		if (!"".equals(id)) {
			ActividadFilter filter = this.createActividadFilter(id);
			lista = actividadService.findByFilter(filter);
		}
		((ReporteFinancieroForm)form).setActividades(lista);
		
		Map<String, String> map = new HashMap<String, String>();
		for (Actividad actividad : lista) {
			map.put(new Integer(actividad.getId()).toString(), actividad.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	private ObjetivoGeneralFilter createObjetivoGeneralFilter(String proyecto) {
		ObjetivoGeneralFilter filter = new ObjetivoGeneralFilter();
		filter.setProyecto(proyectoService.findById(Integer.parseInt(proyecto)));
		return filter;
	}

	private ObjetivoEspecificoFilter createObjetivoEspecificoFilter(String objetivoGeneral) {
		ObjetivoEspecificoFilter filter = new ObjetivoEspecificoFilter();
		filter.setObjetivoGeneral(objetivoGeneralService.findById(Integer.parseInt(objetivoGeneral)));
		return filter;
	}

	private MetaFilter createMetaFilter(String objetivoEspecifico) {
		MetaFilter filter = new MetaFilter();
		filter.setObjetivoEspecifico(objetivoEspecificoService.findById(Integer.parseInt(objetivoEspecifico)));
		return filter;
	}
	
	private ActividadFilter createActividadFilter(String meta) {
		ActividadFilter filter = new ActividadFilter();
		filter.setMeta(metaService.findById(Integer.parseInt(meta)));
		return filter;
	}
	
	/**
	 * @return the proyectoService
	 */
	public ProyectoService getProyectoService() {
		return proyectoService;
	}


	/**
	 * @param proyectoService the proyectoService to set
	 */
	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}


	/**
	 * @return the objetivoGeneralService
	 */
	public ObjetivoGeneralService getObjetivoGeneralService() {
		return objetivoGeneralService;
	}


	/**
	 * @param objetivoGeneralService the objetivoGeneralService to set
	 */
	public void setObjetivoGeneralService(
			ObjetivoGeneralService objetivoGeneralService) {
		this.objetivoGeneralService = objetivoGeneralService;
	}


	/**
	 * @return the objetivoEspecificoService
	 */
	public ObjetivoEspecificoService getObjetivoEspecificoService() {
		return objetivoEspecificoService;
	}


	/**
	 * @param objetivoEspecificoService the objetivoEspecificoService to set
	 */
	public void setObjetivoEspecificoService(
			ObjetivoEspecificoService objetivoEspecificoService) {
		this.objetivoEspecificoService = objetivoEspecificoService;
	}


	/**
	 * @return the metaService
	 */
	public MetaService getMetaService() {
		return metaService;
	}


	/**
	 * @param metaService the metaService to set
	 */
	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}


	/**
	 * @return the actividadService
	 */
	public ActividadService getActividadService() {
		return actividadService;
	}


	/**
	 * @param actividadService the actividadService to set
	 */
	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}


	/**
	 * @return the rubroService
	 */
	public RubroService getRubroService() {
		return rubroService;
	}


	/**
	 * @param rubroService the rubroService to set
	 */
	public void setRubroService(RubroService rubroService) {
		this.rubroService = rubroService;
	}


	/**
	 * @return the presupuestoService
	 */
	public PresupuestoService getPresupuestoService() {
		return presupuestoService;
	}


	/**
	 * @param presupuestoService the presupuestoService to set
	 */
	public void setPresupuestoService(PresupuestoService presupuestoService) {
		this.presupuestoService = presupuestoService;
	}

	

}
