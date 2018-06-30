<%@page import="entities.Users"%>
<%@page import="java.util.List"%>
<%@page import="repository.UsersFacade"%>
<%@page import="java.util.Map"%>
<%@page import="ejbs.UsersBean"%>
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
    security.checkAdmin(session, response);
    StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
    sb.setUser(session.getAttribute("username").toString());
    sb.addPage("users.jsp");
    LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
    lb.setUser(session.getAttribute("username").toString());
    lb.add(Arrays.asList("users.jsp", request.getSession().getAttribute("username")));
    UsersBean ub = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/UsersBean!ejbs.UsersBean");

    UsersFacade rf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/UsersFacade!repository.UsersFacade");

    List<Users> users = rf.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPanel Admin - USERS</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        <jsp:include page="includes/header.jsp" />
        <jsp:include page="includes/adminPanel.jsp" />

        <div id="usersPanel">
            <h1> Users</h1>
            <h2>Search users by name</h2>
            <form class="formN" action=FrontController method="post">
                <input type="hidden" name="command" value="searchUserCommand">
                <input type="text" name="valueSearched" placeholder="name" required="">
                <input type="submit" value="Search">
            </form>
            
            
            <h2> List of users </h2>
            <table>
                <tr><th>ID</th><th>Username</th><th>Password</th><th>Name</th><th>Role</th><td colspan="2">Options</td></tr>
                        <%
                for (Users u : users) {%>
                <tr>
                    <td><%=u.getIdusers()%></td>
                    <td><%=u.getUsername()%></td>
                    <td><%=u.getPassword()%></td>
                    <td><%=u.getName()%></td>
                    <td><%=u.getRole()%></td>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="EditUserCommand">
                            <input type="hidden" name="userID" value="<%=u.getIdusers()%>">
                            <input type="hidden" name="username" value="<%=u.getUsername()%>">
                            <input type="hidden" name="password" value="<%=u.getPassword()%>">
                            <input type="hidden" name="name" value="<%=u.getName()%>">
                            <input type="hidden" name="role" value="<%=u.getRole()%>">
                            <button class="buttonimg" type="submit" name="Editar">
                                <img src="resources/edit.png"  alt="editar" />
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="DeleteUserCommand">
                            <input type="hidden" name="userID" value="<%=u.getIdusers()%>">
                            <button class="buttonimg" type="submit" name="Delete">
                                <img src="resources/delete.png"  alt="borrar" />
                            </button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <h2>New User</h2>
            <form class="formN" action=FrontController method='post'>
                <input type="hidden" name="command" value="NewUserCommand">
                <input type="number" placeholder="ID" name="userID">
                <input type="text" placeholder="Username" name="username">
                <input type="text" placeholder="Password" name="password">
                <input type="text" placeholder="Name" name="name">
                <input type="number" placeholder="Role" name="role">
                <input type="submit" value="Add">
            </form>
            
            
        </div>
        <jsp:include page="includes/footer.jsp" />   
    </body>
</html>

