package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.FormatoReporte;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.model.Proyecto;

/**
 * Formulario que contendra los filtros a partir de los cuales se generara el reporte de Finanzas.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
@SuppressWarnings("serial")
public class ReporteFinancieroForm extends ActionForm{

	private Collection<FormatoReporte> formatosReporte;
	private String formatoReporte;

	private Collection<Proyecto> proyectos;
	private Collection<ObjetivoGeneral> objetivosGenerales;
	private Collection<ObjetivoEspecifico> objetivosEspecificos;
	private Collection<Meta> metas;
	private Collection<Actividad> actividades;
	
	private String idProyecto;
	private String idObjetivoGeneral;
	private String idObjetivoEspecifico;
	private String idMeta;
	private String idActividad;
	
	private String filtroIdProyecto;
	private String filtroIdObjetivoGeneral;
	private String filtroIdObjetivoEspecifico;
	private String filtroIdMeta;
	private String filtroIdActividad;
	
	private String[] rubrosSeleccionados;
	
	/**
	 * @return the proyectos
	 */
	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}
	/**
	 * @param proyectos the proyectos to set
	 */
	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	/**
	 * @return the objetivosGenerales
	 */
	public Collection<ObjetivoGeneral> getObjetivosGenerales() {
		return objetivosGenerales;
	}
	/**
	 * @param objetivosGenerales the objetivosGenerales to set
	 */
	public void setObjetivosGenerales(Collection<ObjetivoGeneral> objetivosGenerales) {
		this.objetivosGenerales = objetivosGenerales;
	}
	/**
	 * @return the objetivosEspecificos
	 */
	public Collection<ObjetivoEspecifico> getObjetivosEspecificos() {
		return objetivosEspecificos;
	}
	/**
	 * @param objetivosEspecificos the objetivosEspecificos to set
	 */
	public void setObjetivosEspecificos(
			Collection<ObjetivoEspecifico> objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}
	/**
	 * @return the metas
	 */
	public Collection<Meta> getMetas() {
		return metas;
	}
	/**
	 * @param metas the metas to set
	 */
	public void setMetas(Collection<Meta> metas) {
		this.metas = metas;
	}
	/**
	 * @return the actividades
	 */
	public Collection<Actividad> getActividades() {
		return actividades;
	}
	/**
	 * @param actividades the actividades to set
	 */
	public void setActividades(Collection<Actividad> actividades) {
		this.actividades = actividades;
	}
	/**
	 * @return the idProyecto
	 */
	public String getIdProyecto() {
		return idProyecto;
	}
	/**
	 * @param idProyecto the idProyecto to set
	 */
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	/**
	 * @return the idObjetivoGeneral
	 */
	public String getIdObjetivoGeneral() {
		return idObjetivoGeneral;
	}
	/**
	 * @param idObjetivoGeneral the idObjetivoGeneral to set
	 */
	public void setIdObjetivoGeneral(String idObjetivoGeneral) {
		this.idObjetivoGeneral = idObjetivoGeneral;
	}
	/**
	 * @return the idObjetivoEspecifico
	 */
	public String getIdObjetivoEspecifico() {
		return idObjetivoEspecifico;
	}
	/**
	 * @param idObjetivoEspecifico the idObjetivoEspecifico to set
	 */
	public void setIdObjetivoEspecifico(String idObjetivoEspecifico) {
		this.idObjetivoEspecifico = idObjetivoEspecifico;
	}
	/**
	 * @return the idMeta
	 */
	public String getIdMeta() {
		return idMeta;
	}
	/**
	 * @param idMeta the idMeta to set
	 */
	public void setIdMeta(String idMeta) {
		this.idMeta = idMeta;
	}
	/**
	 * @return the idActividad
	 */
	public String getIdActividad() {
		return idActividad;
	}
	/**
	 * @param idActividad the idActividad to set
	 */
	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}
	/**
	 * @return the filtroIdProyecto
	 */
	public String getFiltroIdProyecto() {
		return filtroIdProyecto;
	}
	/**
	 * @param filtroIdProyecto the filtroIdProyecto to set
	 */
	public void setFiltroIdProyecto(String filtroIdProyecto) {
		this.filtroIdProyecto = filtroIdProyecto;
	}
	/**
	 * @return the filtroIdObjetivoGeneral
	 */
	public String getFiltroIdObjetivoGeneral() {
		return filtroIdObjetivoGeneral;
	}
	/**
	 * @param filtroIdObjetivoGeneral the filtroIdObjetivoGeneral to set
	 */
	public void setFiltroIdObjetivoGeneral(String filtroIdObjetivoGeneral) {
		this.filtroIdObjetivoGeneral = filtroIdObjetivoGeneral;
	}
	/**
	 * @return the filtroIdObjetivoEspecifico
	 */
	public String getFiltroIdObjetivoEspecifico() {
		return filtroIdObjetivoEspecifico;
	}
	/**
	 * @param filtroIdObjetivoEspecifico the filtroIdObjetivoEspecifico to set
	 */
	public void setFiltroIdObjetivoEspecifico(String filtroIdObjetivoEspecifico) {
		this.filtroIdObjetivoEspecifico = filtroIdObjetivoEspecifico;
	}
	/**
	 * @return the filtroIdMeta
	 */
	public String getFiltroIdMeta() {
		return filtroIdMeta;
	}
	/**
	 * @param filtroIdMeta the filtroIdMeta to set
	 */
	public void setFiltroIdMeta(String filtroIdMeta) {
		this.filtroIdMeta = filtroIdMeta;
	}
	/**
	 * @return the filtroIdActividad
	 */
	public String getFiltroIdActividad() {
		return filtroIdActividad;
	}
	/**
	 * @param filtroIdActividad the filtroIdActividad to set
	 */
	public void setFiltroIdActividad(String filtroIdActividad) {
		this.filtroIdActividad = filtroIdActividad;
	}
	/**
	 * @return the formatosReporte
	 */
	public Collection<FormatoReporte> getFormatosReporte() {
		return formatosReporte;
	}
	/**
	 * @param formatosReporte the formatosReporte to set
	 */
	public void setFormatosReporte(Collection<FormatoReporte> formatosReporte) {
		this.formatosReporte = formatosReporte;
	}
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}
	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the rubrosSeleccionados
	 */
	public String[] getRubrosSeleccionados() {
		return rubrosSeleccionados;
	}
	/**
	 * @param rubrosSeleccionados the rubrosSeleccionados to set
	 */
	public void setRubrosSeleccionados(String[] rubrosSeleccionados) {
		this.rubrosSeleccionados = rubrosSeleccionados;
	}
	public void initialize() {
	}

	public void initializeForm() {
	}

}
