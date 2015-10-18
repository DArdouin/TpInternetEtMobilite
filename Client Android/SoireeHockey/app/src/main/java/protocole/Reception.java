
package protocole;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Dimitri on 16/10/2015.
 */

public class Reception implements Runnable{



/**
     * Permet de récupérer les requêtes UDP
     */
/*
     * Ip du serveur
 */

    private String  serverIP;
/*
     * Port correspondant à notre service d'information des matchs
 */

    private int serverPort;
/*
     * Permet de récupérer les requêtes UDP
 */


    private DatagramSocket ReceptionSocket;

    public Reception(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {

            ReceptionSocket = new DatagramSocket(serverPort);

            byte[] buffer = new byte[1000];

            while(true)
            {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ReceptionSocket.receive(dp);
                Request requete = Request.unmarshall(dp.getData());


            }







        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
