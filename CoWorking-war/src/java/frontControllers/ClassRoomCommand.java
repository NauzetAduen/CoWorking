/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import ejbs.LogBean;
import ejbs.StatsBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 *
 * @author nauzetaduen
 */
public class ClassRoomCommand extends FrontCommand{

    @Override
    public void process() {
        
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("classRoomCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("ClassroomCommand", request.getSession().getAttribute("username")));
            request.getRequestDispatcher("classroom.jsp").forward(request,response);
        } catch (ServletException | IOException | NamingException ex) {
        }
    }
    
}
