package com.antares.sirius.service;

import com.antares.sirius.dto.FinanzasDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;


/**
 * Servicio para las consultas financieras de gastos y presupuestos
 * 
 * @version 1.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public interface FinanzasService {

	/**
	 * Devuelve las finanzas para una actividad, discriminadas por rubro.
	 * 
	 * @param actividad actividad cuyas finanzas quieren obtenerse
	 * @param rubros rubros cuyas finanzas quieren obtenerse
	 * @param calcularPresupuestos flag para indicar si es necesario incluir los montos presupuestados
	 * @param calcularGastos flag para indicar si es necesario incluir los montos incurridos (gastados)
	 * @return
	 */
	FinanzasDTO finanzasPorActividad(Actividad actividad, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos);

	/**
	 * Devuelve las finanzas para las actividades de una actividad, discriminadas por rubro.
	 * 
	 * @param meta meta cuyas finanzas quieren obtenerse
	 * @param rubros rubros cuyas finanzas quieren obtenerse
	 * @param calcularPresupuestos flag para indicar si es necesario incluir los montos presupuestados
	 * @param calcularGastos flag para indicar si es necesario incluir los montos incurridos (gastados)
	 * @return
	 */
	FinanzasDTO finanzasPorMeta(Meta meta, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos);

	/**
	 * Devuelve las finanzas para las metas de un objetivo especifico, discriminadas por rubro.
	 * 
	 * @param objetivoEspecifico objetivo especifico cuyas finanzas quieren obtenerse
	 * @param rubros rubros cuyas finanzas quieren obtenerse
	 * @param calcularPresupuestos flag para indicar si es necesario incluir los montos presupuestados
	 * @param calcularGastos flag para indicar si es necesario incluir los montos incurridos (gastados)
	 * @return
	 */
	FinanzasDTO finanzasPorObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos);

	/**
	 * Devuelve las finanzas para los objetivos especificos de un objetivo general, discriminadas por rubro.
	 * 
	 * @param objetivoGeneral objetivo general cuyas finanzas quieren obtenerse
	 * @param rubros rubros cuyas finanzas quieren obtenerse
	 * @param calcularPresupuestos flag para indicar si es necesario incluir los montos presupuestados
	 * @param calcularGastos flag para indicar si es necesario incluir los montos incurridos (gastados)
	 * @return
	 */
	FinanzasDTO finanzasPorObjetivoGeneral(ObjetivoGeneral objetivoGeneral, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos);

	/**
	 * Devuelve las finanzas para los objetivos generales de un proyecto, discriminadas por rubro.
	 * 
	 * @param proyecto proyecto cuyas finanzas quieren obtenerse
	 * @param rubros rubros cuyas finanzas quieren obtenerse
	 * @param calcularPresupuestos flag para indicar si es necesario incluir los montos presupuestados
	 * @param calcularGastos flag para indicar si es necesario incluir los montos incurridos (gastados)
	 * @return
	 */
	FinanzasDTO finanzasPorProyecto(Proyecto proyecto, Rubro[] rubros, Boolean calcularPresupuestos, Boolean calcularGastos);

}
