
package frontControllers;

import ejbs.LogBean;
import ejbs.StatsBean;
import entities.Workplaces;
import java.io.IOException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import repository.WorkplacesFacade;

/**
 *
 * @author nauzetaduen
 */
public class NewWorkplaceCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("newWorkplaceCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("newWorkplaceCommand", request.getSession().getAttribute("username")));
            
            /*
            <input type="text" placeholder="Name" name="workplaceName">
                    <input type="text" placeholder="Address" name="workplaceAddress">
                    <input type="number" placeholder="Slots" name="workplaceSlots">
                    <textarea  name="workplaceDescription">Description..
            
            
            */
            WorkplacesFacade wpf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkplacesFacade!repository.WorkplacesFacade");

            int index = wpf.count() +1;
            Workplaces workplace = new Workplaces();
            workplace.setIdworkplace(index);
            workplace.setAddress(request.getParameter("workplaceAddress"));
            workplace.setDescription(request.getParameter("workplaceDescription"));
            workplace.setName(request.getParameter("workplaceName"));
            workplace.setSlots(Integer.parseInt(request.getParameter("workplaceSlots")));
            
            wpf.create(workplace);
            
            
            request.getRequestDispatcher("workplacesPanel.jsp").forward(request,response);
        } catch (ServletException | IOException | NamingException ex) {
        }
    }
    
}
