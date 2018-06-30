package ejbs;

import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Reservation;

@Singleton
@LocalBean
public class FinancialBean {
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private int profit;
    private String user ="";

    public void setUser(String user) {
        this.user = user;
    }
    
    @PostConstruct
    public void init() {
        reservations.clear();
        profit = 0;
        user="";
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("FinancialBean", "PostConstruct", user);
        } catch (NamingException | IOException ex) {
        }
    }
    @Lock(LockType.WRITE)
    public void addTransaction(Reservation r){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("FinancialBean", "addTransaction", user);
        } catch (NamingException | IOException ex) {
        }
        reservations.add(r);
        profit += r.getCash();
    }
    @Lock(LockType.READ)
    public ArrayList<Reservation> getReservations() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("FinancialBean", "getReservations", user);
        } catch (NamingException | IOException ex) {
        }
        return reservations;
    }
    @Lock(LockType.READ)
    public int getProfit() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("FinancialBean", "getProfit", user);
        } catch (NamingException | IOException ex) {
        }
        return profit;
    }
    @Lock(LockType.READ)
    public int getTotalReservations(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("FinancialBean", "getTotalReservations", user);
        } catch (NamingException | IOException ex) {
        }
        return reservations.size();
    }
    
    @PreDestroy
    public void predestroy() {
      try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("FinancialBean", "PreDestroy", user);
        } catch (NamingException | IOException ex) {
        }  
    }
}
