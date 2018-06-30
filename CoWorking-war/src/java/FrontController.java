

import ejbs.LogBean;
import ejbs.StatsBean;
import frontControllers.FrontCommand;
import frontControllers.UnknownCommand;
import java.io.IOException;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nauzetaduen
 */
@WebServlet(urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String a = (String) request.getSession().getAttribute("username");
        if (a==null) a="";
        StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
        sb.addComponent("FrontController");
        LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
        lb.add(Arrays.asList("FrontController", a));
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }
    private FrontCommand getCommand(HttpServletRequest request) throws Exception{
        try{
            FrontCommand f = (FrontCommand) getCommandClass(request).newInstance();
            return f;
        }catch(InstantiationException | IllegalAccessException e){
            throw new Exception(e);
        }
    }
    private Class getCommandClass(HttpServletRequest request){
        Class result;
        final String command = "frontControllers." + (String) request.getParameter("command");
        try {
            result = Class.forName(command);
        } catch (ClassNotFoundException e) {
            result = UnknownCommand.class;
        }
        return result;

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
