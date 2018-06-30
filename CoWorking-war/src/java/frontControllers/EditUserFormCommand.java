/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import entities.Users;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class EditUserFormCommand extends FrontCommand{

    @Override
    public void process() {

        try {
//            UsersFacade wpf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/UsersFacade!repository.UsersFacade");
//            int id = Integer.parseInt(request.getParameter("userID"));
//            Users userToUpdate = wpf.find(id);
//            userToUpdate.setUsername(request.getParameter("username"));
//            userToUpdate.setPassword(request.getParameter("password"));
//            userToUpdate.setName(request.getParameter("name"));
//            userToUpdate.setRole(Integer.parseInt(request.getParameter("role")));
//            wpf.edit(userToUpdate);
            
            
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            String query = "UPDATE APP.USERS SET IDUSERS = ?, USERNAME = ?, PASSWORD = ?, \"NAME\" = ?, \"ROLE\" =? WHERE IDUSERS = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(request.getParameter("userID")));
            statement.setString(2, request.getParameter("username"));
            statement.setString(3, request.getParameter("password"));
            statement.setString(4, request.getParameter("name"));
            statement.setInt(5, Integer.valueOf(request.getParameter("role")));
            statement.setInt(6, Integer.valueOf(request.getParameter("userID")));
            System.out.println(query);
            statement.executeUpdate();
            
            request.getRequestDispatcher("users.jsp").forward(request,response);
        } catch (ServletException | IOException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(EditUserFormCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
