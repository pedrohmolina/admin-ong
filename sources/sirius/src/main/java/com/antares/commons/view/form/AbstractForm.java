package com.antares.commons.view.form;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

import com.antares.commons.enums.ActionEnum;

/**
 * ActionForm abstracto para los ABMs.
 *
 * @version 1.0.0 Created 22/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("serial")
public abstract class AbstractForm<T> extends ValidatorForm {
	
	/**
	 * Coleccion con el resultado de la consulta
	 */
	protected Collection<T> result;

	/**
	 * Accion a relaizar.
	 */
	protected ActionEnum action;

	/**
	 * Id del objeto del formulario
	 */
	protected Integer id;

	/**
	 * Blanquea todos atributos del objeto (tanto del formulario como de la consulta)
	 */
	public abstract void initialize();

	/**
	 * Blanquea todos atributos del objeto correspondientes al formulario de alta o edicion
	 */
	public abstract void initializeForm();

	/**
	 * Inicializa el formulario con los atributos correspondientes a los de la entidad pasada por parametro
	 * 
	 * @param entity objeto a partir del cual se completaran los datos del formulario 
	 */
	public abstract void initializeForm(T entity);

	public Collection<T> getResult() {
		return result;
	}

	public void setResult(Collection<T> result) {
		this.result = result;
	}

	public ActionEnum getAction() {
		return action;
	}

	public void setAction(ActionEnum action) {
		this.action = action;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
