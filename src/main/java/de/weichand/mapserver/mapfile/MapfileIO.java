package de.weichand.mapserver.mapfile;

import java.io.File;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.jdom.Document;
import org.jdom.transform.JDOMResult;

/**
 *
 * @author weich_ju
 */
public class MapfileIO 
{
    
    public static Map readMapFromFile(File file) 
    {
        return JAXB.unmarshal(file, Map.class);
    }
    
    public static void writeXmlMapfile(Map map, File file) throws JAXBException 
    {
        mapfileMarshaller().marshal(map, file);
    }
    
    public static Document convertToJdom(Map map) throws JAXBException 
    {
        JDOMResult result = new JDOMResult();
        mapfileMarshaller().marshal(map, result);
        return result.getDocument();
    }
    
    public static String convertToString(Map map) throws JAXBException, TransformerConfigurationException, TransformerException 
    {
//        Document xmlMapfile = convertToJdom(map);
        
//        Transformer transformer =
//            TransformerFactory.newInstance()
//                    .newTransformer(new StreamSource("/tmp/mapfile.xsl"));
//        
//        System.out.println(transformer.getClass());
//        StringWriter writer = new StringWriter();
//        Result result = new StreamResult(writer);
//        transformer.transform(new JDOMSource(xmlMapfile), result);
//        return writer.toString();
        throw new UnsupportedOperationException("Not implemented yet!");
        
    }
    
    
    
    private static Marshaller mapfileMarshaller() throws JAXBException 
    {
        JAXBContext context = JAXBContext.newInstance(Map.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);    
        return marshaller;
    }
     
}
