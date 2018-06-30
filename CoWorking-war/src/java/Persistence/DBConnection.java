package Persistence;

import ejbs.CartBean;
import ejbs.SessionBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DBConnection {
    private final HttpServletRequest request;

    public DBConnection(HttpServletRequest request) {
        this.request = request;
    }
    
    
    public boolean userIsValid() throws SQLException{
        try {
            ResultSet resultSet = connectAndQuery();
            if(resultSet.next()){
                updateSession(resultSet);
                return true;
            }
            
        } catch (SQLException | NamingException e) {}
       
        return false;
    }
    
    public ResultSet connectAndQuery() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        String query = "select * from APP.USERS where USERNAME = ? AND PASSWORD = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, request.getParameter("user"));
        statement.setString(2, request.getParameter("password"));
        return statement.executeQuery();
    }

    public void updateSession(ResultSet resultSet) throws NamingException {
        try {
            CartBean cart = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/CartBean!ejbs.CartBean");
            SessionBean sessionBean = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/SessionBean!ejbs.SessionBean");
            String username = resultSet.getString("username");
            String name = resultSet.getString("name");
            int type = resultSet.getInt("role");
            HttpSession session = request.getSession(true);
            sessionBean.init(username, type, name);
            session.setAttribute("userData", sessionBean);
            session.setAttribute("username", username);
            session.setAttribute("cart",cart);
        } catch (SQLException ex) {
        }
    }
    public boolean isAdmin(){
        HttpSession session = request.getSession(true);
        return ((SessionBean)session.getAttribute("userData")).isAdmin();
    }
    
}
    
