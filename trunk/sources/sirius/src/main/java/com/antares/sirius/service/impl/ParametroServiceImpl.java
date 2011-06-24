package com.antares.sirius.service.impl;

import static com.antares.commons.enums.ParametroEnum.ESTADO_ACTIVIDAD_LATENTE;
import static com.antares.commons.enums.ParametroEnum.ESTADO_ACTIVIDAD_PROGRESO;
import static com.antares.commons.enums.ParametroEnum.ESTADO_PROYECTO_IDEA;
import static com.antares.commons.enums.ParametroEnum.TIPO_AGRUPAMIENTO_AGRUPADO;
import static com.antares.commons.enums.ParametroEnum.TIPO_AGRUPAMIENTO_INDIVIDUAL;
import static com.antares.commons.enums.ParametroEnum.TIPO_GASTO_ACTIVIDAD;
import static com.antares.commons.enums.ParametroEnum.TIPO_GASTO_ORGANIZACION;
import static com.antares.commons.enums.ParametroEnum.TIPO_GASTO_PROYECTO;
import static com.antares.commons.enums.ParametroEnum.TIPO_INGRESO_FINANCIACION;
import static com.antares.commons.enums.ParametroEnum.TIPO_PRESUPUESTO_ACTIVIDAD;
import static com.antares.commons.enums.ParametroEnum.TIPO_PRESUPUESTO_PROYECTO;

import com.antares.commons.enums.ParametroEnum;
import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ParametroDAO;
import com.antares.sirius.model.Parametro;
import com.antares.sirius.service.ParametroService;

/**
 * Implementacion de la interfaz ParametroService.
 *
 * @version 1.0.0 Created 13/02/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ParametroServiceImpl extends BaseServiceImpl<Parametro, ParametroDAO> implements ParametroService {

	public String findValueByEnum(ParametroEnum parametroEnum) {
		String value = null;
		Parametro parametro = dao.findById(parametroEnum.getId());
		if (parametro != null) {
			value = parametro.getValor();
		}
		return value;
	}

	public String findIdTipoIngresoFinanciacion() {
		return findValueByEnum(TIPO_INGRESO_FINANCIACION);
	}

	public Integer findIdEstadoProyectoIdea() {
		return Utils.parseInteger(findValueByEnum(ESTADO_PROYECTO_IDEA));
	}

	public Integer findIdEstadoActividadLatente() {
		return Utils.parseInteger(findValueByEnum(ESTADO_ACTIVIDAD_LATENTE));
	}

	public Integer findIdEstadoActividadProgreso() {
		return Utils.parseInteger(findValueByEnum(ESTADO_ACTIVIDAD_PROGRESO));
	}

	public Integer findIdTipoGastoActividad() {
		return Utils.parseInteger(findValueByEnum(TIPO_GASTO_ACTIVIDAD));
	}

	public Integer findIdTipoGastoOrganizacion() {
		return Utils.parseInteger(findValueByEnum(TIPO_GASTO_ORGANIZACION));
	}

	public Integer findIdTipoGastoProyecto() {
		return Utils.parseInteger(findValueByEnum(TIPO_GASTO_PROYECTO));
	}

	public Integer findIdTipoAgrupamientoIndividual() {
		return Utils.parseInteger(findValueByEnum(TIPO_AGRUPAMIENTO_INDIVIDUAL));
	}

	public Integer findIdTipoAgrupamientoAgrupado() {
		return Utils.parseInteger(findValueByEnum(TIPO_AGRUPAMIENTO_AGRUPADO));
	}

	public Integer findIdTipoPresupuestoActividad() {
		return Utils.parseInteger(findValueByEnum(TIPO_PRESUPUESTO_ACTIVIDAD));
	}

	public Integer findIdTipoPresupuestoProyecto() {
		return Utils.parseInteger(findValueByEnum(TIPO_PRESUPUESTO_PROYECTO));
	}

}
