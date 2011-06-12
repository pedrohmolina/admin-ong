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
<html:form action="/usuario/usuario-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<p>
		<label for="username"><bean:message key="sirius.usuario.username.label" />(*)&nbsp;:</label>
		<logic:equal name="usuarioForm" property="action.descripcion" value="create">
			<html:text property="username" />
		</logic:equal>
		<logic:equal name="usuarioForm" property="action.descripcion" value="update">
			<html:text property="username" readonly="true"/>
		</logic:equal>
		</p><br>
		<logic:equal name="usuarioForm" property="action.descripcion" value="create">
			<p>
			<label for="password"><bean:message key="sirius.usuario.password.label" />(*)&nbsp;:</label>
			<html:password property="password" />
			</p><br><p>
			<label for="password2"><bean:message key="sirius.usuario.password2.label" />(*)&nbsp;:</label>
			<html:password property="password2" />
			</p><br>
		</logic:equal>
		<p>
		<label for="idPerfil"><bean:message key="sirius.usuario.perfil.label" />(*)&nbsp;:</label>
		<html:select property="idPerfil">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioForm" property="perfiles" label="nombre" value="id"/>
		</html:select>
		</p><br><p>

		<label for="apellido"><bean:message key="sirius.usuario.apellido.label" />(*)&nbsp;:</label>
		<html:text property="apellido" />
		</p><br><p>
		<label for="nombre"><bean:message key="sirius.usuario.nombre.label" />(*)&nbsp;:</label>
		<html:text property="nombre" />
		</p><br><p>
		<label for="segundoNombre"><bean:message key="sirius.usuario.segundoNombre.label" />&nbsp;:</label>
		<html:text property="segundoNombre" />
		</p><br><p>
		<label for="idTipoDocumento"><bean:message key="sirius.usuario.tipoDocumento.label" />(*)&nbsp;:</label>
		<html:select property="idTipoDocumento">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioForm" property="tiposDocumento" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="numeroDocumento"><bean:message key="sirius.usuario.numeroDocumento.label" />(*)&nbsp;:</label>
		<html:text property="numeroDocumento" />
		</p><br><p>
		<label for="cuit"><bean:message key="sirius.usuario.cuit.label" />&nbsp;:</label>
		<html:text property="cuit" />
		</p><br><p>
		<label for="cbu"><bean:message key="sirius.usuario.cbu.label" />&nbsp;:</label>
		<html:text property="cbu" />
		</p><br><p>
		<label for="nacionalidad"><bean:message key="sirius.usuario.nacionalidad.label" />&nbsp;:</label>
		<html:text property="nacionalidad" />
		</p><br><p>
		<label for="fechaNacimiento"><bean:message key="sirius.usuario.fechaNacimiento.label" />&nbsp;:</label>
		<html:text property="fechaNacimiento" styleClass="datepicker"/>
		</p><br><p>
		<label for="profesion"><bean:message key="sirius.usuario.profesion.label" />&nbsp;:</label>
		<html:text property="profesion" />
		</p><br><p>
		<label for="direccion"><bean:message key="sirius.usuario.direccion.label" />&nbsp;:</label>
		<html:text property="direccion" />
		</p><br><p>
		<label for="telefono"><bean:message key="sirius.usuario.telefono.label" />&nbsp;:</label>
		<html:text property="telefono" />
		</p><br><p>
		<label for="celular"><bean:message key="sirius.usuario.celular.label" />&nbsp;:</label>
		<html:text property="celular" />
		</p><br><p>
		<label for="idRelacionContractual"><bean:message key="sirius.usuario.relacionContractual.label" />(*)&nbsp;:</label>
		<html:select property="idRelacionContractual">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioForm" property="relacionesContractuales" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="email"><bean:message key="sirius.usuario.email.label" />&nbsp;:</label>
		<html:text property="email" />
		</p><br><p>
		<label for="funcion"><bean:message key="sirius.usuario.funcion.label" />&nbsp;:</label>
		<html:text property="funcion" />
		</p><br><p>
		<label for="observaciones"><bean:message key="sirius.usuario.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		</p><br><p>
		<label for="idFormaPago"><bean:message key="sirius.usuario.formaPago.label" />(*)&nbsp;:</label>
		<html:select property="idFormaPago">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioForm" property="formasPago" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idPersonaFactura"><bean:message key="sirius.usuario.personaFactura.label" />(*)&nbsp;:</label>
		<html:select property="idPersonaFactura">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioForm" property="personasFactura" label="nombreYApellido" value="id"/>
		</html:select>
		</p><br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="usuarioForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/usuario/usuario-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
