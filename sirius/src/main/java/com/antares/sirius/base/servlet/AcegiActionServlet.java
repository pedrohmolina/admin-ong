package com.antares.sirius.base.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.context.HttpSessionContextIntegrationFilter;
import org.apache.struts.action.ActionServlet;

/**
 * Clase que extiende del ActionServlet de struts para invocar el filtro de Acegi en caso de el mismo no corra, 
 * por ejemplo en las paginas de error.
 * <code>HttpSessionContextIntegrationFilter</code> hace un control en el caso de que el filtro no se invoque dos veces,
 * por lo que no hay problemas de que este código se ejecute siempre.
 * 
 * @version 1.0.0 Created 15/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
@SuppressWarnings("serial")
public class AcegiActionServlet extends ActionServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final AcegiActionServlet a = this;
		final HttpServletRequest req = request;
		final HttpServletResponse res = response;

		FilterChain f = new FilterChain() {
			public void doFilter(ServletRequest arg0, ServletResponse arg1) throws IOException, ServletException {
				a.process(req, res);
			}
		};

		HttpSessionContextIntegrationFilter filter = new HttpSessionContextIntegrationFilter();
		filter.doFilter(request, response, f);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final AcegiActionServlet a = this;
		final HttpServletRequest req = request;
		final HttpServletResponse res = response;

		FilterChain f = new FilterChain() {
			public void doFilter(ServletRequest arg0, ServletResponse arg1) throws IOException, ServletException {
				a.process(req, res);
			}
		};

		HttpSessionContextIntegrationFilter filter = new HttpSessionContextIntegrationFilter();
		filter.doFilter(request, response, f);
	}

}
