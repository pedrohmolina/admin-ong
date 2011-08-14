package com.antares.sirius.service.impl;

import static java.lang.Boolean.TRUE;

import java.util.Collection;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.GastoDAO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.GastoService;
import com.antares.sirius.service.ParametroService;
import com.antares.sirius.service.ProyectoService;

/**
 * Implementacion de la interfaz GastoService.
 *
 * @version 1.0.0 Created 16/02/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class GastoServiceImpl extends BusinessEntityServiceImpl<Gasto, GastoDAO> implements GastoService {

	private ParametroService parametroService;
	private ProyectoService proyectoService;

	public void ejecutarConfirmacion(Gasto entity) {
		entity.setConfirmado(TRUE);
		dao.save(entity);
	}

	private Proyecto findProyecto(Gasto gasto) {
		Proyecto proyecto = null;
		if (gasto.getTipoGasto().getId().equals(parametroService.findIdTipoGastoProyecto())) {
			proyecto = gasto.getProyecto();
		}
		if (gasto.getTipoGasto().getId().equals(parametroService.findIdTipoGastoActividad())) {
			proyecto = gasto.getActividad().getProyecto();
		}
		return proyecto;
	}
	
	public boolean isIndividual(Gasto gasto) {
		boolean isIndividual = Boolean.TRUE;
		Proyecto proyecto = findProyecto(gasto);
		if (proyecto != null) {
			isIndividual = proyectoService.isIndividual(proyecto);
		}
		return isIndividual;
	}

	public boolean isAgrupado(Gasto gasto) {
		boolean isAgrupado = Boolean.FALSE;
		Proyecto proyecto = findProyecto(gasto);
		if (proyecto != null) {
			isAgrupado = proyectoService.isAgrupado(proyecto);
		}
		return isAgrupado;
	}

	@Override
	public Collection<Gasto> findAllByProyecto(Proyecto proyecto) {
		return dao.findAllByProyecto(proyecto);
	}

	@Override
	public boolean existenGastosProyecto(Proyecto proyecto) {
		return dao.existenGastosProyecto(proyecto);
	}

	@Override
	public Collection<Gasto> findAllByObjetivoGeneral(ObjetivoGeneral objetivoGeneral) {
		return dao.findAllByObjetivoGeneral(objetivoGeneral);
	}

	@Override
	public boolean existenGastosObjetivoGeneral(ObjetivoGeneral objetivoGeneral) {
		return dao.existenGastosObjetivoGeneral(objetivoGeneral);
	}

	@Override
	public Collection<Gasto> findAllByObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico) {
		return dao.findAllByObjetivoEspecifico(objetivoEspecifico);
	}

	@Override
	public boolean existenGastosObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico) {
		return dao.existenGastosObjetivoEspecifico(objetivoEspecifico);
	}

	@Override
	public Collection<Gasto> findAllByMeta(Meta meta) {
		return dao.findAllByMeta(meta);
	}

	@Override
	public boolean existenGastosMeta(Meta meta) {
		return dao.existenGastosMeta(meta);
	}

	@Override
	public Collection<Gasto> findAllByActividad(Actividad actividad) {
		return dao.findAllByActividad(actividad);
	}

	@Override
	public boolean existenGastosActividad(Actividad actividad) {
		return dao.existenGastosActividad(actividad);
	}

	@Override
	public Collection<MontoDTO> obtainMontosByActividadAndRubro(Actividad actividad, Rubro[] rubros) {
		return dao.obtainMontosByActividadAndRubro(actividad, rubros);
	}

	@Override
	public Collection<MontoDTO> obtainMontosByMetaAndRubro(Meta meta, Rubro[] rubros) {
		return dao.obtainMontosByMetaAndRubro(meta, rubros);
	}

	@Override
	public Collection<MontoDTO> obtainMontosByObjetivoEspecificoAndRubro(ObjetivoEspecifico objetivoEspecifico, Rubro[] rubros) {
		return dao.obtainMontosByObjetivoEspecificoAndRubro(objetivoEspecifico, rubros);
	}

	@Override
	public Collection<MontoDTO> obtainMontosByObjetivoGeneralAndRubro(ObjetivoGeneral objetivoGeneral, Rubro[] rubros) {
		return dao.obtainMontosByObjetivoGeneralAndRubro(objetivoGeneral, rubros);
	}

	@Override
	public Collection<MontoDTO> obtainMontosByProyectoAndRubro(Proyecto proyecto, Rubro[] rubros) {
		return dao.obtainMontosByProyectoAndRubro(proyecto, rubros);
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
