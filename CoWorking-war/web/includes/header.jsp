<%@page import="ejbs.CartBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="model.UserSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@page import="ejbs.SessionBean"%>
<%String type,message, username = "";%>
<%String url="";%>
<%
    CartBean cart = (CartBean)session.getAttribute("cart");
    SessionBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SessionBean!ejbs.SessionBean");
    sb = ((SessionBean) session.getAttribute("userData"));
    if(cart.size() == 0){
        type="empty";
        message="Empty Cart";
    }else{
        type="notempty";
        message="Your Cart ("+ cart.size()+")";
    }
    if (sb != null) {
        username = sb.getName();
    }
    if(sb.isAdmin()){
        url="admin.jsp";
    }else{
        url="workplaces.jsp";
    }

%>


<header>
    <a href="<%=url%>"><img id="logoSmall" src="resources/CoWorkSmall.jpg"/></a>
    <div id="headerRight">
        <div id="options" >
            Hola <%= username%>!
            <%
            if (!sb.isAdmin()) {%>
            <form id="cartForm" class="options <%= type%>" action=FrontController>
                <input type="hidden" name="command" value="CartCommand">
                <input type="Submit" value="<%=message%>">
            </form>
            <%}%>
            <form id="logoutForm"  class="options" action=FrontController>
                <input type="hidden" name="command" value="LogOutCommand">
                <input type="Submit" value="Log Out!">
            </form>
        </div>
    </div>

</header>