<%@page import="java.util.Arrays"%>
<%@page import="ejbs.LogBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.StatsBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");

sb.addPage("main.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.add(Arrays.asList("main.jsp", ""));

%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor Aulas ULPGC</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        <div id="loginDiv">
            <img id="logoImg" src="resources/CoWork.jpg" />
            <form id="loginForm" method="post" action=FrontController>
                <input type="hidden" name="command" value="LoginCommand">
                <input type="text" placeholder="User" autofocus="" name="user">
                <input type="password" placeholder="Password" name="password">
                <input type="submit" value="Login">
            </form>
        </div>
    </body>
</html>
