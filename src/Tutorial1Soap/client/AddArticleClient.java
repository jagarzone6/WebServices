package Tutorial1Soap.client;

/**
 * Created by jgarzon on 1/02/16.
 */
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;


public class AddArticleClient {
    private static EndpointReference targetEPR =
            new EndpointReference(
                    "http://localhost:8080/axis2/services/CMSService");

    private static OMElement getOMElement(){

        SOAPFactory fac = OMAbstractFactory.getSOAP12Factory();
        OMNamespace omNs = fac.createOMNamespace(
                "http://daily-moon.com", "cms");
        OMElement method = fac.createOMElement("addArticle", omNs);

        OMElement category = fac.createOMElement("category", omNs);
        category.setText("classifieds");

        OMElement subcategory =
                fac.createOMElement("subcategory", omNs);
        category.setText("wantads");

        OMElement adtext = fac.createOMElement("article", omNs);
        adtext.setText("Do you  have good head for numbers"+
                " and a great deal of patience?  Do you like"+
                " to sit for hours sorting objects by their"+
                " size?  If so, then you could be the"+
                " next goober counter in the world famous"+
                " Murphy Brothers peanut factory. "+
                " Willingness to dress up as our mascot"+
                " helpful, but not required.");

        method.addChild(category);
        method.addChild(subcategory);
        method.addChild(adtext);

        return method;

    }

    public static void main(String[] args) {
        try {
            OMElement payload = AddArticleClient.getOMElement();
            ServiceClient serviceClient = new ServiceClient();

            Options options = new Options();
            serviceClient.setOptions(options);
            options.setTo(targetEPR);

            OMElement result =  serviceClient.sendReceive(payload);
            String response = result.getText();
            System.out.println("This is the response from Service "+response+" .");


        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }
}
