/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import entities.Workplaces;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import repository.WorkplacesFacade;

/**
 *
 * @author nauzetaduen
 */
public class DeleteWorkplaceCommand extends FrontCommand{

    @Override
    public void process() {
        
        try {
            
            WorkplacesFacade wpf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkplacesFacade!repository.WorkplacesFacade");
            
            int id = Integer.parseInt(request.getParameter("workplaceId"));
            Workplaces workplaceToRemove = wpf.find(id);
            wpf.remove(workplaceToRemove);
            
            
            request.getRequestDispatcher("workplacesPanel.jsp").forward(request,response);
        } catch (ServletException | IOException | NamingException ex) {
        }
        
        
    }
    
}
