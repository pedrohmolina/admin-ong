<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%
	response.setHeader("Cache-control", "private, no-cache, no-store, must-revalidate, max-age=0");
	response.setHeader("pragma","no-cache");
	response.setHeader("Expires","-1");
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=<%= System.getProperty("file.encoding") %>" />
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<meta http-equiv="Cache" content="no-cache"/>
<meta http-equiv="Expires" content="thu, 01 Jan 1998 12:00:00 GMT"/>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<title>
		<c:if test="${empty requestScope['title']}">
			<c:set var="titulo">
				<tiles:getAsString name="title"/>
			</c:set>
		</c:if>
		<c:if test="${not empty requestScope['title']}">
			<c:set var="titulo" value="${requestScope['title']}" />
		</c:if>
		<c:if test="${not empty titulo}" >
			<bean-el:message key="${titulo}"/>
		</c:if>
</title>
<%-- <link href="<c:url value="/css/estilosDIV.css"/>" type="text/css" rel="stylesheet"></link> --%>
<%-- <link href="<c:url value="/css/estilos.css"/>" type="text/css" rel="stylesheet"></link> --%>
<%-- <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"></link> --%>
<%-- <link href="<c:url value="/css/calendar-win2k-1.css"/>" type="text/css" rel="stylesheet"></link> --%>
<%-- <link href="<c:url value="/css/thickbox.css"/>" type="text/css" rel="stylesheet"></link> --%>
<%-- <link href="<c:url value="/crud/css/customCrud.css"/>" type="text/css" rel="stylesheet"></link> --%>
<tiles:insert attribute="js-header"/>
</head>
<body onload="return winOnLoad()">
	
	<div id=contenedor style="text-align: left;">
		<div class="textoInterior3" style="width: 98%;">
			<div id="main-form" class="form" style="height: 95%;">
				<tiles:insert attribute="body"/>
			</div>
		</div>
	</div>
		
	<div style="display: none;">
	<tiles:insert attribute="messages"/>
	</div>
</body>
</html>