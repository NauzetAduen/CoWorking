package frontControllers;

import ejbs.LogBean;
import ejbs.SessionBean;
import ejbs.StatsBean;
import java.io.IOException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class LogOutCommand extends FrontCommand{

    @Override
    public void process() {
        
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("logOutCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("LogOutCommand", request.getSession().getAttribute("username")));
            sb.removeUser(request.getSession().getAttribute("username").toString());
            
            request.getSession().invalidate();

            request.getRequestDispatcher("main.jsp").forward(request,response);
        } catch (ServletException | IOException | NamingException ex) {
        }
    }
}
