<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@page import="java.io.PrintWriter"%>

<logic:empty name="org.apache.struts.action.ERROR">
	<p class="atencion">Ha ocurrido un problema interno. Generalmente puede seguir utilizando la aplicación, pero ante la imposibilidad de hacerlo <a href="mailto:admin@prov.norte.com?subject=blankapp%20Problem&body=TimeStamp%20<%= System.currentTimeMillis() %>">comuníquese con el administrador del sistema.</a></p>
	<br/>
</logic:empty>
<logic:present name="org.apache.struts.action.ERROR">
	<h1 class="atencion">Información del problema</h1>
	<html:errors/>
	<h3 class="info">Haga click <a href="<c:url value="/base/home_init.do"/>">aqui</a> para continuar trabajando.</h3>
</logic:present>
<logic:empty name="org.apache.struts.action.ERROR">
	<p class="info">Haga click <a href="<c:url value="/base/home_init.do"/>">aqui</a> para continuar trabajando.</p>
</logic:empty>
<logic:empty name="org.apache.struts.action.ERROR">
	<div class="boton"><a href="#" onclick="hideShow('masInfo')">Más Información</a></div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
	<div id="masInfo" style="display: none;">
		<label>URL:</label><input disabled="disabled" type="text" value="<c:out value="${requestScope['javax.servlet.error.request_uri']}"/>" style="width: 735px"/><br/>
		<label>Mensaje:</label><input disabled="disabled" type="text" value="<c:out value="${requestScope['javax.servlet.error.message']}"/>" style="width: 735px"/><br/>
		<label>Excepcion:</label><textarea readonly="readonly" rows="5" cols="500" style="width: 735px">
		<c:out value="${requestScope['javax.servlet.error.exception']}"/>
		</textarea><br/>
		<label>Traza:</label><textarea readonly="readonly" rows="15" cols="500" style="width: 735px">
		<%
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.PrintWriter pw = new PrintWriter(bos);
		((Exception)request.getAttribute("javax.servlet.error.exception")).printStackTrace(pw);
		pw.flush();
		String traza = bos.toString();
		%>
		<%= traza %>
		</textarea>
	</div>
	
</logic:empty>
