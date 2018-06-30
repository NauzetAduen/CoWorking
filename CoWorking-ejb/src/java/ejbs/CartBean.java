package ejbs;

import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Reservation;

@Stateful
@LocalBean
public class CartBean {
    private ArrayList<Reservation> cart = new ArrayList<>();
    private String user = "";

    public void setUser(String user) {
        this.user = user;
    }
    
    
    public void add(Reservation r){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "add", user);
        } catch (NamingException | IOException ex) {
        }
        
        cart.add(r);
    }
    public void remove(Reservation r){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "remove", user);
        } catch (NamingException | IOException ex) {
        }
        cart.remove(r);
        
    }

    public ArrayList<Reservation> getCart() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "getCart", user);
        } catch (NamingException | IOException ex) {
        }
        return cart;
    }
    public int size(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "size", user);
        } catch (NamingException | IOException ex) {
        }
        return cart.size();
    }

    @PostConstruct
    public void postconstruct() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "postConstruct", user);
            cart.clear();
        } catch (NamingException | IOException ex) {
        }

    }

    public String getUser() {
        return user;
    }

    @PreDestroy
    public void predestroy() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "PreDestroy", user);
        } catch (NamingException | IOException ex) {
        }

    }
    @PostActivate
    public void postactivate(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "PostActivate", user);
        } catch (NamingException | IOException ex) {
        }
        
    }
    //@PreActivate
    public void preactivate(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("CartBean", "PreActivate", user);
        } catch (NamingException | IOException ex) {
        }
        
    }

    public Reservation getReservationById(int id) {
        for (Reservation reservation : cart) {
            if(reservation.getReservationID() == id) return reservation;
        }
        return null;
    }
}
