package ejbs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@LocalBean
public class StatsBean {

    private ArrayList<String> users = new ArrayList<>();
    private Map<String, Integer> pageCount = new HashMap<>();
    private Map<String, Integer> componentCount = new HashMap<>();
    private String user = "";

    public void setUser(String user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        users.clear();
        pageCount.clear();
        componentCount.clear();
        user="";
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "PostConstruct", user);
        } catch (NamingException | IOException ex) {
        }
    }
    @Lock(LockType.READ)
    public ArrayList<String> getArrayUsers() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "getArrayUsers", user);
        } catch (NamingException | IOException ex) {
        }
        return users;
    }
    
    @PreDestroy
    public void destroy(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "PostDestroy", user);
        } catch (NamingException | IOException ex) {
        }
        
    }
    @Lock(LockType.READ)
    public Map<String, Integer> getPageCount() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "getPageCount", user);
        } catch (NamingException | IOException ex) {
        }
        return pageCount;
    }
    @Lock(LockType.READ)
    public Map<String, Integer> getComponentCount() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "getComponentCount", user);
        } catch (NamingException | IOException ex) {
        }
        return componentCount;
    }
    
    @Lock(LockType.WRITE)
    public void addPage(String page){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "addPage", user);
        } catch (NamingException | IOException ex) {
        }
        if(pageCount.containsKey(page)){
            pageCount.put(page, pageCount.get(page)+1);
        }else{
            pageCount.put(page, 1);
        }
    }
    @Lock(LockType.WRITE)
    public void addComponent(String page){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "addComponent", user);
        } catch (NamingException | IOException ex) {
        }
        if(componentCount.containsKey(page)){
            componentCount.put(page, componentCount.get(page)+1);
        }else{
            componentCount.put(page, 1);
        }
    }
    @Lock(LockType.WRITE)
    public void addUser(String user){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "addUser", user);
        } catch (NamingException | IOException ex) {
        }
        if(!users.contains(user)){
            users.add(user);
        }
    }
    @Lock(LockType.WRITE)
    public void removeUser(String user){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "removeUser", user);
        } catch (NamingException | IOException ex) {
        }
        users.remove(user);
    }
    @Lock(LockType.READ)
    public int getTotalUsers(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("StatsBean", "getTotalUsers", user);
        } catch (NamingException | IOException ex) {
        }
        return users.size();
    }
}
