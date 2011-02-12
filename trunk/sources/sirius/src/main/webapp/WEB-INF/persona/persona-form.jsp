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
<html:form action="/persona/persona-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="apellido"><bean:message key="sirius.persona.apellido.label" />(*)&nbsp;:</label>
		<html:text property="apellido" />
		<br>
		<label for="nombre"><bean:message key="sirius.persona.nombre.label" />(*)&nbsp;:</label>
		<html:text property="nombre" />
		<br>
		<label for="segundoNombre"><bean:message key="sirius.persona.segundoNombre.label" />&nbsp;:</label>
		<html:text property="segundoNombre" />
		<br>
		<label for="idTipoDocumento"><bean:message key="sirius.persona.tipoDocumento.label" />(*)&nbsp;:</label>
		<html:select property="idTipoDocumento">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="personaForm" property="tiposDocumento" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="numeroDocumento"><bean:message key="sirius.persona.numeroDocumento.label" />(*)&nbsp;:</label>
		<html:text property="numeroDocumento" />
		<br>
		<label for="cuit"><bean:message key="sirius.persona.cuit.label" />&nbsp;:</label>
		<html:text property="cuit" />
		<br>
		<label for="cbu"><bean:message key="sirius.persona.cbu.label" />&nbsp;:</label>
		<html:text property="cbu" />
		<br>
		<label for="nacionalidad"><bean:message key="sirius.persona.nacionalidad.label" />&nbsp;:</label>
		<html:text property="nacionalidad" />
		<br>
		<label for="fechaNacimiento"><bean:message key="sirius.persona.fechaNacimiento.label" />&nbsp;:</label>
		<html:text property="fechaNacimiento" />
		<br>
		<label for="profesion"><bean:message key="sirius.persona.profesion.label" />&nbsp;:</label>
		<html:text property="profesion" />
		<br>
		<label for="direccion"><bean:message key="sirius.persona.direccion.label" />&nbsp;:</label>
		<html:text property="direccion" />
		<br>
		<label for="telefono"><bean:message key="sirius.persona.telefono.label" />&nbsp;:</label>
		<html:text property="telefono" />
		<br>
		<label for="celular"><bean:message key="sirius.persona.celular.label" />&nbsp;:</label>
		<html:text property="celular" />
		<br>
		<label for="idRelacionContractual"><bean:message key="sirius.persona.relacionContractual.label" />(*)&nbsp;:</label>
		<html:select property="idRelacionContractual">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="personaForm" property="relacionesContractuales" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="email"><bean:message key="sirius.persona.email.label" />&nbsp;:</label>
		<html:text property="email" />
		<br>
		<label for="funcion"><bean:message key="sirius.persona.funcion.label" />&nbsp;:</label>
		<html:text property="funcion" />
		<br>
		<label for="observaciones"><bean:message key="sirius.persona.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		<br>
		<label for="idFormaPago"><bean:message key="sirius.persona.formaPago.label" />(*)&nbsp;:</label>
		<html:select property="idFormaPago">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="personaForm" property="formasPago" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="idPersonaFactura"><bean:message key="sirius.persona.personaFactura.label" />(*)&nbsp;:</label>
		<html:select property="idPersonaFactura">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="personaForm" property="personasFactura" label="nombreYApellido" value="id"/>
		</html:select>
		<br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="personaForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/persona/persona-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
