package ejbs;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Stateless
@LocalBean
public class SecurityBean {
    private String user = "";

    public void setUser(String user) {
        this.user = user;
    }
    
    public void checkAdmin(HttpSession session, HttpServletResponse response) throws NamingException {
        SessionBean sessionBean = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SessionBean!ejbs.SessionBean");
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SecurityBean", "checkAdmin", user);
            if (session.getAttribute("userData") == null) {
                response.sendRedirect("main.jsp");
            }
            if (!((SessionBean) session.getAttribute("userData")).isAdmin()) {
                response.sendRedirect("workplaces.jsp");
            }
        } catch (NullPointerException | NamingException | IOException ex) {
        }
    }
    public void checkNormal(HttpSession session, HttpServletResponse response) throws NamingException{
        SessionBean sessionBean = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SessionBean!ejbs.SessionBean");
         try {
             WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SecurityBean", "checkNormal", user);
            if (session.getAttribute("userData") == null) {
                response.sendRedirect("main.jsp");
            }
            if (((SessionBean) session.getAttribute("userData")).isAdmin()) {
                response.sendRedirect("admin.jsp");
            }
        } catch (NullPointerException | NamingException | IOException e) {
        }
    }
    @PostConstruct
    public void postconstruct(){
        
    }
    @PreDestroy
    public void predestroy(){
        
    }
}
