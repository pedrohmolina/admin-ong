/**
 *
 */
package com.antares.sirius.base.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class InitCheckServlet implements Servlet {

	public void destroy() {
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig config) throws ServletException {
		try {
			doInit();
		} catch (Exception e) {
			System.out.println("Hubo un problema al iniciar Sirius: " + e);
		}
	}

	private void doInit() throws ServletException {
		System.out.println("Chequeando el estado del entorno para poder levantar la aplicaci�n ...");

//		String path = System.getProperty(CONFIGURATION_DIR);
//		if (path == null || path.equals("")) {
//			throw new ServletException("La aplicaci�n no puede levantar porque no est� seteada la variable de entorno '" + CONFIGURATION_DIR + "' (el valor actual es " + path + ")");
//		}
//		System.out.println("El valor de la variable de entorno '" + CONFIGURATION_DIR + "' es: " + path);
//
//		System.out.println("Chequeando si est�n los archivos de configuraci�n necesarios ...");
//		File file = new File(path + System.getProperty("file.separator") + CONFIGURATION_FILE);
//		if (!file.exists()) {
//			String message = "La aplicaci�n no puede levantar porque no existe el archivo de configuraci�n 'configuration.properties' ('" + path + System.getProperty("file.separator") + CONFIGURATION_FILE + "')";
//			throw new ServletException(message);
//		}
//		System.out.println("Se encontr� configuration.properties ...");
//
//		file = new File(path + System.getProperty("file.separator") + CONFIGURATION_LOG);
//		if (!file.exists()) {
//			System.out.println("No se encuentra la configuraci�n de log4j");
//		}
//		System.out.println("Se encontr� " + CONFIGURATION_LOG + " ...");

	}

	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
	}

}
