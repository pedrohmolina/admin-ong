/**
 * 
 */
package com.antares.sirius.view.action;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DJCrosstabColumn;
import ar.com.fdvs.dj.domain.DJCrosstabRow;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabColumnBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabRowBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

import com.antares.commons.util.ReportUtils;
import com.antares.commons.util.Utils;
import com.antares.sirius.filter.ActividadFilter;
import com.antares.sirius.filter.MetaFilter;
import com.antares.sirius.filter.ObjetivoEspecificoFilter;
import com.antares.sirius.filter.ObjetivoGeneralFilter;
import com.antares.sirius.filter.ProyectoFilter;
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
import com.antares.sirius.service.ReporteFinancieroService;
import com.antares.sirius.service.RubroService;
import com.antares.sirius.view.form.ActividadForm;
import com.antares.sirius.view.form.AsignacionForm;
import com.antares.sirius.view.form.ReporteFinancieroForm;

/**
 * @author PDelfino
 *
 */
public class ReporteFinancieroAction extends ReporteAction{

	private ReporteFinancieroService reporteFinancieroService;

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
		//TODO - Completar
		ReporteFinancieroForm reporteFinancieroForm = (ReporteFinancieroForm)form;
		
		/*
		FastReportBuilder drb = new FastReportBuilder();
		drb.setTitle("Reporte de Finanzas");
		drb.setSubtitle("Reporte generado el dia " + new Date());
		drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		drb.setPrintColumnNames(false);
		drb.setUseFullPageWidth(true);
		
		DJCrosstab djcross = createCrosstab();
		*/
		
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
		/*
		drb.addHeaderCrosstab(djcross);
		
		DynamicReport dr = drb.build();
		
		JRDataSource ds = new JRBeanCollectionDataSource(presupuestos);
		jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		reportType = reporteFinancieroForm.getFormatoReporte();
		*/
		jasperPrint = reporteFinancieroService.generateReportBytes(presupuestos);

		this.generateReport(request, response, reportType, jasperPrint);

		return mapping.findForward("null");
	}

	private DJCrosstab createCrosstab() {
		
		CrosstabBuilder cb = new CrosstabBuilder();
		cb.setHeight(200);
		cb.setWidth(500);
		//cb.setHeaderStyle(mainHeaderStyle);
		cb.setDatasource("sr",DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION);
		cb.setUseFullWidth(true);
		cb.setColorScheme(4);
		cb.setAutomaticTitle(true);
		cb.setCellBorder(Border.THIN);
		
		cb.addMeasure("monto",Double.class.getName(), DJCalculation.SUM , "Monto",new StyleBuilder(false).setPattern("#,###.##")
				 			.setHorizontalAlign(HorizontalAlign.RIGHT)
				 			.setFont(Font.ARIAL_MEDIUM)
							.build());
		
		CrosstabRowBuilder row = new CrosstabRowBuilder();
		row.setProperty("actividad.nombre",String.class.getName());
		row.setHeaderWidth(100).setHeight(20);
		row.setTitle("Actividad");
		//row.setShowTotals(true).setTotalStyle(totalStyle);
		//row.setTotalHeaderStyle(totalHeader).setHeaderStyle(colAndRowHeaderStyle);
		
		cb.addRow(row.build());
		
		CrosstabColumnBuilder col = new CrosstabColumnBuilder().setProperty("rubro.nombre",String.class.getName());
		col.setHeaderHeight(60).setWidth(80);
		col.setTitle("Rubro").setShowTotals(true);
		//col.setTotalStyle(totalStyle).setTotalHeaderStyle(totalHeader);
		//col.setHeaderStyle(colAndRowHeaderStyle);
		cb.addColumn(col.build());
		
		return cb.build();
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
	
 	private void initStyles() {
 		Style totalHeader = new StyleBuilder(false)
 			.setHorizontalAlign(HorizontalAlign.CENTER)
 			.setVerticalAlign(VerticalAlign.MIDDLE)
 			.setFont(Font.ARIAL_MEDIUM_BOLD)
 			.setTextColor(Color.BLUE)
 			.build();
 		Style colAndRowHeaderStyle = new StyleBuilder(false)
 			.setHorizontalAlign(HorizontalAlign.LEFT)
 			.setVerticalAlign(VerticalAlign.TOP)
 			.setFont(Font.ARIAL_MEDIUM_BOLD)
 			.build();
 		Style mainHeaderStyle = new StyleBuilder(false)
 			.setHorizontalAlign(HorizontalAlign.CENTER)
 			.setVerticalAlign(VerticalAlign.MIDDLE)
 			.setFont(Font.ARIAL_BIG_BOLD)
 			.setTextColor(Color.BLACK)
 			.build();
 		Style totalStyle = new StyleBuilder(false).setPattern("#,###.##")
 			.setHorizontalAlign(HorizontalAlign.RIGHT)
 			.setFont(Font.ARIAL_MEDIUM_BOLD)
 			.build();
 		Style measureStyle = new StyleBuilder(false).setPattern("#,###.##")
 			.setHorizontalAlign(HorizontalAlign.RIGHT)
 			.setFont(Font.ARIAL_MEDIUM)
 			.build();
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
