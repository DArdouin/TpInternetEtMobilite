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
import Match.Match;
import protocole.Request;

/**
 *
 * @author Quentin
 */
public class Request extends Message implements Serializable{
public static Request unmarshall(byte[] request) {
            // TODO Auto-generated method stub
            return SerializationUtils.deserialize(request);
    }
    @SuppressWarnings("empty-statement")
    public static byte[] marshall(Request request) {

            return SerializationUtils.serialize((Serializable) request);
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