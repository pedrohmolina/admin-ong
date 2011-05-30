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

<div class="form">
<html:form action="/gasto/gasto-organizacion-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float: left;">
		<p>
		<label for="fecha"><bean:message key="sirius.gasto.fecha.label" />(*)&nbsp;:</label>
		<html:text property="fecha" />
		</p><br><p>
		<label for="idRubro"><bean:message key="sirius.gasto.rubro.label" />(*)&nbsp;:</label>
		<html:select property="idRubro">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoOrganizacionForm" property="rubros" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idOrigen"><bean:message key="sirius.gasto.origen.label" />(*)&nbsp;:</label>
		<html:select property="idOrigen">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoOrganizacionForm" property="origenes" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idProveedor"><bean:message key="sirius.gasto.proveedor.label" />(*)&nbsp;:</label>
		<html:select property="idProveedor">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoOrganizacionForm" property="proveedores" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idTipoComprobante"><bean:message key="sirius.gasto.tipoComprobante.label" />(*)&nbsp;:</label>
		<html:select property="idTipoComprobante">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoOrganizacionForm" property="tiposComprobante" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="observaciones"><bean:message key="sirius.gasto.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		</p><br><p>
		<label for="importe"><bean:message key="sirius.gasto.importe.label" />(*)&nbsp;:</label>
		<html:text property="importe" />
		</p><br><p>
		<label for="numeroComprobante"><bean:message key="sirius.gasto.numeroComprobante.label" />(*)&nbsp;:</label>
		<html:text property="numeroComprobante" />
		</p><br>
	</div>

	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="gastoOrganizacionForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/gasto/gasto-organizacion-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
	
</script>
