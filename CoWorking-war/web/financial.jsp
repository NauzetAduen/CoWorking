<%@page import="entities.Reservations"%>
<%@page import="java.util.List"%>
<%@page import="repository.ReservationsFacade"%>
<%@page import="model.Reservation"%>
<%@page import="ejbs.FinancialBean"%>
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
sb.addPage("financial.jsp");
LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
lb.setUser(session.getAttribute("username").toString());
lb.add(Arrays.asList("financial.jsp", request.getSession().getAttribute("username")));
FinancialBean fb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/FinancialBean!ejbs.FinancialBean");
fb.setUser(session.getAttribute("username").toString());

ReservationsFacade rf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/ReservationsFacade!repository.ReservationsFacade");
    int index;
    int max = rf.count();
    if(request.getParameter("id") == null){
       index = 0; 
    }else{
        index = Integer.parseInt(request.getParameter("id"));
    }
     List<Reservations> reservations = rf.findRange(new int[]{index,index+10});
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Financial Status</title>
        <link rel="stylesheet" type="text/css" href="main.css"> 
    </head>
    <body>
        
    <jsp:include page="includes/header.jsp" />
    <jsp:include page="includes/adminPanel.jsp" />
    <div id="workplacesPanel" >
    <h1>Financial Status</h1>
    <h2>Totals</h2>
    <table>
            <tr>
                <th>Total Profit</th>
                <th>Total Reservations</th>
                <th>Avegare</th>
            </tr>
            <tr>
                <td><%=rf.getTotal()%> €</td>
                <td><%=rf.count()%></td>
                <td><%=rf.getTotal()/rf.count()%></td>
            </tr>
    </table>
            <h2>Reservations</h2>
    <table>
            <tr>
                <td>id</td>
                <td>Workplace</td>
                <td>User</td>
                <td>Timestamp</td>
                <td>Price</td>
            </tr>
        <%
            for(Reservations r : reservations){%>
            <tr>
                <td><%=r.getIdreservation()%></td>
                <td><%=r.getIdworkplace()%></td>
                <td><%=r.getIduser()%></td>
                <td><%=r.getTimestamp()%></td>
                <td><%=r.getPrice()%></td>
            <tr>
            <%}%>
    </table>
    <div id="desplazador">
        <form aciton="">
            <input type="hidden" value="0" name="id">
            <input type="submit" value="<<">
        </form>
        <form aciton="">
            <input type="hidden" value="<%=minusTen(index)%>" name="id">
            <input type="submit" value="<">
        </form>
        <form aciton="">
            <input type="hidden" value="<%=plusTen(index,max)%>" name="id">
            <input type="submit" value=">">
        </form>
        <form aciton="">
            <input type="hidden" value="<%=rf.count()-10%>" name="id">
            <input type="submit" value=">>">
        </form>
    </div>
                
    </div> 
    <div style="padding-bottom:25px;">
    </div>
    <jsp:include page="includes/footer.jsp" />
</body>
</html>

<%!
    public static int minusTen(int index){
        int newIndex = index -10;
        if(newIndex<0) return 0;
        return newIndex;
    }
    public static int plusTen(int index, int max){
        int newIndex = index +10;
        if(newIndex>(max-10)) return (max-10);
        return newIndex;

    }
%>