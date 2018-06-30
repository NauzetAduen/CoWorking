package Strategies;

import ejbs.WorkPlaceBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Strategy;
@Stateless
@LocalBean
public class OfertaStrategyBean implements Strategy{

    @Override
    public float calculateRevenue(String workplace) {
        try {
            WorkPlaceBean wpb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkPlaceBean!ejbs.WorkPlaceBean");
            
            return (float) (wpb.getBasePrice(workplace) * 0.5);
        } catch (NamingException ex) {
        }
        return 100f;
    }
}
