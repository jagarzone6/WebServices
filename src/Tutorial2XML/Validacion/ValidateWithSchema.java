package Tutorial2XML.Validacion;

/**
 * Created by jgarzon on 1/02/16.
 */
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ValidateWithSchema {

    public static void main(String args[]) {

        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);

        dbf.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        dbf.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaSource",
                "classifieds.xsd");

        Document doc = null;
        try{
            DocumentBuilder parser = dbf.newDocumentBuilder();
            doc = parser.parse("classifieds.xml");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}