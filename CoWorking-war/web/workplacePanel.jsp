<%@page import="entities.Workplaces"%>
<%@page import="ejbs.CartBean"%>
<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="ejbs.LogBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.StatsBean"%>
<%@page import="ejbs.StatsBean"%>
<%@page import="model.UserSession"%>
<%@page import="java.util.Random"%>
<%@page import="Services.TypeValidatorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
SecurityBean security = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SecurityBean!ejbs.SecurityBean");
security.setUser(session.getAttribute("username").toString());
security.checkAdmin(session, response);
StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
sb.setUser(session.getAttribute("username").toString());
sb.addPage("workplacePanel.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("workplacePanel.jsp", request.getSession().getAttribute("username")));
CartBean cart = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/CartBean!ejbs.CartBean");
Workplaces w = (Workplaces) request.getAttribute("workplace");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoWorkingNow</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        <jsp:include page="includes/header.jsp" />
        <jsp:include page="includes/adminPanel.jsp" />
        <div id="workplacesPanel" >
            <h2>Update Form</h2>
            <form class="formN" method="post" action="FrontController" id="updateWorkplaceForm">
                <input type="hidden" name="command" value="EditWorkplaceFormCommand" >
                <input type="hidden" name="workplaceId" value="<%=w.getIdworkplace()%>">
                Nombre:<input type="text" name="workplaceName" value="<%=w.getName()%>" required="" placeholder="<%=w.getName()%>">
                Address:<input type="text" name="workplaceAddress" value="<%=w.getAddress()%>" placeholder="<%=w.getAddress()%>">
                Slots: <input type="number" name="workplaceSlots" value="<%=w.getSlots()%>" placeholder="<%=w.getSlots()%>">
                Description: <textarea  cols="100" rows="5" name="workplaceDescription" cols="30" rows="5"><%=w.getDescription()%></textarea>
                    <input type="submit" value="Update!">
            </form>
        </div>
         <jsp:include page="includes/footer.jsp" />
    </body>
</html>
