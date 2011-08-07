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

	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.perfil.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="20" property="nombre"  styleClass="veinte" ></html:text>
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.perfil.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" />
		</p><br>
		<h2><bean:message key="sirius.perfil.roles.label" />&nbsp;:</h2>
		
		<br />
		<br />
		<logic:iterate id="rol" name="perfilForm" property="roles">
			<p>
			<html:multibox property="idRoles" style="width:20px">
				<bean:write name="rol" property="id"/> 
			</html:multibox> 
			<label><bean:write name="rol" property="nombre"/></label>
			</p> 
			<br /> 
		</logic:iterate>
		<html:hidden property="idRoles" value="0" />
		<br>
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="perfilForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/perfil/perfil-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
