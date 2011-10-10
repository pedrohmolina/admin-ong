package com.antares.commons.exception;

/**
 * Exception de la capa de acceso a datos.
 *
 * @version 1.0.0 Created 25/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
