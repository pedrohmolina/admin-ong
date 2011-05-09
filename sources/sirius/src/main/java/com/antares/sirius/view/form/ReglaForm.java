package com.antares.sirius.view.form;

import java.util.Collection;

import com.antares.commons.util.Utils;
import com.antares.commons.view.form.AbstractForm;
import com.antares.sirius.model.Atributo;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.Operador;
import com.antares.sirius.model.PersistentObject;
import com.antares.sirius.model.Regla;
import com.antares.sirius.model.Usuario;

/**
 * Representacion en la capa de vista de la entidad de modelo Regla.
 *
 * @version 1.0.0 Created 23/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public class ReglaForm extends AbstractForm<Regla> {

	private String idUsuario;
	private String idEntidad;
	private String idAtributo;
	private String idOperador;
	private String valor;
	private String valorNumerico;
	private String valorFecha;
	private String valorCombo;
	private Collection<Usuario> usuarios;
	private Collection<Entidad> entidades;
	private Collection<Atributo> atributos;
	private Collection<Operador> operadores;
	private Collection<PersistentObject> valores;

	private String filtroIdUsuario;

	private String labelUsuario;
	private String labelEntidad;
	private String labelAtributo;
	private String labelOperador;
	private String labelValor;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getIdAtributo() {
		return idAtributo;
	}

	public void setIdAtributo(String idAtributo) {
		this.idAtributo = idAtributo;
	}

	public String getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(String idOperador) {
		this.idOperador = idOperador;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Collection<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(Collection<Entidad> entidades) {
		this.entidades = entidades;
	}

	public Collection<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(Collection<Atributo> atributos) {
		this.atributos = atributos;
	}

	public Collection<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(Collection<Operador> operador) {
		this.operadores = operador;
	}

	public String getFiltroIdUsuario() {
		return filtroIdUsuario;
	}

	public void setFiltroIdUsuario(String filtroIdUsuario) {
		this.filtroIdUsuario = filtroIdUsuario;
	}

	public String getLabelUsuario() {
		return labelUsuario;
	}

	public void setLabelUsuario(String labelUsuario) {
		this.labelUsuario = labelUsuario;
	}

	public String getLabelEntidad() {
		return labelEntidad;
	}

	public void setLabelEntidad(String labelEntidad) {
		this.labelEntidad = labelEntidad;
	}

	public String getLabelAtributo() {
		return labelAtributo;
	}

	public void setLabelAtributo(String labelAtributo) {
		this.labelAtributo = labelAtributo;
	}

	public String getLabelOperador() {
		return labelOperador;
	}

	public void setLabelOperador(String labelOperador) {
		this.labelOperador = labelOperador;
	}

	public String getValorFecha() {
		return valorFecha;
	}

	public void setValorFecha(String valorFecha) {
		this.valorFecha = valorFecha;
	}

	public String getValorCombo() {
		return valorCombo;
	}

	public void setValorCombo(String valorCombo) {
		this.valorCombo = valorCombo;
	}

	public Collection<PersistentObject> getValores() {
		return valores;
	}

	public void setValores(Collection<PersistentObject> valores) {
		this.valores = valores;
	}

	public String getValorNumerico() {
		return valorNumerico;
	}

	public void setValorNumerico(String valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	@Override
	public void initialize() {
		this.id = null;
		this.result = null;
		this.idUsuario = "";
		this.idEntidad = "";
		this.idAtributo = "";
		this.idOperador = "";
		this.valor = "";
		this.valorNumerico = "";
		this.valorFecha = "";
		this.valorCombo = "";
		this.filtroIdUsuario = "";
		this.labelUsuario = "";
		this.labelEntidad = "";
		this.labelAtributo = "";
		this.labelOperador = "";
		this.labelValor = "";
	}

	@Override
	public void initializeForm() {
		this.id = null;
		this.idUsuario = "";
		this.idEntidad = "";
		this.idAtributo = "";
		this.idOperador = "";
		this.valor = "";
		this.valorNumerico = "";
		this.valorFecha = "";
		this.valorCombo = "";
		this.labelUsuario = "";
		this.labelEntidad = "";
		this.labelAtributo = "";
		this.labelOperador = "";
		this.labelValor = "";
	}

	@Override
	public void initializeForm(Regla entity) {
		this.id = entity.getId();
		this.idUsuario = entity.getUsuario().getId().toString();
		this.idEntidad = entity.getEntidad().getId().toString();
		this.idAtributo = entity.getAtributo().getId().toString();
		this.idOperador = entity.getOperador().getId().toString();
		this.labelUsuario = entity.getUsuario().getUsername();
		this.labelEntidad = entity.getEntidad().getDescripcion();
		this.labelAtributo = Utils.getMessage(entity.getAtributo().getDescripcion());
		this.labelOperador = entity.getOperador().getDescripcion();
		this.labelValor = entity.getValorDescripcion();
	}

	public String getLabelValor() {
		return labelValor;
	}

	public void setLabelValor(String labelValor) {
		this.labelValor = labelValor;
	}

}
