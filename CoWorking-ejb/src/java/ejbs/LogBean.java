package ejbs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.naming.InitialContext;
import javax.naming.NamingException;



@Singleton
@LocalBean
public class LogBean {
    private int index=0;
    private int indexLastWrite=0;
    private String user ="";


    private Map<Integer, List<String>> log = new HashMap<>();
    private Map<Integer, List<String>> clon = new HashMap<>();
    
    @PostConstruct
    public void init() {
        index=0;
        indexLastWrite=0;
        user="";
        log.clear();
        clon.clear();
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("LogBean", "PostConstruct", user);
        } catch (NamingException | IOException ex) {
        }
    }
    @Lock(LockType.WRITE)
    public void setUser(String user) {
        this.user = user;
    }
    @Lock(LockType.WRITE)
    public void add(List thing){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("LogBean", "add", user);
        } catch (NamingException | IOException ex) {
        }
        log.put(index++, thing);
    }
    @Lock(LockType.READ)
    public Map<Integer, List<String>> getLog() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("LogBean", "getLog", user);
        } catch (NamingException | IOException ex) {
        }
        return log;
    }
//    @Lock(LockType.READ)
//    @Schedule(second="*/5", minute="*", hour="*")
//    public void timer(){
//        try {
//            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
//            writer.setText("LogBean", "timer", user);
//        } catch (NamingException | IOException ex) {
//        }
//        if(log.equals(clon)){
//            add(new ArrayList<>(Arrays.asList("Sistema no utilizado", "Sistema")));
//        }
//        clon = new HashMap<>(log);
//    }
    
//    @Lock(LockType.READ)
//    @Schedule(second="*/5", minute="*", hour="*")
//    public void writeOnDisk(){
//        try {
//            String mensaje ="Logweb.txt\n";
//            String path = "/home/nauzetaduen/Escritorio/logWeb.txt";
//            File document = new File(path);
//            PrintWriter printWriter;
//            try (FileWriter fileWriter = new FileWriter(document, false)) {
//                printWriter = new PrintWriter(fileWriter);
//                for (Map.Entry<Integer, List<String>> entry : getLog().entrySet()) {
//                    mensaje+=entry.getValue() +"\n";
//                }   printWriter.println(mensaje);
//            }
//            printWriter.close();
//        } catch (IOException ex) {
//        }
//    }

    @PreDestroy
    public void predestroy() {
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("LogBean", "PreDestroy", user);
        } catch (NamingException | IOException ex) {
        }

    }

}