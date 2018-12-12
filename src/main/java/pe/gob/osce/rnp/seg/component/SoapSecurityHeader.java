package pe.gob.osce.rnp.seg.component;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.client.ServiceClient;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import java.nio.charset.Charset;

public class SoapSecurityHeader {

    public static void add(ServiceClient client, String username, String password, String token){

        OMFactory omFactory = OMAbstractFactory.getOMFactory();
        OMElement omSecurityElement = omFactory.createOMElement(new QName( "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse"), null);


        OMElement omUsertoken = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "UsernameToken", "wsu"), null);
        omUsertoken.addAttribute("wsu:Id", "SecurityToken-" + token, null);

        OMElement omUsername = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd","Username", "wsse"), null);
        omUsername.setText(username);

        OMElement omPassword = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd","Password",  "wsse"), null);
        omPassword.addAttribute("Type","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText",null );
        omPassword.setText(password);

        String authCredentials = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(authCredentials.getBytes(Charset.forName("US-ASCII")));

        OMElement omNonce = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd","Nonce", "wsse"), null);
        omNonce.setText(new String(encodedAuth));

        omUsertoken.addChild(omUsername);
        omUsertoken.addChild(omPassword);
        omUsertoken.addChild(omNonce);
        omSecurityElement.addChild(omUsertoken);
        System.out.println(omSecurityElement.toString());
        client.addHeader(omSecurityElement);
    }
}
