package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Origen;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.Proveedor;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.model.TipoComprobante;

/**
 * Representacion en la capa de vista de la entidad de modelo Gasto.
 *
 * @version 1.0.0 Created 16/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class GastoForm extends AbstractForm<Gasto> {

	private Integer idPersona;
	private String fecha;
	private String observaciones;
	private String importe;
	private String numeroComprobante;
	private String paquete;
	private String idRubro;
	private String idOrigen;
	private String idProveedor;
	private String idTipoComprobante;
	private String idProyecto;
	private String idActividad;
	private Collection<Rubro> rubros;
	private Collection<Origen> origenes;
	private Collection<Proveedor> proveedores;
	private Collection<TipoComprobante> tiposComprobante;
	private Collection<Proyecto> proyectos;
	private Collection<Actividad> actividades;
	private Collection<Persona> personas;

	private String filtroIdPersona;
	private String filtroIdRubro;
	private String filtroIdOrigen;
	private String filtroIdProveedor;
	private String filtroFecha;
	private String filtroIdProyecto;
	private String filtroIdActividad;

	private String labelRubro;
	private String labelOrigen;
	private String labelProveedor;
	private String labelTipoComprobante;
	private String labelProyecto;
	private String labelActividad;

	private boolean individual;
	private boolean agrupado;

	private String referencia = "";
	private String updated = "";

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(String idRubro) {
		this.idRubro = idRubro;
	}

	public String getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getIdTipoComprobante() {
		return idTipoComprobante;
	}

	public void setIdTipoComprobante(String idTipoComprobante) {
		this.idTipoComprobante = idTipoComprobante;
	}

	public Collection<Rubro> getRubros() {
		return rubros;
	}

	public void setRubros(Collection<Rubro> rubros) {
		this.rubros = rubros;
	}

	public Collection<Origen> getOrigenes() {
		return origenes;
	}

	public void setOrigenes(Collection<Origen> origenes) {
		this.origenes = origenes;
	}

	public Collection<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(Collection<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Collection<TipoComprobante> getTiposComprobante() {
		return tiposComprobante;
	}

	public void setTiposComprobante(Collection<TipoComprobante> tiposComprobante) {
		this.tiposComprobante = tiposComprobante;
	}

	public String getFiltroIdPersona() {
		return filtroIdPersona;
	}

	public void setFiltroIdPersona(String filtroIdPersona) {
		this.filtroIdPersona = filtroIdPersona;
	}

	public String getFiltroIdRubro() {
		return filtroIdRubro;
	}

	public void setFiltroIdRubro(String filtroIdRubro) {
		this.filtroIdRubro = filtroIdRubro;
	}

	public String getFiltroIdOrigen() {
		return filtroIdOrigen;
	}

	public void setFiltroIdOrigen(String filtroIdOrigen) {
		this.filtroIdOrigen = filtroIdOrigen;
	}

	public String getFiltroIdProveedor() {
		return filtroIdProveedor;
	}

	public void setFiltroIdProveedor(String filtroIdProveedor) {
		this.filtroIdProveedor = filtroIdProveedor;
	}

	public String getFiltroFecha() {
		return filtroFecha;
	}

	public void setFiltroFecha(String filtroFecha) {
		this.filtroFecha = filtroFecha;
	}

	public String getLabelRubro() {
		return labelRubro;
	}

	public void setLabelRubro(String labelRubro) {
		this.labelRubro = labelRubro;
	}

	public String getLabelOrigen() {
		return labelOrigen;
	}

	public void setLabelOrigen(String labelOrigen) {
		this.labelOrigen = labelOrigen;
	}

	public String getLabelProveedor() {
		return labelProveedor;
	}

	public void setLabelProveedor(String labelProveedor) {
		this.labelProveedor = labelProveedor;
	}

	public String getLabelTipoComprobante() {
		return labelTipoComprobante;
	}

	public void setLabelTipoComprobante(String labelTipoComprobante) {
		this.labelTipoComprobante = labelTipoComprobante;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Collection<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Collection<Actividad> actividades) {
		this.actividades = actividades;
	}

	public String getFiltroIdProyecto() {
		return filtroIdProyecto;
	}

	public void setFiltroIdProyecto(String filtroIdProyecto) {
		this.filtroIdProyecto = filtroIdProyecto;
	}

	public String getFiltroIdActividad() {
		return filtroIdActividad;
	}

	public void setFiltroIdActividad(String filtroIdActividad) {
		this.filtroIdActividad = filtroIdActividad;
	}

	public String getLabelProyecto() {
		return labelProyecto;
	}

	public void setLabelProyecto(String labelProyecto) {
		this.labelProyecto = labelProyecto;
	}

	public String getLabelActividad() {
		return labelActividad;
	}

	public void setLabelActividad(String labelActividad) {
		this.labelActividad = labelActividad;
	}

	public Collection<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Collection<Persona> personas) {
		this.personas = personas;
	}

	public boolean getIndividual() {
		return id == null || individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}

	public boolean getAgrupado() {
		return id == null || agrupado;
	}

	public void setAgrupado(boolean agrupado) {
		this.agrupado = agrupado;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.idPersona = null;
		this.fecha = "";
		this.observaciones = "";
		this.importe = "";
		this.numeroComprobante = "";
		this.paquete = "";
		this.idRubro = "";
		this.idOrigen = "";
		this.idProveedor = "";
		this.idTipoComprobante = "";
		this.idProyecto = "";
		this.idActividad = "";
		this.filtroIdPersona = "";
		this.filtroIdRubro = "";
		this.filtroIdOrigen = "";
		this.filtroIdProveedor = "";
		this.filtroIdProyecto = "";
		this.filtroIdActividad = "";
		this.filtroFecha = "";
		this.labelRubro = "";
		this.labelOrigen = "";
		this.labelProveedor = "";
		this.labelTipoComprobante = "";
		this.labelProyecto = "";
		this.labelActividad = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.idPersona = null;
		this.fecha = "";
		this.observaciones = "";
		this.importe = "";
		this.numeroComprobante = "";
		this.paquete = "";
		this.idRubro = "";
		this.idOrigen = "";
		this.idProveedor = "";
		this.idTipoComprobante = "";
		this.idProyecto = "";
		this.idActividad = "";
		this.labelRubro = "";
		this.labelOrigen = "";
		this.labelProveedor = "";
		this.labelTipoComprobante = "";
		this.labelProyecto = "";
		this.labelActividad = "";
	}

	@Override
	public void initializeForm(Gasto entity) {
		this.id = entity.getId();
		this.idPersona = entity.getPersona().getId();
		this.fecha = Utils.formatDate(entity.getFecha());
		this.observaciones = entity.getObservaciones();
		this.importe = Utils.formatDouble(entity.getImporte());
		this.numeroComprobante = entity.getNumeroComprobante();
		this.paquete = entity.getPaquete();
		this.idRubro = entity.getRubro().getId().toString();
		this.idOrigen = entity.getOrigen().getId().toString();
		this.labelRubro = entity.getRubro().getDescripcion();
		this.labelOrigen = entity.getOrigen().getDescripcion();
		
		if (entity.getProveedor() != null) {
			this.idProveedor = entity.getProveedor().getId().toString();
			this.labelProveedor = entity.getProveedor().getNombre();
		} else {
			this.idProveedor = "";
			this.labelProveedor = "";
		}

		if (entity.getTipoComprobante() != null) {
			this.idTipoComprobante = entity.getTipoComprobante().getId().toString();
			this.labelTipoComprobante = entity.getTipoComprobante().getDescripcion();
		} else {
			this.idTipoComprobante = null;
			this.labelTipoComprobante = null;
		}

		if (entity.getProyecto() != null) {
			this.idProyecto = entity.getProyecto().getId().toString();
			this.labelProyecto = entity.getProyecto().getNombre();
		}

		if (entity.getActividad() != null) {
			this.idProyecto = entity.getActividad().getProyecto().getId().toString();
			this.idActividad = entity.getActividad().getId().toString();
			this.labelProyecto = entity.getActividad().getProyecto().getNombre();
			this.labelActividad = entity.getActividad().getNombre();
		}
	}

}
