<%@page import="ejbs.SessionBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="java.util.Map"%>
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
security.checkAdmin(session, response);
StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
sb.setUser(session.getAttribute("username").toString());
sb.addPage("log.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("log.jsp", request.getSession().getAttribute("username")));
String classAdmin ="";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPanel Admin</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
    <jsp:include page="includes/header.jsp" />
    <jsp:include page="includes/adminPanel.jsp" />
    <div id="generalDiv">
        <h2>Log</h2>
        <table>
            <tr>
                <th>Index</th>
                <th>Step</th>
                <th>User</th>
            </tr>
          <%
        for (Map.Entry<Integer, List<String>> entry : lb.getLog().entrySet()) {
            if(entry.getValue().get(1).equals("admin") || entry.getValue().get(1).equals("admin1")){
                classAdmin ="class=\"admin\"";
            }%>
        <tr> <td><%=entry.getKey()%></td> <td><%=entry.getValue().get(0)%></td><td <%=classAdmin%>><%=entry.getValue().get(1)%></td></tr>
        <%classAdmin="";}
        %> 
        </table>
    </div>
    <jsp:include page="includes/footer.jsp" />
</body>
</html>