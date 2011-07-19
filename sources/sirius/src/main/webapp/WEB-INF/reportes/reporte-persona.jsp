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

	<div style="float:left; width: 100%;">
		<p>
		<h2><bean:message key="sirius.reportes.filtros" /></h2>
		</p><p>
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
		</p><br>
	</div>

	<br>
	<br>

	<div style="float: left; width: 100%;">
	<p>
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="return hacerSubmit('/reportes/reporte-persona.do?method=verResultados');"><bean:message key="sirius.reportes.vistaPrevia" /></a>
		</div>
	<br>	
	</div>

	<div style="float: left; width: 100%;">
	<p>	
	<h2><bean:message key="sirius.reportes.vistaPreviaResultados" /></h2>
	<display-el:table export="false" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.reportePersonaForm.result" id="item"
		requestURI="/reportes/reporte-persona.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="apellido" 					titleKey="sirius.persona.apellido.label" />
		<display:column sortable="true" property="nombre" 						titleKey="sirius.persona.nombre.label" />
		<display:column sortable="true" property="tipoDocumento.descripcion"	titleKey="sirius.persona.tipoDocumento.label" />
		<display:column sortable="true" property="numeroDocumento" 				titleKey="sirius.persona.numeroDocumento.label" />

	</display-el:table>
	<br>	
	</div>

	<div style="float:left; width: 100%;">
	<p>	
		<h2><bean:message key="sirius.reportes.columnas" /></h2>
		<p>
			<label for="numeroDocumento"><bean:message key="sirius.persona.numeroDocumento.label" />:</label>
			<html:checkbox property="verNumeroDocumento" value="true" style="width:20px" />
			<html:hidden property="verNumeroDocumento" value="false" />
		</p><br><p>
			<label for="cuit"><bean:message key="sirius.persona.cuit.label" />:</label>
			<html:checkbox property="verCuit" value="true" style="width:20px" />
			<html:hidden property="verCuit" value="false" />
		</p><br><p>
			<label for="cbu"><bean:message key="sirius.persona.cbu.label" />:</label>
			<html:checkbox property="verCBU" value="true" style="width:20px" />
			<html:hidden property="verCBU" value="false" />
		</p><br><p>
			<label for="fechaNacimiento"><bean:message key="sirius.persona.fechaNacimiento.label" />:</label>
			<html:checkbox property="verFechaNacimiento" value="true" style="width:20px" />
			<html:hidden property="verFechaNacimiento" value="false" />
		</p><br><p>
			<label for="profesion"><bean:message key="sirius.persona.profesion.label" />:</label>
			<html:checkbox property="verProfesion" value="true" style="width:20px" />
			<html:hidden property="verProfesion" value="false" />
		</p><br><p>
			<label for="direccion"><bean:message key="sirius.persona.direccion.label" />:</label>
			<html:checkbox property="verDireccion" value="true" style="width:20px" />
			<html:hidden property="verDireccion" value="false" />
		</p><br><p>
			<label for="telefono"><bean:message key="sirius.persona.telefono.label" />:</label>
			<html:checkbox property="verTelefono" value="true" style="width:20px" />
			<html:hidden property="verTelefono" value="false" />
		</p><br><p>
			<label for="email"><bean:message key="sirius.persona.email.label" />:</label>
			<html:checkbox property="verEmail" value="true" style="width:20px" />
			<html:hidden property="verEmail" value="false" />
		</p><br><p>
			<label for="funcion"><bean:message key="sirius.persona.funcion.label" />:</label>
			<html:checkbox property="verFuncion" value="true" style="width:20px" />
			<html:hidden property="verFuncion" value="false" />
		</p><br><p>
			<label for="relacionContractual"><bean:message key="sirius.persona.relacionContractual.label" />:</label>
			<html:checkbox property="verRelacionContractual" value="true" style="width:20px" />
			<html:hidden property="verRelacionContractual" value="false" />
		</p><br>
		</table>
		<br>
	</div>

	<div style="float: left; width: 100%;">
		<p>
		<label for="formatosReporte"><bean:message key="sirius.reportes.formatoSalida" />:</label>
		<html:select property="formatoReporte">
			<html:optionsCollection name="reportePersonaForm" property="formatosReporte" label="descripcion" value="id"/>
		</html:select>
		<div class="boton">
			<a href="#" onclick="reportePersonaForm.submit();"><bean:message key="sirius.reportes.generar" /></a>
		</div>
		<br>
	</div>
	
	</html:form>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

</div>
