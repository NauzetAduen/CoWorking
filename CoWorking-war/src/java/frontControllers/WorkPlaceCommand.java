package frontControllers;

import ejbs.LogBean;
import ejbs.StatsBean;
import ejbs.WorkPlaceBean;
import java.io.IOException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class WorkPlaceCommand extends FrontCommand{

    @Override
    public void process() {
        try {
        /*
        Dependiendo de qué workplace se haya elegido
        Vamos a una página con la información de dicho workplace
        Dicha info, se saca de bases de datos con un statelessbean
        :D
        */
        StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("WorkPlaceCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
        lb.add(Arrays.asList("WorkPlaceCommand", request.getSession().getAttribute("username")));
            WorkPlaceBean wpb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkPlaceBean!ejbs.WorkPlaceBean");
            String workplace = request.getParameter("workplace");
            String description = wpb.getDescription(workplace);
            request.setAttribute("spots", wpb.getSpots(workplace));
            request.setAttribute("workplace", workplace);
            request.setAttribute("description", description);
            request.getRequestDispatcher("workplaceIndividual.jsp").forward(request, response);
        } catch (ServletException | IOException | NamingException ex) {
        }
    }
    
}
