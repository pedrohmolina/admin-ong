<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@page import="java.io.PrintWriter"%>
<logic:empty name="org.apache.struts.action.ERROR">
<h3 class="atencion">Ha ocurrido un problema interno. Generalmente puede seguir utilizando la aplicación, pero ante la imposibilidad de hacerlo <a href="mailto:admin@prov.norte.com?subject=blankapp%20Problem&body=TimeStamp%20<%= System.currentTimeMillis() %>">comuníquese con el administrador del sistema.</a></h3>
</logic:empty>
