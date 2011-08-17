/**
 * 
 */
package com.antares.sirius.view.action;

import static ar.com.fdvs.dj.core.DJConstants.DATA_SOURCE_ORIGIN_PARAMETER;
import static ar.com.fdvs.dj.core.DJConstants.DATA_SOURCE_TYPE_COLLECTION;
import static ar.com.fdvs.dj.domain.DJCalculation.SUM;
import static ar.com.fdvs.dj.domain.constants.Border.NO_BORDER;
import static ar.com.fdvs.dj.domain.constants.Border.THIN;
import static ar.com.fdvs.dj.domain.constants.Font.ARIAL_MEDIUM;
import static ar.com.fdvs.dj.domain.constants.Font._FONT_VERDANA;
import static ar.com.fdvs.dj.domain.constants.HorizontalAlign.CENTER;
import static ar.com.fdvs.dj.domain.constants.HorizontalAlign.RIGHT;
import static ar.com.fdvs.dj.domain.constants.Transparency.OPAQUE;
import static java.awt.Color.LIGHT_GRAY;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRGraphicElement;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DJGroupLabel;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabColumnBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabRowBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

import com.antares.commons.enums.TipoAgregacionEnum;
import com.antares.commons.util.Utils;
import com.antares.sirius.dto.FinanzasDTO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.PersistentObject;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.FinanzasService;
import com.antares.sirius.service.MetaService;
import com.antares.sirius.service.ObjetivoEspecificoService;
import com.antares.sirius.service.ObjetivoGeneralService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.RubroService;
import com.antares.sirius.view.form.ReporteFinancieroForm;

