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
<html:form action="/gasto/gasto-proyecto-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<label for="labelProyecto"><bean:message key="sirius.gasto.proyecto.label" />:</label>
		<html:text property="labelProyecto" readonly="true" />
		<br>
		<label for="fecha"><bean:message key="sirius.gasto.fecha.label" />:</label>
		<html:text property="fecha" readonly="true" />
		<br>
		<label for="labelRubro"><bean:message key="sirius.gasto.rubro.label" />:</label>
		<html:text property="labelRubro" readonly="true" />
		<br>
		<label for="labelOrigen"><bean:message key="sirius.gasto.origen.label" />:</label>
		<html:text property="labelOrigen" readonly="true" />
		<br>
		<logic:equal name="gastoProyectoForm.individual" value="true">
			<label for="labelProveedor"><bean:message key="sirius.gasto.proveedor.label" />:</label>
			<html:text property="labelProveedor" readonly="true" />
			<br>
			<label for="labelTipoComprobante"><bean:message key="sirius.gasto.tipoComprobante.label" />:</label>
			<html:text property="labelTipoComprobante" readonly="true" />
			<br>
		</logic:equal>
		<label for="observaciones"><bean:message key="sirius.gasto.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" readonly="true" />
		<br>
		<label for="importe"><bean:message key="sirius.gasto.importe.label" />:</label>
		<html:text property="importe" readonly="true" />
		<br>
		<logic:equal name="gastoProyectoForm.individual" value="true">
			<label for="numeroComprobante"><bean:message key="sirius.gasto.numeroComprobante.label" />:</label>
			<html:text property="numeroComprobante" readonly="true" />
			<br>
		</logic:equal>
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/gasto/gasto-proyecto-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

