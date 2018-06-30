/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import entities.Users;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author nauzetaduen
 */
public class EditUserCommand extends FrontCommand{

    @Override
    public void process() {
        
        try {
            System.out.println("Entramos en Edit User Command");
            Users userToEdit= new Users();
            userToEdit.setIdusers(Integer.parseInt(request.getParameter("userID")));
            userToEdit.setUsername(request.getParameter("username"));
            userToEdit.setPassword(request.getParameter("password"));
            userToEdit.setName(request.getParameter("name"));
            userToEdit.setRole(Integer.parseInt(request.getParameter("role")));
            
            
            request.setAttribute("userToEdit", userToEdit);
            
            
            System.out.println("Salimos en Edit User Command");
            request.getRequestDispatcher("user.jsp").forward(request,response);
        } catch (ServletException | IOException ex) {
        }
    }
    
}
