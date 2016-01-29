package example2;

/**
 * Created by jage on 11/01/16.
 */

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;

import javax.xml.stream.XMLStreamException;

public class CMSService {

    public OMElement getNumberOfArticles(OMElement element)
            throws XMLStreamException {

        element.build();
        element.detach();

        String rootName = element.getLocalName();
        OMElement childElement = element.getFirstElement();
        String childName = childElement.getLocalName();
        String categoryValue = childElement.getText();

        SOAPFactory factory = OMAbstractFactory.getSOAP12Factory();
        OMNamespace namespace = factory.createOMNamespace(
                "http://daily-moon.com/cms/", "resp");
        OMElement resultElem = factory.createOMElement(
                "numberOfArticles",namespace);

        String actualValue =
                (articleCount(categoryValue)).toString();
        resultElem.setText(actualValue);

        return resultElem;
    }

    private Integer articleCount(String catId){

        //Perform some function such as searching the CMS
        //database, and return the actual value.  For our
        //purposes, you'll hardcode it.
        return new Integer(42);

    }
}
