package com.antares.sirius.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import com.antares.commons.exception.ServiceException;
import com.antares.commons.util.ReportUtils;
import com.antares.sirius.base.Constants;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.service.ReporteFinancieroService;

/**
 * Implementacion de la interfaz ReporteFinancieroService.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
public class ReporteFinancieroServiceImpl implements ReporteFinancieroService{

	public JasperPrint generateReportBytes(Collection<Presupuesto> presupuestos) throws ServiceException{

		try {
			
			Map<String, String> parametros = new HashMap<String, String>();
			//parametros.put("nombreParametroReporte", String.valueOf(...));

			List listaPresupuestos = this.cargarPresupuestos(presupuestos);
			
			return ReportUtils.generateReportBytes(Constants.REPORTE_FINANCIEROS, parametros, listaPresupuestos);
		
		} catch (Exception e) {
			throw new ServiceException("Error al generar reporte de Personas.");
		}
	}
	
	private List cargarPresupuestos(Collection<Presupuesto> presupuestos) {

		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		Iterator<Presupuesto> it = presupuestos.iterator();
		
		while (it.hasNext()) {
			Presupuesto presupuesto = (Presupuesto) it
					.next();

			if (presupuesto!=null){
				HashMap<String, Object> registroPresupuesto = new HashMap<String, Object>();
				registroPresupuesto.put("nombre_actividad",presupuesto.getActividad().getNombre());
				registroPresupuesto.put("nombre_rubro",presupuesto.getRubro().getNombre());
				registroPresupuesto.put("presupuesto_monto",new BigDecimal(presupuesto.getMonto()));
				result.add(registroPresupuesto);
			}
		}

		return result;
	}
}
