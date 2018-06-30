package ejbs;

import java.io.IOException;
import model.Reservation;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.PostActivate;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@LocalBean
public class ReservationsBean {
    private ArrayList<Reservation> list = new ArrayList<>();
    private String user ="";

    public void setUser(String user) {
        this.user = user;
    }
    
    
    @Lock(LockType.READ)
    public ArrayList<Reservation> getList() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("ReservationBean", "getList", user);
        } catch (NamingException | IOException ex) {
        }
        return list;
    }
    @Lock(LockType.WRITE)
    public void addReservation(Reservation r){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("ReservationBean", "addReservation", user);
        } catch (NamingException | IOException ex) {
        }
        list.add(r);
    }
    @PostConstruct
    public void init() {
        list.clear();
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("ReservationBean", "PostConstruct", user);
        } catch (NamingException | IOException ex) {
        }

    }

    @PreDestroy
    public void predestroy() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("ReservationBean", "preDestroy", user);
        } catch (NamingException | IOException ex) {
        }

    }

}
