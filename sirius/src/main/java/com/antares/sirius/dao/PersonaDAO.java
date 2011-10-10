package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Persona;

public interface PersonaDAO extends BusinessEntityDAO<Persona> {

	/**
	 * Devuelve todas las personas del sistema excepto la que tiene un id igual al parametro. Si el parametro es null, se comporta como findAll
	 * 
	 * @param id id de Persona
	 * @return
	 */
	Collection<Persona> findAllOthers(Integer id);

	/**
	 * Devuelve la persona a partir de su nombre y apellido
	 * 
	 * @param nombre de la persona
	 * @param apellido de la persona
	 * @return
	 */
	Persona findByNombreApellido(String nombre, String apellido);

}
