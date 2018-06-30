/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import ejbs.LogBean;
import ejbs.StatsBean;
import entities.Users;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import repository.UsersFacade;

/**
 *
 * @author nauzetaduen
 */
public class NewUserCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("newUserCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("NewUserCommand", request.getSession().getAttribute("username")));
            
            
            UsersFacade wpf = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/UsersFacade!repository.UsersFacade");

//            Users user = new Users();
//            user.setIdusers(Integer.parseInt(request.getParameter("userID")));
//            user.setUsername(request.getParameter("username"));
//            user.setPassword(request.getParameter("password"));
//            user.setName(request.getParameter("name"));
//            user.setRole(Integer.parseInt(request.getParameter("role")));
//            wpf.create(user);
            
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            String query = "insert into APP.USERS (IDUSERS, USERNAME, PASSWORD, \"NAME\", \"ROLE\") VALUES (?, ?, ?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(request.getParameter("userID")));
            statement.setString(2, request.getParameter("username"));
            statement.setString(3, request.getParameter("password"));
            statement.setString(4, request.getParameter("name"));
            statement.setInt(5, Integer.valueOf(request.getParameter("role")));
            statement.execute();
            
                request.getRequestDispatcher("users.jsp").forward(request,response);
        } catch (ServletException | IOException | NamingException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(NewUserCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
