package com.antares.sirius.view.action;

import static java.math.BigDecimal.ZERO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.antares.commons.util.Utils;
import com.antares.commons.util.jqgrid.JQGridContainer;
import com.antares.commons.util.jqgrid.JQGridRow;
import com.antares.sirius.dto.PresupuestoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.ActividadService;
import com.antares.sirius.service.PresupuestoService;
import com.antares.sirius.service.ProyectoService;
import com.antares.sirius.service.RubroService;
import com.google.gson.Gson;

public class PresupuestoAction extends DispatchAction {

	private PresupuestoService presupuestoService;
	private ProyectoService proyectoService;
	private ActividadService actividadService;
	private RubroService rubroService;
	
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		DynaActionForm dyna = (DynaActionForm)form; 
		dyna.set("presupuestos", null);

		String idProyectoStr =(String) request.getParameter("idProyecto");
		if (Utils.isNotNullNorEmpty(idProyectoStr)) {
			Integer idProyecto = Integer.parseInt(idProyectoStr);
			Proyecto proyecto = proyectoService.findById(idProyecto);
			List<Actividad> actividades = (List<Actividad>)actividadService.findAllByProyecto(proyecto);
			List<Rubro> rubros = (List<Rubro>)rubroService.findPrimerNivel();

			dyna.set("idProyecto", idProyecto);
			dyna.set("nombreProyecto", proyecto.getNombre());
			dyna.set("actividades", actividades);
			dyna.set("rubros", rubros);
			dyna.set("nombresColumnas", nombresColumnas(rubros));
		}
		
