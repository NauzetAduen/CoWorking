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
public class DeleteUserCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            UsersFacade uf =InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/UsersFacade!repository.UsersFacade");
            
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            //String query = "insert into APP.USERS (IDUSERS, USERNAME, PASSWORD, \"NAME\", \"ROLE\") VALUES (?, ?, ?,?,?)";
            String query = "delete from APP.USERS where IDUSERS=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(request.getParameter("userID")));
            statement.executeUpdate();
            
            
//            int id = Integer.parseInt(request.getParameter("userID"));
//            Users userToDelete = uf.find(id);
//            uf.remove(userToDelete);
            
            
            request.getRequestDispatcher("users.jsp").forward(request,response);
        } catch (NamingException | ServletException | IOException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
