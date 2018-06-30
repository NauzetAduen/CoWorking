<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="ejbs.LogBean"%>
<%@page import="java.util.Map"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.StatsBean"%>
<%@page import="model.UserSession"%>
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
sb.addPage("stats.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("stats.jsp", request.getSession().getAttribute("username")));
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
        <div id="generalDiv">
        <h1>General Stats of our app</h1>
        <h2>Total Users</h2>
        <%=sb.getTotalUsers()%>
        <table>
            <tr>
                <th>Users</th>
            </tr>
            <%
            for (String user : sb.getArrayUsers()){%>
            <tr><td> <%= user%> </td></tr>
            <%}%>
        </table>    
        <h2>Pages visited</h2>
        <table>
            <tr>
                <th>Page</th>
                <th>Visits</th>
            </tr>
          <%
        for (Map.Entry<String, Integer> entry : sb.getPageCount().entrySet()) {%>
        <tr> <td><%=entry.getKey()%></td> <td><%=entry.getValue()%></td></tr>
        <%}%> 
        </table>
        <h2>Components visited</h2>
        <table>
            <tr>
                <th>Page</th>
                <th>Visits</th>
            </tr>
          <%
        for (Map.Entry<String, Integer> entry : sb.getComponentCount().entrySet()) {%>
        <tr> <td><%=entry.getKey()%></td> <td><%=entry.getValue()%></td></tr>
        <%}%> 
        </table>
        </div>
        <jsp:include page="includes/footer.jsp" />
</body>
</html>