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
        requete.setAddress("96.21.161.134");
        requete.setPort(11111);
        requete.setMethode(Methodes.demandeListMatch);
        byte[] buffout = Request.marshall(requete);
        byte[] buffin = new byte[3000];


        try {
            DatagramPacket out = new DatagramPacket(buffout, buffout.length, InetAddress.getByName("70.81.239.83"), 11111);
            DatagramSocket outsocket = new DatagramSocket();

            System.out.println("Envoi de la requête...");
            outsocket.send(out);
            if(outsocket != null)
                outsocket.close();


            DatagramPacket in = new DatagramPacket(buffin, buffin.length);
            DatagramSocket insocket = new DatagramSocket(11111);
            //insocket.setSoTimeout(20000);

            System.out.println("En attente de réponse..");
            insocket.receive(in);
            Request response = Request.unmarshall(in.getData()) ;
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
