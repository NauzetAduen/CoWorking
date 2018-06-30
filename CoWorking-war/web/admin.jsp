<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="ejbs.LogBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.StatsBean"%>
<%@page import="Services.TypeValidatorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<%
SecurityBean security = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SecurityBean!ejbs.SecurityBean");
security.setUser(session.getAttribute("username").toString());
security.checkAdmin(session, response);
StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
sb.setUser(session.getAttribute("username").toString());
sb.addPage("admin.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("admin.jsp", request.getSession().getAttribute("username")));
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPanel Admin</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        
    <jsp:include page="includes/header.jsp" />
    <jsp:include page="includes/adminPanel.jsp" />
    <jsp:include page="includes/footer.jsp" />
</body>
</html>