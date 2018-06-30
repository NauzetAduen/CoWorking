package ejbs;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
@Stateless
@LocalBean
public class WorkPlaceBean {
    private String user ="";

    public void setUser(String user) {
        this.user = user;
    }
    
    public int getSpots(String workplace){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("WorkPlaceBean", "getSpots", user);
        } catch (NamingException | IOException ex) {
        }
        
        if(workplace.equals("Arteixo")) return 50;
        if(workplace.equals("Mali")) return 20;
        if(workplace.equals("Journey")) return 10;
        return 0;
    }
    public int getBasePrice(String workplace){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("WorkPlaceBean", "getBasePrice", user);
        } catch (NamingException | IOException ex) {
        }
        
        if(workplace.equals("Arteixo")) return 150;
        if(workplace.equals("Mali")) return 120;
        if(workplace.equals("Journey")) return 110;
        return 100;
    }
    
    public String getDescription(String workplace){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("WorkPlaceBean", "getDescription", user);
        } catch (NamingException | IOException ex) {
        }
        if(workplace.equals("Arteixo")) 
            return "Arteixo es es un municipio español situado de la provincia de"
                    + " La Coruña, en la comunidad autónoma de Galicia. En Arteijo"
                    + " se encuentra la sede del grupo Inditex, primer grupo textil"
                    + " mundial, y de su filial más importante, Zara.";
        if(workplace.equals("Mali"))
            return "Mali es un estado sin litoral de África Occidental. "
                    + "Es el octavo país más extenso de África y limita al norte"
                    + " con Argelia, al este con Níger, al oeste con Mauritania"
                    + " y Senegal y al sur con Costa de Marfil, Guinea y Burkina Faso";
        if(workplace.equals("Journey"))
            return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere"
                    + " vestibulum cursus. Maecenas egestas volutpat fringilla. Nullam "
                    + "accumsan eget tortor nec bibendum. Aliquam pretium fermentum iaculis. "
                    + "Quisque sagittis mi at tortor sagittis, vel semper quam pharetra. Etiam"
                    + " ullamcorper ligula sit amet arcu placerat laoreet. Vestibulum non porttitor"
                    + " nisi. Ut viverra leo scelerisque ipsum vestibulum hendrerit. Ut auctor"
                    + " hendrerit consectetur. "; 
            
        return "";    
    }
    
    @PostConstruct
    public void postconstruct(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("WorkPlaceBean", "postConstruct", user);
        } catch (NamingException | IOException ex) {
        }
        
    }
    @PreDestroy
    public void predestroy(){
        try {
            WriterBean writer = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WriterBean!ejbs.WriterBean");
            writer.setText("WorkPlaceBean", "predestroy", user);
        } catch (NamingException | IOException ex) {
        }
        
    }

}
