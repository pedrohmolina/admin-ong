package com.antares.sirius.view.form;

import static java.lang.Boolean.TRUE;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

import com.antares.commons.enums.FormatoReporteEnum;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

/**
 * Formulario que contendra los filtros a partir de los cuales se generara el reporte de Finanzas.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 * @version 2.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
*/
@SuppressWarnings("serial")
public class ReporteFinancieroForm extends ValidatorForm {

	private Collection<FormatoReporteEnum> formatosReporte;
	private String formatoReporte;

	private Collection<Proyecto> proyectos;
	private Collection<Rubro> rubros;
	
	private String idProyecto;
	private String idAgregacion;
	
	private Integer[] rubrosSeleccionados;
	
	private String tipoAgregacion;
	
	//Columnas a visualizar en el reporte
	private Boolean verPresupuestado = TRUE;
	private Boolean verGastado = TRUE;
	private Boolean verDiferencia = TRUE;

	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Collection<FormatoReporteEnum> getFormatosReporte() {
		return formatosReporte;
	}

	public void setFormatosReporte(Collection<FormatoReporteEnum> formatosReporte) {
		this.formatosReporte = formatosReporte;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public Integer[] getRubrosSeleccionados() {
		return rubrosSeleccionados;
	}

	public void setRubrosSeleccionados(Integer[] rubrosSeleccionados) {
		this.rubrosSeleccionados = rubrosSeleccionados;
	}
	
	public Collection<Rubro> getRubros() {
		return rubros;
	}

	public void setRubros(Collection<Rubro> rubros) {
		this.rubros = rubros;
	}

	public String getTipoAgregacion() {
		return tipoAgregacion;
	}

	public void setTipoAgregacion(String tipoAgregacion) {
		this.tipoAgregacion = tipoAgregacion;
	}

	public String getIdAgregacion() {
		return idAgregacion;
	}

	public void setIdAgregacion(String idAgregacion) {
		this.idAgregacion = idAgregacion;
	}

	public Boolean getVerPresupuestado() {
		return verPresupuestado;
	}

	public void setVerPresupuestado(Boolean verPresupuestado) {
		this.verPresupuestado = verPresupuestado;
	}

	public Boolean getVerGastado() {
		return verGastado;
	}

	public void setVerGastado(Boolean verGastado) {
		this.verGastado = verGastado;
	}

	public Boolean getVerDiferencia() {
		return verDiferencia;
	}

	public void setVerDiferencia(Boolean verDiferencia) {
		this.verDiferencia = verDiferencia;
	}

	public void initialize() {
	}

	public void initializeForm() {
	}

	public Boolean calcularPresupuestos() {
		return verPresupuestado || verDiferencia;
	}

	public Boolean calcularGastos() {
		return verGastado || verDiferencia;
	}
}
