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
<html:form action="/proveedor/proveedor-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<label for="nombre"><bean:message key="sirius.proveedor.nombre.label" />&nbsp;:</label>
		<html:text property="nombre" readonly="true" />
		<br>
		<label for="idTipoProveedor"><bean:message key="sirius.proveedor.tipoProveedor.label" />&nbsp;:</label>
		<html:text property="labelTipoProveedor" readonly="true" />
		<br>
		<label for="cuit"><bean:message key="sirius.proveedor.cuit.label" />&nbsp;:</label>
		<html:text property="cuit" readonly="true" />
		<br>
		<label for="cbu"><bean:message key="sirius.proveedor.cbu.label" />&nbsp;:</label>
		<html:text property="cbu" readonly="true" />
		<br>
		<label for="direccion"><bean:message key="sirius.proveedor.direccion.label" />&nbsp;:</label>
		<html:text property="direccion" readonly="true" />
		<br>
		<label for="contacto"><bean:message key="sirius.proveedor.contacto.label" />&nbsp;:</label>
		<html:text property="contacto" readonly="true" />
		<br>
		<label for="telefono"><bean:message key="sirius.proveedor.telefono.label" />&nbsp;:</label>
		<html:text property="telefono" readonly="true" />
		<br>
		<label for="celular"><bean:message key="sirius.proveedor.celular.label" />&nbsp;:</label>
		<html:text property="celular" readonly="true" />
		<br>
		<label for="email"><bean:message key="sirius.proveedor.email.label" />&nbsp;:</label>
		<html:text property="email" readonly="true" />
		<br>
		<label for="observaciones"><bean:message key="sirius.proveedor.observaciones.label" />&nbsp;:</label>
		<html:textarea property="observaciones" rows="5" />
		<br>
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/proveedor/proveedor-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

