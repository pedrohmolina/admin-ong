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

<div class="form">
<html:form action="/gasto/gasto-organizacion-query.do?method=query">
	<div style="float:left;">
		<p>
		<label for="fecha"><bean:message key="sirius.gasto.fecha.label" />:</label>
		<html:text property="fecha" readonly="true" />
		</p><br><p>
		<label for="labelRubro"><bean:message key="sirius.gasto.rubro.label" />:</label>
		<html:text property="labelRubro" readonly="true" />
		</p><br><p>
		<label for="labelOrigen"><bean:message key="sirius.gasto.origen.label" />:</label>
		<html:text property="labelOrigen" readonly="true" />
		</p><br><p>
		<label for="labelProveedor"><bean:message key="sirius.gasto.proveedor.label" />:</label>
		<html:text property="labelProveedor" readonly="true" />
		</p><br><p>
		<label for="labelTipoComprobante"><bean:message key="sirius.gasto.tipoComprobante.label" />:</label>
		<html:text property="labelTipoComprobante" readonly="true" />
		</p><br><p>
		<label for="observaciones"><bean:message key="sirius.gasto.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" readonly="true" />
		</p><br><p>
		<label for="importe"><bean:message key="sirius.gasto.importe.label" />:</label>
		<html:text property="importe" readonly="true" />
		</p><br><p>
		<label for="numeroComprobante"><bean:message key="sirius.gasto.numeroComprobante.label" />:</label>
		<html:text property="numeroComprobante" readonly="true" />
		</p><br>
	</div>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/gasto/gasto-organizacion-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

