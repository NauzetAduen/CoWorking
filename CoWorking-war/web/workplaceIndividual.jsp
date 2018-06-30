<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="ejbs.LogBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.StatsBean"%>
<%@page import="model.UserSession"%>
<%@page import="Services.TypeValidatorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
    SecurityBean security = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SecurityBean!ejbs.SecurityBean");
    security.setUser(session.getAttribute("username").toString());
    security.checkNormal(session, response);
    StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
    sb.setUser(session.getAttribute("username").toString());
    sb.addPage("workplace.jsp");
    LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
    lb.setUser(session.getAttribute("username").toString());
    lb.add(Arrays.asList("workplace.jsp", request.getSession().getAttribute("username")));
    String workplace = request.getAttribute("workplace").toString();
    String description = request.getAttribute("description").toString();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido a CoWorkNau</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        <jsp:include page="includes/header.jsp" />
        <div id="workplacesPanel">
            <p id="mensaje">Tenemos los mejores espacios para CoWorking del mundo</p>
            <div id="imgSide">
                <img src="resources/workplace-${workplace}.jpg" />
            </div>
            <div id="infoSide">
                <h2>${workplace}</h2>

                <p>El número de plazas disponibles es ${spots}</p>
                <h3>Descripción</h3>
                <p id="description">${description}</p>
                <form id="reservation" method="post" action="FrontController">
                    <input type="date" name="date">
                    <input type="time" name="time">
                    <input type="hidden" name="command" value="ReservationCommand">
                    <input type="hidden" name="workplace" value="${workplace}">
                    <input type="Submit" value="Reservar!">
                </form>
            </div>
            

        </div>
            
        <jsp:include page="includes/footer.jsp" />
    </div>
</body>
</html>
