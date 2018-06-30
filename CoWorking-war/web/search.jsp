<%@page import="repository.UsersFacade"%>
<%@page import="entities.Users"%>
<%@page import="java.util.List"%>
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
sb.addPage("search.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("search.jsp", request.getSession().getAttribute("username")));
   
UsersFacade rf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/UsersFacade!repository.UsersFacade");

List<Users> users =  rf.getUsersbyNameAndRole((String)request.getAttribute("value"));
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
    <div id="usersPanel">
            <h1> Found <%=users.size()%> with name '<%=(String)request.getAttribute("value")%>'</h1>
            <table>
                <tr><th>ID</th><th>Username</th><th>Password</th><th>Name</th><th>Role</th></tr>
                        <%
                for (Users u : users) {%>
                <tr>
                    <td><%=u.getIdusers()%></td>
                    <td><%=u.getUsername()%></td>
                    <td><%=u.getPassword()%></td>
                    <td><%=u.getName()%></td>
                    <td><%=u.getRole()%></td>

                </tr>
                <%
                    }
                %>
            </table>
           <p>This result is ordered by role.
    </div>
    
    
    <jsp:include page="includes/footer.jsp" />
</body>
</html>