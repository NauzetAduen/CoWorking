package Services;


import ejbs.SessionBean;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TypeValidatorService {
    
    public static void checkAdmin(HttpSession session, HttpServletResponse response) throws NamingException {
        SessionBean sessionBean = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SessionBean!ejbs.SessionBean");
        try {
            if (session.getAttribute("userData") == null) {
                response.sendRedirect("main.jsp");
            }
            if (!((SessionBean) session.getAttribute("userData")).isAdmin()) {
                response.sendRedirect("workplaces.jsp");
            }
        } catch (NullPointerException | IOException e) {
        }
    }
    public static void checkNormal(HttpSession session, HttpServletResponse response) throws NamingException{
        SessionBean sessionBean = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SessionBean!ejbs.SessionBean");
         try {
            if (session.getAttribute("userData") == null) {
                response.sendRedirect("main.jsp");
            }
            if (((SessionBean) session.getAttribute("userData")).isAdmin()) {
                response.sendRedirect("admin.jsp");
            }
        } catch (NullPointerException | IOException e) {
        }
    }
    
    
}
