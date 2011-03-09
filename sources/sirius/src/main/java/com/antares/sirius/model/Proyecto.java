package com.antares.sirius.model;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@SuppressWarnings("serial")
public class Proyecto extends BusinessObject {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idEstadoProyecto"))
	private EstadoProyecto estadoProyecto;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "financiador"))
	private Financiador financiador;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "responsable"))
	private Persona responsable;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name = "idTipoAgrupamiento"))
	private TipoAgrupamiento tipoAgrupamiento;

	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String ubicacion;
	private String beneficiariosDirectos;
	private String beneficiariosIndirectos;
	private String resumen;

	@Transient //TODO esto es provisorio, sacar cuando se agregue el archivo al ABM
	private File adjunto;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "coordinador", joinColumns = @JoinColumn(name="idProyecto"), inverseJoinColumns = @JoinColumn(name = "idPersona"))
	private Set<Persona> coordinadores;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "proyectoareatematica", joinColumns = @JoinColumn(name="idProyecto"), inverseJoinColumns = @JoinColumn(name = "idAreaTematica"))
	private Set<AreaTematica> areasTematicas;

	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
	private Collection<ObjetivoGeneral> objetivosGenerales;

	public EstadoProyecto getEstadoProyecto() {
		return estadoProyecto;
	}

	public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}

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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
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

	public Financiador getFinanciador() {
		return financiador;
	}

	public void setFinanciador(Financiador financiador) {
		this.financiador = financiador;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public File getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(File adjunto) {
		this.adjunto = adjunto;
	}

	public TipoAgrupamiento getTipoAgrupamiento() {
		return tipoAgrupamiento;
	}

	public void setTipoAgrupamiento(TipoAgrupamiento tipoAgrupamiento) {
		this.tipoAgrupamiento = tipoAgrupamiento;
	}

	public Set<Persona> getCoordinadores() {
		return coordinadores;
	}

	public void setCoordinadores(Set<Persona> coordinadores) {
		this.coordinadores = coordinadores;
	}

	public Set<AreaTematica> getAreasTematicas() {
		return areasTematicas;
	}

	public void setAreasTematicas(Set<AreaTematica> areasTematicas) {
		this.areasTematicas = areasTematicas;
	}

	public Collection<ObjetivoGeneral> getObjetivosGenerales() {
		return objetivosGenerales;
	}

	public void setObjetivosGenerales(Collection<ObjetivoGeneral> objetivosGenerales) {
		this.objetivosGenerales = objetivosGenerales;
	}

	@Override
	public void setActivo(Boolean activo) {
		super.setActivo(activo);
		if (!activo) {
			for (ObjetivoGeneral objetivoGeneral : this.objetivosGenerales) {
				objetivoGeneral.setActivo(activo);
			}
		}
	}

}
