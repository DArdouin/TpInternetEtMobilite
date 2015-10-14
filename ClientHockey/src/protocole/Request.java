/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import org.apache.commons.lang3.SerializationUtils;
/**
 *
 * @author Quentin
 */
public class Request extends Message {
    public static Request unmarshall(byte[] request) {
            // TODO Auto-generated method stub
            return SerializationUtils.deserialize(request);
    }
    @SuppressWarnings("empty-statement")
    public static byte[] marshall(Request requete) {
            // TODO Auto-generated method stub
            return SerializationUtils.serialize((Serializable) requete);
    }
    public int getAddress() {
            // TODO Auto-generated method stub
            return 0;
    }
    public SocketAddress getPort() {
            // TODO Auto-generated method stub
            return null;
    }
}