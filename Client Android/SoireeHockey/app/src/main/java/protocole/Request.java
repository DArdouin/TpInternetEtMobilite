/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package protocole;

import java.io.Serializable;
import java.net.SocketAddress;
import org.apache.commons.lang3.SerializationUtils;


/**
 * Created by Dimitri on 16/10/2015.
 */

public class Request extends Message {

    String adress;
    int port;

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adresse) {
        this.adress = adresse;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

