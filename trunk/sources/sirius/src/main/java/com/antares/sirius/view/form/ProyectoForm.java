package com.antares.sirius.view.form;

import java.util.Collection;

import org.apache.struts.upload.FormFile;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.AreaTematica;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.TipoAgrupamiento;

/**
 * Representacion en la capa de vista de la entidad de modelo Proyecto.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class ProyectoForm extends AbstractForm<Proyecto> {

	private String nombre;
	private String descripcion;
	private String fechaInicio;
	private String fechaFin;
	private String ubicacion;
	private Integer[] idCoordinadores;
	private String idResponsable;
	private String beneficiariosDirectos;
	private String beneficiariosIndirectos;
	private String idFinanciador;
	private String presupuestoTotal;
	private String resumen;
	private Integer[] idAreaTematica;
	private String idTipoAgrupamiento;
	private Collection<Persona> coordinadores;
	private Collection<Persona> responsables;
	private Collection<Financiador> financiadores;
	private Collection<AreaTematica> areasTematicas;
	private Collection<TipoAgrupamiento> tiposAgrupamiento;
	private Collection<EstadoProyecto> estadosProyecto;
	private FormFile archivo;
	private String nombreArchivo;
	private String hashArchivo;

	private String filtroNombre;
	private String filtroFechaInicio;
	private String filtroFechaFin;
	private String filtroIdFinanciador;
	private String filtroIdEstadoProyecto;
	private String filtroIdAreaTematica;
	
	private String labelResponsable;
	private String labelCoordinadores;
	private String labelFinanciador;
	private String labelAreasTematicas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer[] getIdCoordinadores() {
		return idCoordinadores;
	}

	public void setIdCoordinadores(Integer[] idCoordinadores) {
		this.idCoordinadores = idCoordinadores;
	}

	public String getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(String idResponsable) {
		this.idResponsable = idResponsable;
	}

	public String getBeneficiariosDirectos() {
		return beneficiariosDirectos;
	}

	public void setBeneficiariosDirectos(String beneficiariosDirectos) {
		this.beneficiariosDirectos = beneficiariosDirectos;
	}

	public String getBeneficiariosIndirectos() {
		return beneficiariosIndirectos;
	}

	public void setBeneficiariosIndirectos(String beneficiariosIndirectos) {
		this.beneficiariosIndirectos = beneficiariosIndirectos;
	}

	public String getIdFinanciador() {
		return idFinanciador;
	}

	public void setIdFinanciador(String idFinanciador) {
		this.idFinanciador = idFinanciador;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Integer[] getIdAreaTematica() {
		return idAreaTematica;
	}

	public void setIdAreaTematica(Integer[] idAreaTematica) {
		this.idAreaTematica = idAreaTematica;
	}

	public Collection<Persona> getCoordinadores() {
		return coordinadores;
	}

	public void setCoordinadores(Collection<Persona> coordinadores) {
		this.coordinadores = coordinadores;
	}

	public Collection<Persona> getResponsables() {
		return responsables;
	}

	public void setResponsables(Collection<Persona> responsables) {
		this.responsables = responsables;
	}

	public Collection<Financiador> getFinanciadores() {
		return financiadores;
	}

	public void setFinanciadores(Collection<Financiador> financiadores) {
		this.financiadores = financiadores;
	}

	public Collection<AreaTematica> getAreasTematicas() {
		return areasTematicas;
	}

	public void setAreasTematicas(Collection<AreaTematica> areasTematicas) {
		this.areasTematicas = areasTematicas;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroFechaInicio() {
		return filtroFechaInicio;
	}

	public void setFiltroFechaInicio(String filtroFechaInicio) {
		this.filtroFechaInicio = filtroFechaInicio;
	}

	public String getFiltroFechaFin() {
		return filtroFechaFin;
	}

	public void setFiltroFechaFin(String filtroFechaFin) {
		this.filtroFechaFin = filtroFechaFin;
	}

	public String getFiltroIdFinanciador() {
		return filtroIdFinanciador;
	}

	public void setFiltroIdFinanciador(String filtroIdFinanciador) {
		this.filtroIdFinanciador = filtroIdFinanciador;
	}

	public String getFiltroIdAreaTematica() {
		return filtroIdAreaTematica;
	}

	public void setFiltroIdAreaTematica(String filtroIdAreaTematica) {
		this.filtroIdAreaTematica = filtroIdAreaTematica;
	}

	public String getLabelResponsable() {
		return labelResponsable;
	}

	public void setLabelResponsable(String labelResponsable) {
		this.labelResponsable = labelResponsable;
	}

	public String getLabelFinanciador() {
		return labelFinanciador;
	}

	public void setLabelFinanciador(String labelFinanciador) {
		this.labelFinanciador = labelFinanciador;
	}

	public Collection<TipoAgrupamiento> getTiposAgrupamiento() {
		return tiposAgrupamiento;
	}

	public void setTiposAgrupamiento(Collection<TipoAgrupamiento> tiposAgrupamiento) {
		this.tiposAgrupamiento = tiposAgrupamiento;
	}

	public String getIdTipoAgrupamiento() {
		return idTipoAgrupamiento;
	}

	public void setIdTipoAgrupamiento(String idTipoAgrupamiento) {
		this.idTipoAgrupamiento = idTipoAgrupamiento;
	}

	public FormFile getArchivo() {
		return archivo;
	}

	public void setArchivo(FormFile archivo) {
		this.archivo = archivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getHashArchivo() {
		return hashArchivo;
	}

	public void setHashArchivo(String hashArchivo) {
		this.hashArchivo = hashArchivo;
	}

	public String getPresupuestoTotal() {
		return presupuestoTotal;
	}

	public void setPresupuestoTotal(String presupuestoTotal) {
		this.presupuestoTotal = presupuestoTotal;
	}

	public String getLabelCoordinadores() {
		return labelCoordinadores;
	}

	public void setLabelCoordinadores(String labelCoordinadores) {
		this.labelCoordinadores = labelCoordinadores;
	}

	public String getLabelAreasTematicas() {
		return labelAreasTematicas;
	}

	public void setLabelAreasTematicas(String labelAreasTematicas) {
		this.labelAreasTematicas = labelAreasTematicas;
	}

	public String getFiltroIdEstadoProyecto() {
		return filtroIdEstadoProyecto;
	}

	public void setFiltroIdEstadoProyecto(String filtroIdEstadoProyecto) {
		this.filtroIdEstadoProyecto = filtroIdEstadoProyecto;
	}

	public Collection<EstadoProyecto> getEstadosProyecto() {
		return estadosProyecto;
	}

	public void setEstadosProyecto(Collection<EstadoProyecto> estadosProyecto) {
		this.estadosProyecto = estadosProyecto;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.nombre = "";
		this.descripcion = "";
		this.fechaInicio = "";
		this.fechaFin = "";
		this.ubicacion = "";
		this.idCoordinadores = null;
		this.idResponsable = "";
		this.beneficiariosDirectos = "";
		this.beneficiariosIndirectos = "";
		this.idFinanciador = "";
		this.presupuestoTotal = "";
		this.resumen = "";
		this.idAreaTematica = null;
		this.idTipoAgrupamiento = "";
		this.filtroNombre = "";
		this.filtroFechaInicio = "";
		this.filtroFechaFin = "";
		this.filtroIdFinanciador = "";
		this.filtroIdEstadoProyecto = "";
		this.filtroIdAreaTematica = "";
		this.labelResponsable = "";
		this.labelCoordinadores = "";
		this.labelFinanciador = "";
		this.labelAreasTematicas = "";
		this.archivo = null;
		this.nombreArchivo = "";
		this.hashArchivo = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.nombre = "";
		this.descripcion = "";
		this.fechaInicio = "";
		this.fechaFin = "";
		this.ubicacion = "";
		this.idCoordinadores = null;
		this.idResponsable = "";
		this.beneficiariosDirectos = "";
		this.beneficiariosIndirectos = "";
		this.idFinanciador = "";
		this.presupuestoTotal = Utils.formatDouble(0D);
		this.resumen = "";
		this.idAreaTematica = null;
		this.idTipoAgrupamiento = "";
		this.labelResponsable = "";
		this.labelCoordinadores = "";
		this.labelFinanciador = "";
		this.labelAreasTematicas = "";
		this.archivo = null;
		this.nombreArchivo = "";
		this.hashArchivo = "";
	}

	@Override
	public void initializeForm(Proyecto entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.descripcion = entity.getDescripcion();
		this.fechaInicio = Utils.formatDate(entity.getFechaInicio());
		this.fechaFin = Utils.formatDate(entity.getFechaFin());
		this.ubicacion = entity.getUbicacion();
		this.idResponsable = entity.getResponsable().getId().toString();
		this.beneficiariosDirectos = entity.getBeneficiariosDirectos();
		this.beneficiariosIndirectos = entity.getBeneficiariosIndirectos();
		this.idFinanciador = entity.getFinanciador().getId().toString();
		this.presupuestoTotal = Utils.formatDouble(entity.getPresupuestoTotal());
		this.resumen = entity.getResumen();
		this.labelResponsable = entity.getResponsable().getNombreYApellido();
		this.labelFinanciador = entity.getFinanciador().getNombre();

		int cantCoordinadores = entity.getCoordinadores().size();
		this.idCoordinadores = new Integer[cantCoordinadores];
		this.labelCoordinadores = "";
		for (Persona coordinador : entity.getCoordinadores()) {
			this.idCoordinadores[--cantCoordinadores] = coordinador.getId();
			this.labelCoordinadores += coordinador.getNombreYApellido() + "\n";
		}

		int cantAreas = entity.getAreasTematicas().size();
		this.idAreaTematica = new Integer[cantAreas];
		this.labelAreasTematicas = "";
		for (AreaTematica area : entity.getAreasTematicas()) {
			this.idAreaTematica[--cantAreas] = area.getId();
			this.labelAreasTematicas += area.getDescripcion() + "\n";
		}

		this.idTipoAgrupamiento = entity.getTipoAgrupamiento().getId().toString();

		if (entity.getArchivo() != null) {
			this.nombreArchivo = entity.getArchivo().getNombre();
			this.hashArchivo = entity.getArchivo().getHash();
		} else {
			this.nombreArchivo = "";
			this.hashArchivo = "";
		}
	}

}
