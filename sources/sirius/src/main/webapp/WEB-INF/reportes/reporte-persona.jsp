<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<script>
function limpiarFiltro(){
	hacerSubmit('reportes/reporte-persona.do?method=initForm');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/reportes/reporte-persona.do?method=generarReportePersona">
	<h1>Reporte de Personas</h1>
	
	<div style="float:left; width: 100%;">
	<p>
		<h2>Filtros</h2>
		
		<label for="apellido"><bean:message key="sirius.persona.apellido.label" />:</label>
		<html:text property="apellido" />
		</p><br><p>
		<label for="nombre"><bean:message key="sirius.persona.nombre.label" />:</label>
		<html:text property="nombre" />
		</p><br><p>
		<label for="cuit"><bean:message key="sirius.persona.cuit.label" />:</label>
		<html:text property="cuit" />
		</p><br><p>
		<label for="numeroDocumento"><bean:message key="sirius.persona.numeroDocumento.label" />:</label>
		<html:text property="numeroDocumento" />
		</p><br><p>
		<label for="formatosReporte"><bean:message key="sirius.reportes.formatoSalida" />:</label>
		<html:select property="formatoReporte">
			<html:optionsCollection name="reportePersonaForm" property="formatosReporte" label="descripcion" value="descripcion"/>
		</html:select>
		</p><br>
	</div>

	<br>
	<br>
	
	<div style="float:left;">
	<p>	
		<h2>Columnas</h2>
		
		<table>
			<tr>
				<td>
					<label for="numeroDocumento"><bean:message key="sirius.persona.numeroDocumento.label" />:</label>
				</td>
				<td>
					<html:checkbox property="verNumeroDocumento" />
				</td>
				<td>
					<label for="cuit"><bean:message key="sirius.persona.cuit.label" />:</label>
				</td>
				<td>
					<html:checkbox property="verCuit" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="cbu"><bean:message key="sirius.persona.cbu.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verCBU" />
 				</td>
				<td>
					<label for="fechaNacimiento"><bean:message key="sirius.persona.fechaNacimiento.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verFechaNacimiento" />
 				</td>
			</tr>
			<tr>
				<td>
					<label for="profesion"><bean:message key="sirius.persona.profesion.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verProfesion" />
 				</td>
				<td>
					<label for="direccion"><bean:message key="sirius.persona.direccion.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verDireccion" />
 				</td>
			</tr>
			<tr>
				<td>
					<label for="telefono"><bean:message key="sirius.persona.telefono.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verTelefono" />
 				</td>
				<td>
					<label for="email"><bean:message key="sirius.persona.email.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verEmail" />
 				</td>
			</tr>
			<tr>
				<td>
					<label for="funcion"><bean:message key="sirius.persona.funcion.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verFuncion" />
 				</td>
				<td>
					<label for="relacionContractual"><bean:message key="sirius.persona.relacionContractual.label" />:</label>
 				</td>
				<td>
					<html:checkbox property="verRelacionContractual" />
 				</td>
			</tr>
		


		</table>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="reportePersonaForm.submit();"><bean:message key="sirius.reportes.generar" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

</div>
