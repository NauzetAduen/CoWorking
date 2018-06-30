/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import entities.Workplaces;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author nauzetaduen
 */
public class EditWorkplaceCommand extends FrontCommand{

    @Override
    public void process() {
        
        
        try {
            Workplaces w = new Workplaces();
            w.setIdworkplace(Integer.parseInt(request.getParameter("workplaceId")));
            w.setName(request.getParameter("workplaceName"));
            w.setAddress(request.getParameter("workplaceAddress"));
            w.setSlots(Integer.parseInt(request.getParameter("workplaceSlots")));
            w.setDescription(request.getParameter("workplaceDescription"));
            
            request.setAttribute("workplace", w);
            request.getRequestDispatcher("workplacePanel.jsp").forward(request,response);
        } catch (ServletException | IOException ex) {
        }
    }
    
}
