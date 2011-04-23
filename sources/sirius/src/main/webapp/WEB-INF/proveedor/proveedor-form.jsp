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
<html:form action="/proveedor/proveedor-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="nombre"><bean:message key="sirius.proveedor.nombre.label" />(*)&nbsp;:</label>
		<html:text property="nombre" />
		<br>
		<label for="idTipoProveedor"><bean:message key="sirius.proveedor.tipoProveedor.label" />(*)&nbsp;:</label>
		<html:select property="idTipoProveedor">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proveedorForm" property="tiposProveedor" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="cuit"><bean:message key="sirius.proveedor.cuit.label" />&nbsp;:</label>
		<html:text property="cuit" />
		<br>
		<label for="cbu"><bean:message key="sirius.proveedor.cbu.label" />&nbsp;:</label>
		<html:text property="cbu" />
		<br>
		<label for="direccion"><bean:message key="sirius.proveedor.direccion.label" />(*)&nbsp;:</label>
		<html:text property="direccion" />
		<br>
		<label for="contacto"><bean:message key="sirius.proveedor.contacto.label" />&nbsp;:</label>
		<html:text property="contacto" />
		<br>
		<label for="telefono"><bean:message key="sirius.proveedor.telefono.label" />(*)&nbsp;:</label>
		<html:text property="telefono" />
		<br>
		<label for="celular"><bean:message key="sirius.proveedor.celular.label" />&nbsp;:</label>
		<html:text property="celular" />
		<br>
		<label for="email"><bean:message key="sirius.proveedor.email.label" />&nbsp;:</label>
		<html:text property="email" />
		<br>
		<label for="observaciones"><bean:message key="sirius.proveedor.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" readonly="true" />
		<br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="proveedorForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/proveedor/proveedor-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
