<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
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
<title>
		<c:if test="${empty requestScope['title']}">
			<tiles:getAsString name="title"/>
		</c:if>
		<c:if test="${not empty requestScope['title']}">
			<c:out value="${requestScope['title']}"></c:out>
		</c:if>	
</title>
<tiles:insert attribute="js-header"/>
</head>
<body>
<jsp:include flush="true" page="/common/menu.jsp"></jsp:include>
<tiles:insert attribute="body"/></div>
</body>
</html>