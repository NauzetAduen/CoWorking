<%@page import="model.Reservation"%>
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
    sb.addPage("cartlist.jsp");
    LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
    lb.setUser(session.getAttribute("username").toString());
    lb.add(Arrays.asList("cartlist.jsp", request.getSession().getAttribute("username")));
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
        <h1>Carrito de reservas</h1>
        <table>
            <tr>
                <td>id</td>
                <td>date</td>
                <td>Time</td>
                <td>Workplace</td>
                <td>Cash</td>
            </tr>
        <%
            CartBean cart = (CartBean)session.getAttribute("cart");
            for(Reservation r : cart.getCart()){%>
            <tr>
                <td><%=r.getReservationID()%></td>
                <td><%=r.getDate()%></td>
                <td><%=r.getTime()%></td>
                <td><%=r.getWorkplace()%></td>
                <td><%=r.getCash()%></td>
                <td>
                    <form class="confirmationForm" method="post" action=FrontController>
                        <input type="hidden" name="command" value="FinancialCommand">
                        <input type="hidden" name="id" value="<%=r.getReservationID()%>">
                        <input type="submit" value="Confirmar!">
                    </form>
                </td>
             </tr>
            <%}%>
        </table>
        <jsp:include page="includes/footer.jsp" />
    </body>
</html>
