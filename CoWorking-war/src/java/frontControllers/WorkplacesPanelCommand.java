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

public class WorkplacesPanelCommand  extends FrontCommand{

    @Override
    public void process() {
        try {
            //cargaríamos de una BD los workplaces disponibles para poder 
            //editarllos, borrarlos o añadir nuevos
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("WorkPlacePanelCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("WorkPlacePanelCommand", request.getSession().getAttribute("username")));
            
            request.getRequestDispatcher("workplacePanel.jsp").forward(request, response);
            
            
        } catch (NamingException | ServletException | IOException ex) {
        }
        
        
    }
    
}
