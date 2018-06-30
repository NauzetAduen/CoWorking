<%@page import="entities.Workplaces"%>
<%@page import="java.util.List"%>
<%@page import="repository.WorkplacesFacade"%>
<%@page import="ejbs.WorkPlaceBean"%>
<%@page import="java.util.Arrays"%>
<%@page import="ejbs.SecurityBean"%>
<%@page import="ejbs.LogBean"%>
<%@page import="javax.naming.InitialContext"%>
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
sb.addPage("workplacesPanel.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("workplacesPanel.jsp", request.getSession().getAttribute("username")));
WorkPlaceBean wpb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkPlaceBean!ejbs.WorkPlaceBean");
wpb.setUser(session.getAttribute("username").toString());

WorkplacesFacade wpf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkplacesFacade!repository.WorkplacesFacade");
List<Workplaces> workplaces = wpf.findAll();
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
            
        </div>
        <div id="classroomsPanel">
            <h1> Workplaces </h1>
        <div class="workplace">
            <table>
                <tr>
                    <td>id</td>
                    <td>Workplace</td>
                    <td>Address</td>
                    <td>Spots</td>
                    <td>Description</td>
                    <td colspan="2">Options</td>
                </tr>
                
                <%
                for(Workplaces w : workplaces){%>
                <tr>
                    <td><%=w.getIdworkplace()%></td>
                    <td><%=w.getName()%></td>
                    <td><%=w.getAddress()%></td>
                    <td><%=w.getSlots()%></td>
                    <td><%=w.getDescription()%></td>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="EditWorkplaceCommand">
                            <input type="hidden" name="workplaceId" value="<%=w.getIdworkplace()%>">
                            <input type="hidden" name="workplaceName" value="<%=w.getName()%>">
                            <input type="hidden" name="workplaceAddress" value="<%=w.getAddress()%>">
                            <input type="hidden" name="workplaceSlots" value="<%=w.getSlots()%>">
                            <input type="hidden" name="workplaceDescription" value="<%=w.getDescription()%>">
                            <button class="buttonimg" type="submit" name="Editar">
                                <img src="resources/edit.png"  alt="editar" />
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="DeleteWorkplaceCommand">
                            <input type="hidden" name="workplaceId" value="<%=w.getIdworkplace()%>">
                            <button class="buttonimg" type="submit" name="Delete">
                                <img src="resources/delete.png"  alt="borrar" />
                            </button>
                        </form>
                    </td>
                </tr>
                
                <%}%>
            </table>
                    
        </div>
            <form id="newWorkplaceForm" action=FrontController method='post'>
                    New Workplace
                    <input type="hidden" name="command" value="NewWorkplaceCommand">
                    <input type="text" placeholder="Name" name="workplaceName">
                    <input type="text" placeholder="Address" name="workplaceAddress">
                    <input type="number" placeholder="Slots" name="workplaceSlots">
                    <textarea  name="workplaceDescription" placeholder="Description"></textarea>
                    <input type="submit" value="Add">
                </form>
        <jsp:include page="includes/footer.jsp" />
    </body>
</html>