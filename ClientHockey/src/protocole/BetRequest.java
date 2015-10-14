/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import java.net.DatagramPacket;
import java.net.SocketAddress;

/**
 *
 * @author Quentin
 */
public class BetRequest extends Message{
    public static BetRequest unmarshall(DatagramPacket request) {
            // TODO Auto-generated method stub
            return null;
    }
    public static byte[] marshall(BetRequest requete) {
            // TODO Auto-generated method stub
            return null;
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