/**
 * Controlador correspondiente al reporte de finanzas 
 * 
 * @version 1.0.0 Created by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com>Pablo Delfino</a>

 * @version 2.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public class ReporteFinancieroAction extends ReporteAction {

	private static final String COL_DESCRIPCION = "descripcion";
	private static final String COL_RUBRO = "rubro";
	private static final String SUFIJO_PRESUPUESTADO = "-a";
	private static final String SUFIJO_GASTADO = "-b";
	private static final String SUFIJO_DIFERENCIA = "-c";
	private static final String FILENAME = "ReporteFinanzas";

	private ProyectoService proyectoService;
	private ObjetivoGeneralService objetivoGeneralService;
	private ObjetivoEspecificoService objetivoEspecificoService;
	private MetaService metaService;
	private ActividadService actividadService;
	private RubroService rubroService;
	private FinanzasService finanzasService;

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
		viewForm.setFormatosReporte(getReportFormatList());
		viewForm.setProyectos(proyectoService.findAll());
		viewForm.setRubros(rubroService.findPrimerNivel());
		return mapping.findForward("init");
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
		ReporteFinancieroForm reporteForm = (ReporteFinancieroForm)form;
		Collection<Rubro> rubros = findRubros(reporteForm.getRubrosSeleccionados());

		Proyecto proyecto = proyectoService.findById(Utils.parseInteger(reporteForm.getIdProyecto()));
		FinanzasDTO finanzas = getFinanzas(reporteForm, proyecto, rubros.toArray(new Rubro[0]));

		DynamicReportBuilder drb = getDynamicReport(Utils.getMessage("sirius.organizacion"), Utils.getMessage("sirius.reportes.finanzas.title"), "ReporteFinanzas");
		drb.setSubtitle(Utils.getMessage("sirius.reportes.finanzas.title") + " - " + Utils.getMessage("sirius.reportes.finanzas.proyecto.label") + ": " + proyecto.getNombre());
		addColumns(drb, rubros, reporteForm);

		DynamicReport dr = drb.build();
		Collection<PersistentObject> persistentObjects = getPersistentObjects(reporteForm, proyecto);
		JRDataSource ds = buildDataSource(finanzas, persistentObjects, rubros, reporteForm);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

		Integer reportType = Utils.parseInteger(reporteForm.getFormatoReporte());
		this.generateReport(request, response, reportType, jasperPrint);
		return null;
	}

	private Collection<Rubro> findRubros(Integer[] idRubros) {
		Collection<Rubro> rubros = null;

		// Es es porque, por defecto, el array siempre contiene el id 0 (que no corresponde a ningun rubro)
		// Se hizo asi para que siempre se submitee un valor, porque si no se selecciona ninguno, quedan los valores que estaban en la sesion
		if (idRubros.length == 1) {
			rubros = rubroService.findPrimerNivel();
		} else {
			rubros = rubroService.findByIds(idRubros);
		}
		return rubros;
	}
	/*
	 * Agrega las columnas necesarias al reporte
	 */
	private void addColumns(DynamicReportBuilder drb, Collection<Rubro> rubros, ReporteFinancieroForm reporteForm) throws ColumnBuilderException {

		String tituloActividad = Utils.getMessage("sirius.reportes.finanzas.actividad.label");
		String tituloRubro = Utils.getMessage("sirius.reportes.finanzas.rubrosPorActividad.label");
		String tituloPresupuestado = Utils.getMessage("sirius.reportes.finanzas.presupuestado.label"); 
		String tituloGastado = Utils.getMessage("sirius.reportes.finanzas.gastado.label"); 
		String tituloDiferencia = Utils.getMessage("sirius.reportes.finanzas.diferencia.label"); 
		
 		Style headerVariables = new Style();
 		headerVariables.setFont(Font.VERDANA_MEDIUM_BOLD);
 		headerVariables.setBackgroundColor(Color.LIGHT_GRAY);
 		headerVariables.setTransparency(OPAQUE);
 		headerVariables.setBorderBottom(Border.PEN_1_POINT);
 		headerVariables.setHorizontalAlign(HorizontalAlign.CENTER);
 		headerVariables.setVerticalAlign(VerticalAlign.MIDDLE);
 		headerVariables.setOverridesExistingStyle(true);

 		
		AbstractColumn columnaDescripcion = getColumn(COL_DESCRIPCION, String.class, tituloActividad, 80, getHeaderStyle(), headerVariables);
		drb.addColumn(columnaDescripcion);
		AbstractColumn columnaRubro = getColumn(COL_RUBRO, String.class, tituloRubro, 80, getHeaderStyle(), null);
		drb.addColumn(columnaRubro);
	
		AbstractColumn columnaPresupuestado = null;
		AbstractColumn columnaGastado = null;
		AbstractColumn columnaDif = null;
		
		if (reporteForm.getVerPresupuestado()) {
			columnaPresupuestado = getColumn(SUFIJO_PRESUPUESTADO, Double.class, tituloPresupuestado, 80, getHeaderStyle(), null);
			drb.addColumn(columnaPresupuestado);
		}
		if (reporteForm.getVerGastado()) {
			columnaGastado = getColumn(SUFIJO_GASTADO, Double.class, tituloGastado, 80, getHeaderStyle(), null);
			drb.addColumn(columnaGastado);
		}
		if (reporteForm.getVerDiferencia()) {
			columnaDif = getColumn(SUFIJO_DIFERENCIA, Double.class, tituloDiferencia, 80, getHeaderStyle(), null);
			drb.addColumn(columnaDif);
		}

		GroupBuilder gb1 = new GroupBuilder();
  		
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) columnaDescripcion)
	  		.addHeaderVariable(columnaPresupuestado, DJCalculation.SUM, headerVariables)
	  		.addHeaderVariable(columnaGastado, DJCalculation.SUM, headerVariables) 
	  		.addHeaderVariable(columnaDif, DJCalculation.SUM, headerVariables)
	        .setGroupLayout(GroupLayout.VALUE_IN_HEADER) 
	        .build();

  		drb.addGroup(g1);
  		drb.setPrintBackgroundOnOddRows(false);

	}

	/*
	 * Construye un data source para jasper en base a las finanzas, los rubros y los objetos del nivel de agregacion correspondiente
	 */
	private JRDataSource buildDataSource(FinanzasDTO finanzas, Collection<PersistentObject> persistentObjects, Collection<Rubro> rubros, ReporteFinancieroForm reporteForm) {

		Collection<Map<String, Object>> col = new ArrayList<Map<String, Object>>(); 
		for (PersistentObject persistentObject : persistentObjects) {
			for (Rubro rubro : rubros) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(COL_DESCRIPCION, Utils.getPropertyValue(persistentObject, "nombre").toString());
				map.put(COL_RUBRO, rubro.getNombre());
				MontoDTO monto = finanzas.get(persistentObject.getId(), rubro.getId());
				if (monto != null) {
					if (reporteForm.getVerPresupuestado()) {
						map.put(SUFIJO_PRESUPUESTADO, monto.getMontoPresupuestado());
					}
					if (reporteForm.getVerGastado()) {
						map.put(SUFIJO_GASTADO, monto.getMontoGastado());
					}
					if (reporteForm.getVerDiferencia()) {
						map.put(SUFIJO_DIFERENCIA, monto.getMontoDif());
					}
				} else {
					if (reporteForm.getVerPresupuestado()) {
						map.put(SUFIJO_PRESUPUESTADO, Double.valueOf(0D));
					}
					if (reporteForm.getVerGastado()) {
						map.put(SUFIJO_GASTADO, Double.valueOf(0D));
					}
					if (reporteForm.getVerDiferencia()) {
						map.put(SUFIJO_DIFERENCIA, Double.valueOf(0D));
					}
				}
				col.add(map);
			}
		}

		return new JRMapCollectionDataSource(col);
	}

	/*
	 * Obtiene las finanzas correspondientes al nivel de agregacion indicado en el reporteForm
	 */
	private FinanzasDTO getFinanzas(ReporteFinancieroForm reporteForm, Proyecto proyecto, Rubro[] rubros) {
		TipoAgregacionEnum tipoAgregacion = TipoAgregacionEnum.findByNombre(reporteForm.getTipoAgregacion());
		FinanzasDTO finanzas = null;
		switch (tipoAgregacion) {
			case ACTIVIDAD:
				Actividad actividad = actividadService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				finanzas = finanzasService.finanzasPorActividad(actividad, rubros, reporteForm.calcularPresupuestos(), reporteForm.calcularGastos());
				break;
			case META:
				Meta meta = metaService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				finanzas = finanzasService.finanzasPorMeta(meta, rubros, reporteForm.calcularPresupuestos(), reporteForm.calcularGastos());
				break;
			case OBJETIVO_ESPECIFICO:
				ObjetivoEspecifico objetivoEspecifico = objetivoEspecificoService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				finanzas = finanzasService.finanzasPorObjetivoEspecifico(objetivoEspecifico, rubros, reporteForm.calcularPresupuestos(), reporteForm.calcularGastos());
				break;
			case OBJETIVO_GENERAL:
				ObjetivoGeneral objetivoGeneral = objetivoGeneralService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				finanzas = finanzasService.finanzasPorObjetivoGeneral(objetivoGeneral, rubros, reporteForm.calcularPresupuestos(), reporteForm.calcularGastos());
				break;
			case PROYECTO:
				finanzas = finanzasService.finanzasPorProyecto(proyecto, rubros, reporteForm.calcularPresupuestos(), reporteForm.calcularGastos());
				break;
			default: break;
		}
		return finanzas;
	}

	/*
	 * Obtiene la lista de objetos persistentes correspondientes al nivel de agregacion indicado en el reporteForm
	 */
	private Collection<PersistentObject> getPersistentObjects(ReporteFinancieroForm reporteForm, Proyecto proyecto) {
		TipoAgregacionEnum tipoAgregacion = TipoAgregacionEnum.findByNombre(reporteForm.getTipoAgregacion());
		Collection<PersistentObject> persistentObject= new ArrayList<PersistentObject>();
		switch (tipoAgregacion) {
			case ACTIVIDAD:
				Actividad actividad = actividadService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				persistentObject.add(actividad);
				break;
			case META:
				Meta meta = metaService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				persistentObject.addAll(meta.getActividades());
				break;
			case OBJETIVO_ESPECIFICO:
				ObjetivoEspecifico objetivoEspecifico = objetivoEspecificoService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				persistentObject.addAll(objetivoEspecifico.getMetas());
				break;
			case OBJETIVO_GENERAL:
				ObjetivoGeneral objetivoGeneral = objetivoGeneralService.findById(Integer.parseInt(reporteForm.getIdAgregacion()));
				persistentObject.addAll(objetivoGeneral.getObjetivosEspecificos());
				break;
			case PROYECTO:
				persistentObject.addAll(proyecto.getObjetivosGenerales());
				break;
			default: break;
		}
		return persistentObject;
	}

	/*
	 * TODO borrar
	 * Este metodo lo dej� para tener de ejemple del uso de crosstabs
	 */
	private DJCrosstab createCrosstab(HttpServletRequest request) {
		
		MessageResources messageResources = getResources(request);
		
		CrosstabBuilder djcross = new CrosstabBuilder();
		djcross.setHeight(200);
		djcross.setWidth(500);
		djcross.useMainReportDatasource(false);
		djcross.setHeaderStyle(getHeaderStyle());
		djcross.setDatasource("sr",DATA_SOURCE_ORIGIN_PARAMETER, DATA_SOURCE_TYPE_COLLECTION, true);
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

		djcross.addMeasure("monto",Double.class.getName(), SUM , 
				messageResources.getMessage("sirius.ingreso.monto.label"),
				new StyleBuilder(false).setPattern("#,###.##")
	 			.setHorizontalAlign(RIGHT)
	 			.setFont(ARIAL_MEDIUM)
				.build());
	

		return djcross.build();
	}

	/**
	 * Metodo para cargar los posibles objetivos generales del proyecto
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
		if (Utils.isNotNullNorEmpty(id)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
			lista = objetivoGeneralService.findAllByProyecto(proyecto);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		for (ObjetivoGeneral objetivoGeneral : lista) {
			map.put(new Integer(objetivoGeneral.getId()).toString(), objetivoGeneral.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}

	/**
	 * Metodo para cargar los posibles objetivos especificos del proyecto
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboObjetivoEspecifico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		String id =(String) request.getParameter("idProyecto");
		Collection<ObjetivoEspecifico> lista = null;
		if (Utils.isNotNullNorEmpty(id)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
			lista = objetivoEspecificoService.findAllByProyecto(proyecto);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		for (ObjetivoEspecifico objetivoEspecifico : lista) {
			map.put(new Integer(objetivoEspecifico.getId()).toString(), objetivoEspecifico.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	/**
	 * Metodo para cargar las posibles metas del proyecto
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboMeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		String id =(String) request.getParameter("idProyecto");
		Collection<Meta> lista = null;
		if (Utils.isNotNullNorEmpty(id)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
			lista = metaService.findAllByProyecto(proyecto);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		for (Meta meta : lista) {
			map.put(new Integer(meta.getId()).toString(), meta.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	/**
	 * Metodo para cargar las posibles actividades del proyecto
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarComboActividad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		String id =(String) request.getParameter("idProyecto");
		Collection<Actividad> lista = null;
		if (Utils.isNotNullNorEmpty(id)) {
			Proyecto proyecto = proyectoService.findById(Utils.parseInteger(id));
			lista = actividadService.findAllByProyecto(proyecto);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		for (Actividad actividad : lista) {
			map.put(new Integer(actividad.getId()).toString(), actividad.getNombre());
		}
		
		sendJSON(response, map);
		return null;

	}
	
	@Override
	protected String getFileName() {
		return FILENAME;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setObjetivoGeneralService(ObjetivoGeneralService objetivoGeneralService) {
		this.objetivoGeneralService = objetivoGeneralService;
	}

	public void setObjetivoEspecificoService(ObjetivoEspecificoService objetivoEspecificoService) {
		this.objetivoEspecificoService = objetivoEspecificoService;
	}

	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

	public void setRubroService(RubroService rubroService) {
		this.rubroService = rubroService;
	}

	public void setFinanzasService(FinanzasService finanzasService) {
		this.finanzasService = finanzasService;
	}

}
