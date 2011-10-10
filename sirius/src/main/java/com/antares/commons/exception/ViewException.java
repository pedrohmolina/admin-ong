package com.antares.commons.exception;

/**
 * Exception de la capa de vista.
 *
 * @version 1.0.0 Created 25/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
@SuppressWarnings("serial")
public class ViewException extends Exception {

	public ViewException(String message, Throwable cause) {
		super(message, cause);
	}

	public ViewException(String message) {
		super(message);
	}

	public ViewException(Throwable cause) {
		super(cause);
	}

}
