package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Ingreso;
import com.antares.sirius.model.TipoIngreso;

/**
 * Representacion en la capa de vista de la entidad de modelo Ingreso.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class IngresoForm extends AbstractForm<Ingreso> {

	private String monto;
	private String fecha;
	private String descripcion;
	private String idTipoIngreso;
	private String idFinanciador;
	
	private Collection<TipoIngreso> tiposIngreso;
	private Collection<Financiador> financiadores;

	private String filtroFecha;
	private String filtroIdTipoIngreso;

	private String labelTipoIngreso;
	private String labelFinanciador;

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdTipoIngreso() {
		return idTipoIngreso;
	}

	public void setIdTipoIngreso(String idTipoIngreso) {
		this.idTipoIngreso = idTipoIngreso;
	}

	public String getIdFinanciador() {
		return idFinanciador;
	}

	public void setIdFinanciador(String idFinanciador) {
		this.idFinanciador = idFinanciador;
	}

	public Collection<TipoIngreso> getTiposIngreso() {
		return tiposIngreso;
	}

	public void setTiposIngreso(Collection<TipoIngreso> tiposIngreso) {
		this.tiposIngreso = tiposIngreso;
	}

	public Collection<Financiador> getFinanciadores() {
		return financiadores;
	}

	public void setFinanciadores(Collection<Financiador> financiadores) {
		this.financiadores = financiadores;
	}

	public String getFiltroFecha() {
		return filtroFecha;
	}

	public void setFiltroFecha(String filtroFecha) {
		this.filtroFecha = filtroFecha;
	}

	public String getFiltroIdTipoIngreso() {
		return filtroIdTipoIngreso;
	}

	public void setFiltroIdTipoIngreso(String filtroIdTipoIngreso) {
		this.filtroIdTipoIngreso = filtroIdTipoIngreso;
	}

	public String getLabelTipoIngreso() {
		return labelTipoIngreso;
	}

	public void setLabelTipoIngreso(String labelTipoIngreso) {
		this.labelTipoIngreso = labelTipoIngreso;
	}

	public String getLabelFinanciador() {
		return labelFinanciador;
	}

	public void setLabelFinanciador(String labelFinanciador) {
		this.labelFinanciador = labelFinanciador;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.monto = "";
		this.fecha = "";
		this.descripcion = "";
		this.idTipoIngreso = "";
		this.idFinanciador = "";
		this.tiposIngreso = null;
		this.financiadores = null;
		this.filtroFecha = "";
		this.filtroIdTipoIngreso = "";
		this.labelTipoIngreso = "";
		this.labelFinanciador = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.monto = "";
		this.fecha = "";
		this.descripcion = "";
		this.idTipoIngreso = "";
		this.idFinanciador = "";
		this.labelTipoIngreso = "";
		this.labelFinanciador = "";
	}

	@Override
	public void initializeForm(Ingreso entity) {
		this.id = entity.getId();
		this.monto = Utils.formatDouble(entity.getMonto());
		this.fecha = Utils.formatDate(entity.getFecha());
		this.descripcion = entity.getDescripcion();
		this.idTipoIngreso = entity.getTipoIngreso().getId().toString();
		this.labelTipoIngreso = entity.getTipoIngreso().getDescripcion();

		if (entity.getFinanciador() != null) {
			this.idFinanciador = entity.getFinanciador().getId().toString();
			this.labelFinanciador = entity.getFinanciador().getNombre();
		} else {
			this.idFinanciador = "";
			this.labelFinanciador = "";
		}
	}

}
