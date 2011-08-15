package com.antares.sirius.service.impl;

import java.util.Collection;

import com.antares.sirius.dto.FinanzasDTO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.FinanzasService;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.PresupuestoService;

/**
 * Implementacion de la interfaz FinanzasService.
 *
 * @version 1.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class FinanzasServiceImpl implements FinanzasService {

	private PresupuestoService presupuestoService;
	private GastoService gastoService;

	public FinanzasDTO finanzasPorActividad(Actividad actividad, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos) {
		FinanzasDTO finanzas = new FinanzasDTO();
		if (calcularPresupuestos) {
			Collection<MontoDTO> montosPresupuestados = presupuestoService.obtainMontosByActividadAndRubro(actividad, rubros);
			loadMontoPresupuestado(finanzas, montosPresupuestados);
		}
		if (calcularGastos) {
			Collection<MontoDTO> montosGastados = gastoService.obtainMontosByActividadAndRubro(actividad, rubros);
			loadMontoGastado(finanzas, montosGastados);
		}
		return finanzas;
	}

	public FinanzasDTO finanzasPorMeta(Meta meta, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos) {
		FinanzasDTO finanzas = new FinanzasDTO();
		if (calcularPresupuestos) {
			Collection<MontoDTO> montosPresupuestados = presupuestoService.obtainMontosByMetaAndRubro(meta, rubros);
			loadMontoPresupuestado(finanzas, montosPresupuestados);
		}
		if (calcularGastos) {
			Collection<MontoDTO> montosGastados = gastoService.obtainMontosByMetaAndRubro(meta, rubros);
			loadMontoGastado(finanzas, montosGastados);
		}
		return finanzas;
	}

	public FinanzasDTO finanzasPorObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos) {
		FinanzasDTO finanzas = new FinanzasDTO();
		if (calcularPresupuestos) {
			Collection<MontoDTO> montosPresupuestados = presupuestoService.obtainMontosByObjetivoEspecificoAndRubro(objetivoEspecifico, rubros);
			loadMontoPresupuestado(finanzas, montosPresupuestados);
		}
		if (calcularGastos) {
			Collection<MontoDTO> montosGastados = gastoService.obtainMontosByObjetivoEspecificoAndRubro(objetivoEspecifico, rubros);
			loadMontoGastado(finanzas, montosGastados);
		}
		return finanzas;
	}

	public FinanzasDTO finanzasPorObjetivoGeneral(ObjetivoGeneral objetivoGeneral, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos) {
		FinanzasDTO finanzas = new FinanzasDTO();
		if (calcularPresupuestos) {
			Collection<MontoDTO> montosPresupuestados = presupuestoService.obtainMontosByObjetivoGeneralAndRubro(objetivoGeneral, rubros);
			loadMontoPresupuestado(finanzas, montosPresupuestados);
		}
		if (calcularGastos) {
			Collection<MontoDTO> montosGastados = gastoService.obtainMontosByObjetivoGeneralAndRubro(objetivoGeneral, rubros);
			loadMontoGastado(finanzas, montosGastados);
		}
		return finanzas;
	}

	public FinanzasDTO finanzasPorProyecto(Proyecto proyecto, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos) {
		FinanzasDTO finanzas = new FinanzasDTO();
		if (calcularPresupuestos) {
			Collection<MontoDTO> montosPresupuestados = presupuestoService.obtainMontosByProyectoAndRubro(proyecto, rubros);
			loadMontoPresupuestado(finanzas, montosPresupuestados);
		}
		if (calcularGastos) {
			Collection<MontoDTO> montosGastados = gastoService.obtainMontosByProyectoAndRubro(proyecto, rubros);
			loadMontoGastado(finanzas, montosGastados);
		}
		return finanzas;
	}

	private void loadMontoPresupuestado(FinanzasDTO finanzas, Collection<MontoDTO> montosPresupuestados) {
		for (MontoDTO monto : montosPresupuestados) {
			finanzas.putMonto(monto);
		}
	}

	private void loadMontoGastado(FinanzasDTO finanzas, Collection<MontoDTO> montosGastados) {
		for (MontoDTO monto : montosGastados) {
			MontoDTO montoFinanzas = finanzas.get(monto.getId(), monto.getIdRubro());
			if (montoFinanzas == null) {
				montoFinanzas = new MontoDTO();
				montoFinanzas.setMontoPresupuestado(0D);
				finanzas.putMonto(monto);
			}
			montoFinanzas.setMontoGastado(monto.getMontoGastado());
			montoFinanzas.setMontoDif(montoFinanzas.getMontoPresupuestado() - montoFinanzas.getMontoGastado());
		}
	}

	public void setPresupuestoService(PresupuestoService presupuestoService) {
		this.presupuestoService = presupuestoService;
	}

	public void setGastoService(GastoService gastoService) {
		this.gastoService = gastoService;
	}

}
