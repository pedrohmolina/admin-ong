<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<div class="boxTitulo">
	<div class="bi">
		<div class="bt"><div></div></div>
		<h1>Datos</h1>
		<div class="bb"><div></div></div>
	</div>
</div>
<div class="form">
<html:form action="/usuario/usuario-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<label for="username"><bean:message key="sirius.usuario.username.label" />&nbsp;:</label>
		<html:text property="username" readonly="true" />
		<br>
		<label for="labelPerfil"><bean:message key="sirius.usuario.perfil.label" />&nbsp;:</label>
		<html:text property="labelPerfil" readonly="true" />
		<br>
		<label for="apellido"><bean:message key="sirius.usuario.apellido.label" />&nbsp;:</label>
		<html:text property="apellido" readonly="true" />
		<br>
		<label for="nombre"><bean:message key="sirius.usuario.nombre.label" />&nbsp;:</label>
		<html:text property="nombre" readonly="true" />
		<br>
		<label for="segundoNombre"><bean:message key="sirius.usuario.segundoNombre.label" />&nbsp;:</label>
		<html:text property="segundoNombre" readonly="true" />
		<br>
		<label for="idTipoDocumento"><bean:message key="sirius.usuario.tipoDocumento.label" />&nbsp;:</label>
		<html:text property="labelTipoDocumento" readonly="true" />
		<br>
		<label for="numeroDocumento"><bean:message key="sirius.usuario.numeroDocumento.label" />&nbsp;:</label>
		<html:text property="numeroDocumento" readonly="true" />
		<br>
		<label for="cuit"><bean:message key="sirius.usuario.cuit.label" />&nbsp;:</label>
		<html:text property="cuit" readonly="true" />
		<br>
		<label for="cbu"><bean:message key="sirius.usuario.cbu.label" />&nbsp;:</label>
		<html:text property="cbu" readonly="true" />
		<br>
		<label for="nacionalidad"><bean:message key="sirius.usuario.nacionalidad.label" />&nbsp;:</label>
		<html:text property="nacionalidad" readonly="true" />
		<br>
		<label for="fechaNacimiento"><bean:message key="sirius.usuario.fechaNacimiento.label" />&nbsp;:</label>
		<html:text property="fechaNacimiento" readonly="true" />
		<br>
		<label for="profesion"><bean:message key="sirius.usuario.profesion.label" />&nbsp;:</label>
		<html:text property="profesion" readonly="true" />
		<br>
		<label for="direccion"><bean:message key="sirius.usuario.direccion.label" />&nbsp;:</label>
		<html:text property="direccion" readonly="true" />
		<br>
		<label for="telefono"><bean:message key="sirius.usuario.telefono.label" />&nbsp;:</label>
		<html:text property="telefono" readonly="true" />
		<br>
		<label for="celular"><bean:message key="sirius.usuario.celular.label" />&nbsp;:</label>
		<html:text property="celular" readonly="true" />
		<br>
		<label for="idRelacionContractual"><bean:message key="sirius.usuario.relacionContractual.label" />&nbsp;:</label>
		<html:text property="labelRelacionContractual" readonly="true" />
		<br>
		<label for="email"><bean:message key="sirius.usuario.email.label" />&nbsp;:</label>
		<html:text property="email" readonly="true" />
		<br>
		<label for="funcion"><bean:message key="sirius.usuario.funcion.label" />&nbsp;:</label>
		<html:text property="funcion" readonly="true" />
		<br>
		<label for="observaciones"><bean:message key="sirius.usuario.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" readonly="true" />
		<br>
		<label for="labelFormaPago"><bean:message key="sirius.usuario.formaPago.label" />&nbsp;:</label>
		<html:text property="labelFormaPago" readonly="true" />
		<br>
		<label for="labelPersonaFactura"><bean:message key="sirius.usuario.personaFactura.label" />&nbsp;:</label>
		<html:text property="labelPersonaFactura" readonly="true" />
		<br>
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/usuario/usuario-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

