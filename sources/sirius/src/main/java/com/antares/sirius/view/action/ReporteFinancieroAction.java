/**
 * 
 */
package com.antares.sirius.view.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.MetaService;
import com.antares.sirius.service.ObjetivoEspecificoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.ReporteFinancieroService;
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
		
		//Obtener Rubros seleccionados
		/*
		int size = reporteFinancieroForm.getRubrosSeleccionados().length;
	    for (int i=0; i<size; i++)
	    {
	      System.out.println(reporteFinancieroForm.getRubrosSeleccionados()[i]);
	    }
		*/
		//this.generateReport(request, response, reportType, jasperPrint);

		return mapping.findForward("null");
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

	

}
