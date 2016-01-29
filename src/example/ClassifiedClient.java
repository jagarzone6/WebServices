package example;

/**
 * Created by jage on 9/01/16.
 */

import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.StringWriter;

public class ClassifiedClient {
    private static EndpointReference targetEPR =
            new EndpointReference(
                    "http://localhost:8080/axis2/services/CMSService");

    public static OMElement getEchoOMElement() {
        SOAPFactory fac = OMAbstractFactory.getSOAP12Factory();
        OMNamespace omNs = fac.createOMNamespace(
                "http://daily-moon.com/cms", "cms");
        OMElement method = fac.createOMElement("getNumberOfArticles", omNs);
        OMElement value = fac.createOMElement("category", omNs);
        value.addChild(fac.createOMText(value, "classifieds"));
        method.addChild(value);

        return method;
    }

    public static void main(String[] args) {
        try {
            OMElement payload = ClassifiedClient.getEchoOMElement();
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            OMElement result = sender.sendReceive(payload);

            String response = result.getText();
            System.out.println("There are "+response+" classifieds at the moment.");

        } catch (Exception e) { //(XMLStreamException e) {
            System.out.println(e.toString());
        }
    }
}