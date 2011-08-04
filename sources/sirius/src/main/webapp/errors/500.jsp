<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>

<p class="atencion">Ha ocurrido un problema interno. Generalmente puede seguir utilizando la aplicación, pero ante la imposibilidad de hacerlo <a href="mailto:<bean:message key="sirius.mailAdministrador" />">comuníquese con el administrador del sistema.</a></p>
<br/>
<p class="info">Haga click <a href="<c:url value="/base/home_init.do"/>">aqui</a> para continuar trabajando.</p>
