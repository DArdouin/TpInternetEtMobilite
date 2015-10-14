/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthockey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Damien
 */
public class ClientHockey {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        // TODO code application logic here
        DatagramSocket s = new DatagramSocket(11111);
        byte[] buf = new byte[1000];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        InetAddress hostAddress = InetAddress.getByName("localhost");
        while (true) {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String outMessage = stdin.readLine();

            if (outMessage.equals("bye"))
              break;
            String outString = "Client say: " + outMessage;
            buf = outString.getBytes();

            DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, 9999);
            s.send(out);
        }

    }
    
}
