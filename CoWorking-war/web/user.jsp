<%@page import="entities.Users"%>
<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="ejbs.LogBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.StatsBean"%>
<%@page import="model.UserSession"%>
<%@page import="java.util.Random"%>
<%@page import="Services.TypeValidatorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<% String userID = request.getParameter("userID"); %>
<%
SecurityBean security = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SecurityBean!ejbs.SecurityBean");
security.setUser(session.getAttribute("username").toString());
security.checkAdmin(session, response);
StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
sb.setUser(session.getAttribute("username").toString());
sb.addPage("user.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("user.jsp", request.getSession().getAttribute("username")));
Users u = (Users) request.getAttribute("userToEdit");
System.out.println("Entramos en user.jsp");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor Aulas ULPGC</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        <jsp:include page="includes/header.jsp" />
        <jsp:include page="includes/adminPanel.jsp" />
        <div id="workplacesPanel" >
            <h2>Update Form</h2>
            <form class="formN" method="post" action="FrontController" id="updateWorkplaceForm">
                <input type="hidden" name="command" value="EditUserFormCommand" >
                <input type="hidden" name="userID" value="<%=u.getIdusers()%>">
                Username: <input type="text" name="username" value="<%=u.getUsername()%>" required="" placeholder="<%=u.getUsername()%>">
                Password: <input type="text" name="password" value="<%=u.getPassword()%>" placeholder="<%=u.getPassword()%>">
                Name: <input type="text" name="name" value="<%=u.getName()%>" placeholder="<%=u.getName()%>">
                Role: <input type="number" name="role" value="<%=u.getRole()%>" placeholder="<%=u.getRole()%>">
                    <input type="submit" value="Update!">
            </form>
        </div>
        
        
        
        
            <jsp:include page="includes/footer.jsp" />
    </body>
</html>