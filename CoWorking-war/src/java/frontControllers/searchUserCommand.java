/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import entities.Users;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import repository.UsersFacade;

/**
 *
 * @author nauzetaduen
 */
public class searchUserCommand extends FrontCommand{

    @Override
    public void process() {
        
        try {
            request.setAttribute("value", request.getParameter("valueSearched"));
            request.getRequestDispatcher("search.jsp").forward(request,response);
        } catch (ServletException | IOException ex) {
        }
    }
    
}
