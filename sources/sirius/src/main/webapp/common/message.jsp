<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<div class="form">
	<h2 class="info">
		<html:messages id="msg" message="true">
			<bean:write filter="false" name="msg"/><br>
		</html:messages>
		<logic:notPresent name="org.apache.struts.action.ACTION_MESSAGE" scope="request">
			<bean:message key="antares.base.defaultMessage.label" />
		</logic:notPresent>
	</h2>
	<c:remove var="org.apache.struts.action.ACTION_MESSAGE" scope="request"/>
	
	<div style="float: left; width: 100%;">
		<div class="boton">
			<logic:notPresent name="backUrl" scope="request">
				<a href="<c:url value="/base/home_init.do"/>"><bean:message key="antares.base.aceptar.label" /></a>
			</logic:notPresent>
			<logic:present name="backUrl" scope="request">
				<a href="<c:url value="${requestScope['backUrl']}"/>"><bean:message key="antares.base.aceptar.label" /></a>
			</logic:present>
		</div>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</div>

