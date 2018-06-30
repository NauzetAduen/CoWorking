/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import ejbs.WorkPlaceBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Strategy;

@Stateless
@LocalBean
public class ZoneStrategyBean implements Strategy{

    @Override
    public float calculateRevenue(String workplace) {
        try {
            WorkPlaceBean wpb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/WorkPlaceBean!ejbs.WorkPlaceBean");
            
            return wpb.getBasePrice(workplace);
        } catch (NamingException ex) {
        }
        return 0f;
    }
}
