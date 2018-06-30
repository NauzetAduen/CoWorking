package frontControllers;

import ejbs.CartBean;
import ejbs.FinancialBean;
import ejbs.LogBean;
import ejbs.StatsBean;
import java.io.IOException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import model.Reservation;

public class FinancialCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("FinancialCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("FinancialCommand", request.getSession().getAttribute("username")));
            HttpSession session = request.getSession();
            CartBean cart = (CartBean)session.getAttribute("cart");
            FinancialBean fb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/FinancialBean!ejbs.FinancialBean");
            fb.setUser(session.getAttribute("username").toString());
            Reservation r = cart.getReservationById(Integer.valueOf(request.getParameter("id")));
            fb.addTransaction(r);
            cart.remove(r);
            request.getRequestDispatcher("cartlist.jsp").forward(request, response);
            
        } catch (NamingException | ServletException | IOException ex) {
        }

        
        
        
    }
    
}
