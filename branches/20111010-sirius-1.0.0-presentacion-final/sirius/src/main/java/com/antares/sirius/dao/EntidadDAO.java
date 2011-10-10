package com.antares.sirius.dao;

import com.antares.commons.dao.GenericDAO;
import com.antares.sirius.model.Entidad;

public interface EntidadDAO extends GenericDAO<Entidad> {

	Entidad findByNombreEntidad(String descripcion);

}
