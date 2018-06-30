package frontControllers;

import ejbs.LogBean;
import ejbs.StatsBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class UserCommand extends FrontCommand{

    @Override
    public void process() {
        
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("userCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("UserCommand", request.getSession().getAttribute("username")));
            request.getRequestDispatcher("user.jsp").forward(request,response);
        } catch (ServletException | IOException | NamingException ex) {}
    }
    
}
