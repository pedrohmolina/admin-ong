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

<script>

	function submitenter(myfield,e)	{
		var keycode;
		if (window.event) {
			keycode = window.event.keyCode;
		} else if (e) {
			keycode = e.which;
		} else {
			return true;
		}
		
		if (keycode == 13) {
		   myfield.form.submit();
		   return false;
		} else {
		   return true;
		}
	}
	
</script>

<div class="form">
<form action="<c:url value="/j_security_check_acegi"/>">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="j_username"><bean:message key="sirius.usuario.username.label"/>:</label><input type="text" name="j_username" id="j_username"  ><br/><br/>
		<label for="j_password"><bean:message key="sirius.usuario.password.label"/>:</label><input type="password" onKeyPress="return submitenter(this,event)"  name="j_password" id="j_password"  maxlength="12">
		<br><br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
	<logic:present parameter="error">
		<h3 style="text-align: center; font-size: small;"><bean:message key="security.login.loginproblem"/></h3>
		<h3 style="text-align: center;">
		<c:choose>
			<c:when test="${sessionScope['ACEGI_SECURITY_LAST_EXCEPTION'].class.name == 'org.acegisecurity.BadCredentialsException'}">
				<bean:message key="security.login.badCredentials"/>
			</c:when>
			<c:when test="${sessionScope['ACEGI_SECURITY_LAST_EXCEPTION'].class.name == 'org.acegisecurity.userdetails.UsernameNotFoundException'}">
				<bean:message key="security.login.userNotFound"/>
			</c:when>
			<c:when test="${sessionScope['ACEGI_SECURITY_LAST_EXCEPTION'].class.name == 'org.acegisecurity.LockedException'}">
				<bean:message key="security.login.userLocked"/><br/>
				<c:out value="${sessionScope['ACEGI_SECURITY_LAST_EXCEPTION'].message}"/>		
			</c:when>
			<c:otherwise>
				<bean:message key="security.login.errorUnknown"/>
			</c:otherwise>
		</c:choose>
		</h3>
		<%-- if (true) throw new RuntimeException(); --%>
	</logic:present>
	</div>
	<div class="boton">
		<a href="#" onclick="document.forms[0].submit()"><bean:message key="antares.base.aceptar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</form>

</div>

<script type="text/javascript">
</script>
