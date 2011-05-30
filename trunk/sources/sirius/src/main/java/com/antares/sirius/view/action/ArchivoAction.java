package com.antares.sirius.view.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.antares.sirius.model.Archivo;
import com.antares.sirius.service.ArchivoService;

public class ArchivoAction extends DispatchAction {

	private ArchivoService archivoService;

	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hash = request.getParameter("hash");
		Archivo archivo = archivoService.findByHash(hash);
		copyFile(archivo, response);
		return null;
	}

	private void copyFile(Archivo archivo, HttpServletResponse response) throws IOException, SQLException {
		InputStream in = archivo.getContenido().getBinaryStream();
		OutputStream out = response.getOutputStream();
		
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + archivo.getNombre() + "\"");
		byte[] outputByte = new byte[4096];
		while(in.read(outputByte, 0, 4096) != -1) {
			out.write(outputByte, 0, 4096);
		}

		in.close();
		out.flush();
		out.close();
	}

	public ArchivoService getArchivoService() {
		return archivoService;
	}

	public void setArchivoService(ArchivoService archivoService) {
		this.archivoService = archivoService;
	}

}
