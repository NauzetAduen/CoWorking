package frontControllers;

import entities.Workplaces;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import repository.WorkplacesFacade;
public class EditWorkplaceFormCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            WorkplacesFacade wpf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkplacesFacade!repository.WorkplacesFacade");
            int id = Integer.parseInt(request.getParameter("workplaceId"));
            Workplaces workplaceToUpdate = wpf.find(id);
            workplaceToUpdate.setName(request.getParameter("workplaceName"));
            workplaceToUpdate.setAddress(request.getParameter("workplaceAddress"));
            workplaceToUpdate.setSlots(Integer.parseInt(request.getParameter("workplaceSlots")));
            workplaceToUpdate.setDescription(request.getParameter("workplaceDescription"));
            wpf.edit(workplaceToUpdate);
            request.getRequestDispatcher("workplacesPanel.jsp").forward(request,response);
        } catch (NamingException | ServletException | IOException ex) {
        }
        
        
        
    }
    
}
