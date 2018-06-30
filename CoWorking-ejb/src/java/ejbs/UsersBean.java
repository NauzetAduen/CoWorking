package ejbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import model.Reservation;

@Singleton
@LocalBean
public class UsersBean {
        ResultSet rs = null;
        private Map<String, Integer> users = new HashMap<>();
        public Map<String,Integer> getUsers(){
            int total = 0;
            users.clear();
            try {
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
                String query = "select * from APP.USERS";
                PreparedStatement statement = con.prepareStatement(query);
                rs = statement.executeQuery();
                while(rs.next()){
                    total++;
                    users.put(rs.getString("username"), rs.getInt("iduser"));
                }
                con.close();
            } catch (SQLException ex) {
            }
            return users;
        }
        
}
