<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<h3 class="info">
	<html:messages id="msg" message="true">
		<bean:write filter="false" name="msg"/><br>
	</html:messages>
	<logic:notPresent name="org.apache.struts.action.ACTION_MESSAGE" scope="session">
		La &uacute;ltima operaci&oacute;n se realiz&oacute; con &eacute;xito.
	</logic:notPresent>
</h3>
<c:remove var="org.apache.struts.action.ACTION_MESSAGE" scope="session"/>

<h3 class="info"><a href="<c:url value="${sessionScope['antares.originalPath']}"/>">Aceptar</a></h3>

