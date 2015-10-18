package com.example.dimitri.soireehockey;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import protocole.Methodes;
import protocole.Request;

/**
 * Created by Quentin on 18/10/15.
 */
public class SendFeedBackJob extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        Request requete = new Request();
        requete.setAdress("192.168.0.60");
        requete.setPort(11111);
        requete.setMethode(Methodes.demandeListMatch);
        byte[] buff = Request.marshall(requete);


        try {
            DatagramPacket out = new DatagramPacket(buff, buff.length, InetAddress.getByName("192.168.0.52"), 11111);

            DatagramSocket asocket = new DatagramSocket();

            System.out.println("Envoi de la requête...");
            asocket.send(out);

            System.out.println("En attente de réponse..");
            asocket.receive(out);
            Request response = Request.unmarshall(out.getData()) ;
            System.out.println("Réponse reçue !");

            return response ;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
