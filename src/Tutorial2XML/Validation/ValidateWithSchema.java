package Tutorial2XML.Validation;

/**
 * Created by jgarzon on 1/02/16.
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

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
                "src/Tutorial2XML/Validation/classifieds.xsd");

        Document doc = null;
        try{
            DocumentBuilder parser = dbf.newDocumentBuilder();
            doc = parser.parse("src/Tutorial2XML/Validation/classifieds.xml");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}