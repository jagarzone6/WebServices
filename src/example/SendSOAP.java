package example;


/**
 * Created by jage on 9/01/16.
 */
import javax.xml.soap.*;
import javax.xml.transform.*;
import java.io.FileInputStream;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class SendSOAP {

  public static void main(String args[]) {

    try {

      MessageFactory messageFactory = MessageFactory.newInstance();
      SOAPMessage message = messageFactory.createMessage();

      //Create objects for the message parts
      SOAPPart SOAPPart = message.getSOAPPart();
      SOAPEnvelope envelope = SOAPPart.getEnvelope();
      SOAPBody body = envelope.getBody();

      SOAPElement bodyElement =
              body.addChildElement(envelope.createName("echo",
                      "req", "http://localhost:8080/axis2/services/MyService/"));
      bodyElement.addChildElement("category")
              .addTextNode("classifieds");
      message.saveChanges();

      SOAPPart SOAPpartbefore = message.getSOAPPart();
      SOAPEnvelope reqenv = SOAPpartbefore.getEnvelope();

      System.out.println("REQUEST:");
      System.out.println(reqenv.toString());

      //Now create the connection
      SOAPConnectionFactory SOAPConnFactory =
              SOAPConnectionFactory.newInstance();
      SOAPConnection connection =
              SOAPConnFactory.createConnection();

      SOAPMessage reply = connection.call(message,
              "http://localhost:8080/axis2/services/MyService");

      SOAPPart SOAPpart = reply.getSOAPPart();
      SOAPEnvelope replyenv = SOAPpart.getEnvelope();

      System.out.println("\nRESPONSE:");
      System.out.println(replyenv.toString());

      connection.close();

    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}