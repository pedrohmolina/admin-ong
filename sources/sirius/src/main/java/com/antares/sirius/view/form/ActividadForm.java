package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Meta;

/**
 * Representacion en la capa de vista de la entidad de modelo Actividad.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class ActividadForm extends AbstractForm<Actividad> {

	private String nombre;
	private String ponderacion;
	private String observaciones;
	private String fechaInicio;
	private String fechaFin;
	private String idMeta;
	private String idFinanciador;
	private Integer completitud;
	private Collection<Meta> metas;
	private Collection<Financiador> financiadores;

	private String filtroIdMeta;
	private String filtroNombre;

	private String labelMeta;
	private String labelFinanciador;

	private Integer idEstado;
	private boolean actualizarCompletitud;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(String ponderacion) {
		this.ponderacion = ponderacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getIdMeta() {
		return idMeta;
	}

	public void setIdMeta(String idMeta) {
		this.idMeta = idMeta;
	}

	public String getIdFinanciador() {
		return idFinanciador;
	}

	public void setIdFinanciador(String idFinanciador) {
		this.idFinanciador = idFinanciador;
	}

	public Collection<Meta> getMetas() {
		return metas;
	}

	public void setMetas(Collection<Meta> metas) {
		this.metas = metas;
	}

	public Collection<Financiador> getFinanciadores() {
		return financiadores;
	}

	public void setFinanciadores(Collection<Financiador> financiadores) {
		this.financiadores = financiadores;
	}

	public String getFiltroIdMeta() {
		return filtroIdMeta;
	}

	public void setFiltroIdMeta(String filtroIdMeta) {
		this.filtroIdMeta = filtroIdMeta;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getLabelMeta() {
		return labelMeta;
	}

	public void setLabelMeta(String labelMeta) {
		this.labelMeta = labelMeta;
	}

	public String getLabelFinanciador() {
		return labelFinanciador;
	}

	public void setLabelFinanciador(String labelFinanciador) {
		this.labelFinanciador = labelFinanciador;
	}

	public Integer getCompletitud() {
		return completitud;
	}

	public void setCompletitud(Integer completitud) {
		this.completitud = completitud;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public boolean isActualizarCompletitud() {
		return actualizarCompletitud;
	}

	public void setActualizarCompletitud(boolean actualizarCompletitud) {
		this.actualizarCompletitud = actualizarCompletitud;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.ponderacion = "";
		this.observaciones = "";
		this.completitud = 0;
		this.idMeta = "";
		this.idFinanciador = "";
		this.filtroNombre = "";
		this.filtroIdMeta = "";
		this.labelMeta = "";
		this.labelFinanciador = "";

		this.idEstado = null;
		this.actualizarCompletitud = false;
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.ponderacion = "";
		this.observaciones = "";
		this.completitud = 0;
		this.idMeta = "";
		this.idFinanciador = "";
		this.labelMeta = "";
		this.labelFinanciador = "";

		this.idEstado = null;
		this.actualizarCompletitud = false;
	}

	@Override
	public void initializeForm(Actividad entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.ponderacion = entity.getPonderacion().toString();
		this.observaciones = entity.getObservaciones();
		this.completitud = entity.getCompletitud().intValue();
		this.idMeta = entity.getMeta().getId().toString();
		this.idFinanciador = entity.getFinanciador().getId().toString();
		this.labelMeta = entity.getMeta().getNombre();
		this.labelFinanciador = entity.getFinanciador().getNombre();

		this.idEstado = entity.getEstadoActividad().getId();
		this.actualizarCompletitud = false;
	}

}
