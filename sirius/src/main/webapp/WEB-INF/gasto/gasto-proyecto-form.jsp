<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>

<script>
$(document).ready(function() {
	changeProyecto();
})

function changeProyecto() {
	var idProyecto = $("#proyecto").val();

	url = "<c:url value='/gasto/gasto-proyecto-form.do?method=isIndividual' />";
	$.getJSON(url, {idProyecto:idProyecto}, function(json){
		if (json.isIndividual == "true") {
			$("#comboProveedor").attr("disabled", false);
			$("#comboTipoComprobante").attr("disabled", false);
			$("#numeroComprobante").attr("disabled", false);
		} else {
			$("#comboProveedor").attr("disabled", true);
			$("#comboTipoComprobante").attr("disabled", true);
			$("#numeroComprobante").attr("disabled", true);
		}
	});
}
</script>

<div class="form">
<html:form action="/gasto/gasto-proyecto-form-validate.do?method=save" styleId="abmForm">

	<div style="float: left;">
		<p>
		<label for="idProyecto"><bean:message key="sirius.gasto.proyecto.label" />(*)&nbsp;:</label>
		<logic:equal name="gastoProyectoForm" property="action.descripcion" value="create">
			<html:select property="idProyecto" styleId="proyecto" onchange="changeProyecto();">
				<html:option value=""><bean:message key="antares.base.seleccione.label" /></html:option>
				<html:optionsCollection name="gastoProyectoForm" property="proyectos" label="nombre" value="id" />
			</html:select>
		</logic:equal>
		<logic:equal name="gastoProyectoForm" property="action.descripcion" value="update">
			<html:text property="labelProyecto" readonly="true" />
		</logic:equal>
		</p><br><p>
		<label for="fecha"><bean:message key="sirius.gasto.fecha.label" />(*)&nbsp;:</label>
		<html:text property="fecha" styleClass="datepicker"/>
		</p><br><p>
		<label for="idRubro"><bean:message key="sirius.gasto.rubro.label" />(*)&nbsp;:</label>
		<html:select property="idRubro">
			<html:option value=""> <bean:message key="antares.base.seleccione.label" /> </html:option>
			<html:optionsCollection name="gastoProyectoForm" property="rubros" label="nombre" value="id" />
		</html:select>
		</p><br><p>
		<label for="idOrigen"><bean:message key="sirius.gasto.origen.label" />(*)&nbsp;:</label>
		<html:select property="idOrigen">
			<html:option value=""><bean:message key="antares.base.seleccione.label" /> </html:option>
			<html:optionsCollection name="gastoProyectoForm" property="origenes" label="descripcion" value="id" />
		</html:select>
		</p><br>
		<logic:equal name="gastoProyectoForm" property="individual"	value="true">
			<p>
			<label for="idProveedor"><bean:message key="sirius.gasto.proveedor.label" />(*)&nbsp;:</label>
			<html:select property="idProveedor" styleId="comboProveedor">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="gastoProyectoForm" property="proveedores" label="nombre" value="id"/>
			</html:select>
			</p><br><p>
			<label for="idTipoComprobante"><bean:message key="sirius.gasto.tipoComprobante.label" />(*)&nbsp;:</label>
			<html:select property="idTipoComprobante" styleId="comboTipoComprobante">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="gastoProyectoForm" property="tiposComprobante" label="descripcion" value="id"/>
			</html:select>
			</p><br>
		</logic:equal>
		<p>
		<label for="observaciones"><bean:message key="sirius.gasto.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		</p><br><p>
		<label for="importe"><bean:message key="sirius.gasto.importe.label" />(*)&nbsp;:</label>
		<html:text property="importe" />
		</p><br>
		<logic:equal name="gastoProyectoForm" property="individual" value="true">
			<p>
			<label for="numeroComprobante"><bean:message key="sirius.gasto.numeroComprobante.label" />(*)&nbsp;:</label>
			<html:text property="numeroComprobante" styleId="numeroComprobante" />
			</p><br>
		</logic:equal>
	</div>

	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="gastoProyectoForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/gasto/gasto-proyecto-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">	
</script>
