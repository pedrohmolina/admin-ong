<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<h3 class="info">
	No posee los permisos necesarios para realizar esta acción
</h3>
<c:remove var="org.apache.struts.action.ACTION_MESSAGE" scope="session"/>

<h3 class="info"><a href="<c:url value="/base/home_init.do"/>">Aceptar</a></h3>

