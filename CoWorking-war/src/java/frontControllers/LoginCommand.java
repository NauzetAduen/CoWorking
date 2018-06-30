package frontControllers;

import Persistence.DBConnection;
import ejbs.CartBean;
import ejbs.LogBean;
import ejbs.StatsBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class LoginCommand extends FrontCommand {

    @Override
    public void process(){
        
        try {
            DBConnection con = new DBConnection(request);
            if(con.userIsValid()){
                
                StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
                sb.addComponent("loginCommand");
                LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
                lb.add(Arrays.asList("LoginCommand", request.getSession().getAttribute("username")));
                if(con.isAdmin()){
                    request.getRequestDispatcher("admin.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("workplaces.jsp").forward(request,response);
                }
                
            }
            
            request.getRequestDispatcher("main.jsp").forward(request,response);

        } catch (ServletException | IOException | SQLException | NamingException ex ) {}
    }
    
    
}
