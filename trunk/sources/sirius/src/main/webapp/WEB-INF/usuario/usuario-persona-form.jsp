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
<html:form action="/usuario/usuario-persona-form-validate.do?method=saveUsuarioPersona" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
	<p>
		<label for="username"><bean:message key="sirius.usuario.username.label" />(*)&nbsp;:</label>
		<logic:equal name="usuarioPersonaForm" property="action.descripcion" value="create">
			<html:text property="username" />
		</logic:equal>
		<logic:equal name="usuarioPersonaForm" property="action.descripcion" value="update">
			<html:text property="username" readonly="true"/>
		</logic:equal>
		</p><br>
		<logic:equal name="usuarioPersonaForm" property="action.descripcion" value="create">
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
			<html:optionsCollection name="usuarioPersonaForm" property="perfiles" label="nombre" value="id"/>
		</html:select>
		</p><br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="usuarioPersonaForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/persona/persona-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
