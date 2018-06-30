/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontControllers;

import ejbs.LogBean;
import ejbs.StatsBean;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author nauzetaduen
 */
public class InfoCommand  extends FrontCommand{

    @Override
    public void process() {
        try {
            StatsBean sb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/StatsBean!ejbs.StatsBean");
            sb.addComponent("infoCommand");
            LogBean lb = InitialContext.doLookup("java:global/CoWorking/CoWorking-ejb/LogBean!ejbs.LogBean");
            lb.add(Arrays.asList("InfoCommand", request.getSession().getAttribute("username")));
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xsl = new StreamSource(new File("/home/nauzetaduen/NetBeansProjects/P1-GAulaULPGC/web/info.xsl"));
            Transformer newTransformer = factory.newTransformer(xsl);

            StreamSource xml = new StreamSource(new File("/home/nauzetaduen/NetBeansProjects/P1-GAulaULPGC/web/info.xml"));
            PrintWriter writer = response.getWriter();
            Result result = new StreamResult(writer);
            newTransformer.transform(xml, result);
            
        } catch (IOException | TransformerException | NamingException ioe) {}
    }
    
}
