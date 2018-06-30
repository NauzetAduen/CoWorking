package ejbs;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;


@Stateful
@LocalBean
public class SessionBean {
    private String username ="";
    private int type;
    private String name;

    public String getUsername() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "getUserName", username);
        } catch (NamingException | IOException ex) {
        }
        return username;
    }

    public int getType() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "getType", username);
        } catch (NamingException | IOException ex) {
        }
        return type;
    }

    public String getName() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "getName", username);
        } catch (NamingException | IOException ex) {
        }
        return name;
    }
    public void init(String username,int type,String name ) {
        this.username = username;
        this.type = type;
        this.name = name;
        addUserToSingleton();
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "init", username);
        } catch (NamingException | IOException ex) {
        }
    }
    public void addUserToSingleton(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "addUserToSingleton", username);
        } catch (NamingException | IOException ex) {
        }
        StatsBean sb;
        try {
            sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addUser(username);
        } catch (NamingException ex) {
        }
    }
    @PreDestroy
    public void remove() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "PreDestroy", username);
        } catch (NamingException | IOException ex) {
        }
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.removeUser(username);
        } catch (NamingException ex) {
        }
    }
    @PostConstruct
    public void postconstruct() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "postConstruct", username);
        } catch (NamingException | IOException ex) {
        }

    }

    @PostActivate
    public void postactivate(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "postActivate", username);
        } catch (NamingException | IOException ex) {
        }
        
    }
    //@PreActivate
    public void preactivate(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "preActivate", username);
        } catch (NamingException | IOException ex) {
        }
        
    }
    
    @Override
    public String toString(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "toString", username);
        } catch (NamingException | IOException ex) {
        }
        return "--->" + username + " --- " + type;
    }
    public boolean isAdmin(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("SessionBean", "isAdmin", username);
        } catch (NamingException | IOException ex) {
        }
        return type == 0;
    }
    
}