		return mapping.findForward("show");
	}

	/**
	 * Obtiene los datos que se deben mostrar en la grilla y los envia a la vista usando JSON.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward getData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		DynaActionForm dyna = (DynaActionForm)form;
		Integer idProyecto = (Integer)dyna.get("idProyecto");
		Proyecto proyecto = proyectoService.findById(idProyecto);

		List<Rubro> rubros = (List<Rubro>)dyna.get("rubros");
		List<Actividad> actividades = (List<Actividad>)dyna.get("actividades");

		PresupuestoDTO presupuestos = (PresupuestoDTO)dyna.get("presupuestos");
		if (presupuestos == null) {
			presupuestos = presupuestoService.findAllByProyecto(proyecto);
			dyna.set("presupuestos", presupuestos);
		}

		JQGridContainer grid = buildJQGrid(rubros, actividades, presupuestos);
		sendJSON(grid, response);
		return null;
	}

	/**
	 * Actualiza el DTO de presupuestos con las modificaciones hechas en la grilla.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward updateData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		DynaActionForm dyna = (DynaActionForm)form;
		List<Actividad> actividades = (List<Actividad>)dyna.get("actividades");
		List<Rubro> rubros = (List<Rubro>)dyna.get("rubros");
		PresupuestoDTO presupuestos = (PresupuestoDTO)dyna.get("presupuestos");

		Rubro rubro = resolverRubro(rubros, (Map<String, String>)request.getParameterMap());
		
		if (rubro != null) {
			String montoStr = request.getParameter(rubro.getId().toString());
			if (montoStr.equals("") || Utils.isDouble(montoStr)) {
				Double monto = Utils.parseDouble(montoStr);
	
				// Si el monto ingresado es 0, se debe eliminar el presupuesto del DTO
				if (monto.doubleValue() == 0D) {
					monto = null;
				}
	
				if (Integer.parseInt(request.getParameter("id")) <= actividades.size()) {
					Actividad actividad = actividades.get(Integer.parseInt(request.getParameter("id")) - 1);
					presupuestoService.addPresupuestoActividad(presupuestos, actividad, rubro, monto);
				} else {
					presupuestoService.addPresupuestoProyecto(presupuestos, rubro, monto);
				}
			}
		}
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		DynaActionForm dyna = (DynaActionForm)form;
		PresupuestoDTO presupuestos = (PresupuestoDTO)dyna.get("presupuestos");
		if (presupuestos != null) {
			presupuestoService.saveAll(presupuestos);
		}

		return mapping.findForward("success");
	}

	/**
	 * Construye una grilla a partir de los rubros de primer nivel y actividades del sistema y la completa con los montos 
	 * de los presupuestos correspondientes.
	 * Agrega una fila mas con el presupuesto de los gastos generales de proyecto por rubro.
	 * 
	 * @param rubros rubros de primer nival
	 * @param actividades actividades
	 * @param presupuestos DTO con los presupuestos del proyecto
	 * @return grilla con los datos cargados
	 */
	protected JQGridContainer buildJQGrid(List<Rubro> rubros, List<Actividad> actividades, PresupuestoDTO presupuestos) {
		BigDecimal[][] matrizGrilla = buildMatrizPresupuesto(rubros, actividades, presupuestos);
		JQGridContainer grid = new JQGridContainer();
		List<JQGridRow> rows = new ArrayList<JQGridRow>();

		for (int i = 0; i < actividades.size() + 3; i++) {
			JQGridRow row = new JQGridRow();
			row.getCells().add(resolverNombreFila(actividades, i));
			for (int j = 0; j < rubros.size() + 2; j++) {
				// Se evalua esto para dejar dos celdas en blanco 
				if (!(i == actividades.size() + 2 && j == rubros.size()) && !(i == actividades.size() + 1 && j == rubros.size() + 1)) {
					BigDecimal cellValue = matrizGrilla[i][j] == null ? ZERO : matrizGrilla[i][j];
					row.getCells().add(Utils.formatDouble(cellValue.doubleValue()));
				} else {
					row.getCells().add("");
				}
			}
			rows.add(row);
		}

		grid.setRows(rows);
		return grid;
	}

	private String resolverNombreFila(List<Actividad> actividades, int index) {
		String nombreFila = "";
		if (index < actividades.size()) {
			nombreFila = actividades.get(index).getNombre();
		} else if (index == actividades.size()) {
			nombreFila = "Proyecto";
		} else if (index == actividades.size() + 1) {
			nombreFila = "TOTAL %";
		} else if (index == actividades.size() + 2) {
			nombreFila = "TOTAL";
		}
		return nombreFila;
	}

	/**
	 * Construye una matriz a partir del DTO de presupuestos para representar la grilla que se muestra en pantalla
	 * 
	 * @param rubros rubros de primer nival
	 * @param actividades actividades
	 * @param presupuestos DTO con los presupuestos del proyecto
	 * @return matriz con los valores a mostrarse
	 */
	protected BigDecimal[][] buildMatrizPresupuesto(List<Rubro> rubros, List<Actividad> actividades, PresupuestoDTO presupuestos) {

		BigDecimal presupuestoTotal = new BigDecimal(presupuestos.getProyecto().getPresupuestoTotal());
		BigDecimal[][] matrizGrilla = new BigDecimal[actividades.size() + 3][rubros.size() + 2];

		// Agrego los presupuestos por actividad
		int fila = 0;
		for (Actividad actividad : actividades) {
			int columna = 0;
			for (Rubro rubro : rubros) {
				Presupuesto presupuesto = presupuestos.get(actividad, rubro);
				matrizGrilla[fila][columna++] = presupuesto != null ? new BigDecimal(presupuesto.getMonto()) : ZERO; 
			}
			fila++;
		}

		// Agrego los presupuestos para gastos de proyecto
		int columna = 0;
		for (Rubro rubro : rubros) {
			Presupuesto presupuesto = presupuestos.get(rubro);
			matrizGrilla[fila][columna++] = presupuesto != null ? new BigDecimal(presupuesto.getMonto()) : ZERO; 
		}

		//Calcular totales
		BigDecimal totalAsignado = ZERO;
		for (int i = 0; i < actividades.size() + 1; i++) {
			for (int j = 0; j < rubros.size(); j++) {
				BigDecimal cellValue = matrizGrilla[i][j];

				if (matrizGrilla[i][rubros.size() + 1] == null) 
					matrizGrilla[i][rubros.size() + 1] = ZERO;
				if (matrizGrilla[actividades.size() + 2][j] == null) 
					matrizGrilla[actividades.size() + 2][j] = ZERO;
					
				matrizGrilla[i][rubros.size() + 1] = matrizGrilla[i][rubros.size() + 1].add(cellValue);
				matrizGrilla[actividades.size() + 2][j] = matrizGrilla[actividades.size() + 2][j].add(cellValue);
				totalAsignado = totalAsignado.add(cellValue);
			}
		}

		//Calcular porcentajes
		int columnaTotal = rubros.size() + 1;
		int filaTotal = actividades.size() + 2;
		for (int i = 0; i < actividades.size() + 1; i++) {
			matrizGrilla[i][columnaTotal - 1] = Utils.calcularPorcentaje(matrizGrilla[i][columnaTotal], presupuestoTotal);
		}
		for (int j = 0; j < rubros.size(); j++) {
			matrizGrilla[filaTotal - 1][j] = Utils.calcularPorcentaje(matrizGrilla[filaTotal][j], presupuestoTotal);
		}

		presupuestos.setPresupuestoDisponible(presupuestoTotal.subtract(totalAsignado).doubleValue());
		matrizGrilla[actividades.size() + 2][rubros.size() + 1] = totalAsignado;
		matrizGrilla[actividades.size() + 1][rubros.size()] = Utils.calcularPorcentaje(totalAsignado, presupuestoTotal);
		return matrizGrilla;
	}

	/**
	 * Transforma la grilla a JSON y la escribe en el response
	 * 
	 * @param grid grilla con los datos
	 * @param response response http
	 * @throws IOException
	 */
	protected void sendJSON(JQGridContainer grid, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		response.setContentType("text/html; charset=iso-8859-1");
		response.getOutputStream().print(gson.toJson(grid));
	}

	private String nombresColumnas(List<Rubro> rubros) {
		String nombresColumnas = "";
		for (Rubro rubro : rubros) {
			nombresColumnas += "'" + rubro.getNombre() + "',";
		}
		return nombresColumnas;
	}

	/**
	 * Determina cual es el rubro que se debe actualizar, a partir de los parametros de request y lo busca en una 
	 * lista de rubros.
	 * 
	 * @param rubros lista con todos los rubros de primer nivel
	 * @param parametros parametros de request
	 * @return rubro encontrado
	 */
	protected Rubro resolverRubro(List<Rubro> rubros, Map<String, String> parametros) {
		Rubro retval = null;
		Iterator<Rubro> it = rubros.iterator();
		while (it.hasNext() && retval == null) {
			Rubro rubro = it.next();
			if (parametros.get(rubro.getId().toString()) != null) {
				retval = rubro;
			}
		}
		return retval;
	}

	public ActionForward getPresupuestoDisponible(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		DynaActionForm dyna = (DynaActionForm)form;
		PresupuestoDTO presupuestos = (PresupuestoDTO)dyna.get("presupuestos");
		Map<String, String> map = new HashMap<String, String>();
		map.put("presupuestoDisponible", Utils.formatDouble(presupuestos.getPresupuestoDisponible()));

		JSONObject jsonMap = JSONObject.fromObject(map);
		response.setContentType("text/html; charset=iso-8859-1");
		response.getOutputStream().print(jsonMap.toString());
		return null;
	}

	public void setPresupuestoService(PresupuestoService presupuestoService) {
		this.presupuestoService = presupuestoService;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setActividadService(ActividadService actividadService) {
		this.actividadService = actividadService;
	}

	public void setRubroService(RubroService rubroService) {
		this.rubroService = rubroService;
	}

}
