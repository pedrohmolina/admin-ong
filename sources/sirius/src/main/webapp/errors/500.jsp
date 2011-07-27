<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ page import="java.io.PrintWriter"%>
<div class="form">
	<logic:empty name="org.apache.struts.action.ERROR">
		<h2 class="atencion">Ha ocurrido un problema interno. Generalmente puede seguir utilizando la aplicación, pero ante la imposibilidad de hacerlo comuníquese con el administrador del sistema.</h2>
		</logic:empty>
	<logic:present name="org.apache.struts.action.ERROR">
		<h1 class="atencion">Información del problema</h1>
		<html:errors/>
		<h2 class="info">Haga click <a href="<c:url value="/base/home_init.do"/>">aqui</a> para continuar trabajando.</h2>
	</logic:present>
	<logic:empty name="org.apache.struts.action.ERROR">
		<h2 class="info">Haga click <a href="<c:url value="/base/home_init.do"/>">aqui</a> para continuar trabajando.</h2>
	</logic:empty>
	<logic:empty name="org.apache.struts.action.ERROR">
		<h1 onclick="hideShow('masInfo')">Más Información</h1>
		<div id="masInfo" style="display: none; float:left;">
			<p>
			<label>URL:</label><input disabled="disabled" type="text" value="<c:out value="${requestScope['javax.servlet.error.request_uri']}"/>" style="width: 400px"/><br/>
			</p><br /><p>
			<label>Mensaje:</label><input disabled="disabled" type="text" value="<c:out value="${requestScope['javax.servlet.error.message']}"/>" style="width: 400px"/><br/>
			</p><br /><p>
			<label>Excepcion:</label><textarea readonly="readonly" rows="5" cols="500" style="width: 400px">
			<c:out value="${requestScope['javax.servlet.error.exception']}"/>
			</textarea>
			</p><br /><p>
			<label>Traza:</label><textarea readonly="readonly" rows="15" cols="500" style="width: 400px">
			<%
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			java.io.PrintWriter pw = new PrintWriter(bos);
			((Exception)request.getAttribute("javax.servlet.error.exception")).printStackTrace(pw);
			pw.flush();
			String traza = bos.toString();
			%>
			<%= traza %>
			</textarea>
			</p><br />
		</div>
	</logic:empty>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</div>
	