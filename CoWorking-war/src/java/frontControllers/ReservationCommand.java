/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import Strategies.ZoneStrategyBean;
import ejbs.CartBean;
import ejbs.LogBean;
import ejbs.StatsBean;
import java.io.IOException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import model.Reservation;

/**
 *
 * @author nauzetaduen
 */
public class ReservationCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("reservationCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("reservationCommand", request.getSession().getAttribute("username")));
            HttpSession session = request.getSession();
            CartBean cart = (CartBean)session.getAttribute("cart");
            Reservation reservation = new Reservation(request.getSession().getAttribute("username").toString(),
                    request.getParameter("date"),
                    request.getParameter("time"),
                    request.getParameter("workplace"),
                    new ZoneStrategyBean());
            cart.add(reservation);
            
            request.getRequestDispatcher("workplaces.jsp").forward(request, response);
        } catch (NamingException | ServletException | IOException ex) {
        }
    }
    
    
}
