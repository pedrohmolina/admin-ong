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
<html:form action="/perfil/perfil-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="nombre"><bean:message key="sirius.perfil.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="20" property="nombre"></html:text>
		<br />
		<label for="descripcion"><bean:message key="sirius.perfil.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" />
		<br />
		<label for="idAccesos"><bean:message key="sirius.perfil.roles.label" />&nbsp;:</label>
		<br />
		<br />
		<logic:iterate id="rol" name="perfilForm" property="roles">
			<html:multibox property="idRoles">
				<bean:write name="rol" property="id"/> 
			</html:multibox> 
			<bean:write name="rol" property="nombre"/>
			<br /> 
		</logic:iterate>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="perfilForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/perfil/perfil-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
