<%@page import="ejbs.CartBean"%>
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
    sb.addPage("workplaces.jsp");
    LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
    lb.setUser(session.getAttribute("username").toString());
    lb.add(Arrays.asList("workplaces.jsp", request.getSession().getAttribute("username")));
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
            <div class="workplace">
                <h2>Arteixo</h2>
                <form  method="post" action="FrontController">
                <input type="hidden" name="command" value="WorkPlaceCommand">
                <input type="hidden" name="workplace" value="Arteixo">
                <button class="buttonworkplaces" type="submit">
                    <img src="resources/workplace-Arteixo.jpg" />
                </button>
            </form>
            </div>
            <div class="workplace">
                <h2>Mali</h2>
                <form  method="post" action="FrontController">
                <input type="hidden" name="command" value="WorkPlaceCommand">
                <input type="hidden" name="workplace" value="Mali">
                <button class="buttonworkplaces" type="submit">
                    <img src="resources/workplace-Mali.jpg" />
                </button>
            </form>
            </div>
            <div class="workplace">
                <h2>Journey</h2>
                <form  method="post" action="FrontController">
                <input type="hidden" name="command" value="WorkPlaceCommand">
                <input type="hidden" name="workplace" value="Journey">
                <button class="buttonworkplaces" type="submit">
                    <img src="resources/workplace-Journey.jpg" />
                </button>
            </form>
            </div>


            <jsp:include page="includes/footer.jsp" />
        </div>
    </body>
</html>
