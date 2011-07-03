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
<html:form action="/perfil/perfil-query.do?method=query">
	<div style="float:left;">
		<p>
		<label for="firstName"><bean:message key="sirius.perfil.nombre.label" />&nbsp;</label>
		<html:text property="nombre" readonly="true" />
		</p><br><p>
		<label for="telefono"><bean:message key="sirius.perfil.descripcion.label" />&nbsp;</label>
		<html:textarea property="descripcion" rows="5" readonly="true" />
		</p><br><p>
		<label for="idRoles"><bean:message key="sirius.perfil.roles.label" />&nbsp;:</label>
		<br />
		<br />
		<logic:iterate id="rol" name="perfilForm" property="roles">
			<html:multibox property="idRoles" disabled="true">
				<bean:write name="rol" property="id"/> 
			</html:multibox> 
			<bean:write name="rol" property="nombre"/>
			<br />
		</logic:iterate>
		 </p><br>
	</div>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/perfil/perfil-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

