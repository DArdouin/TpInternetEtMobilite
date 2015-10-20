package com.example.dimitri.soireehockey;

import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import protocole.Methodes;
import protocole.Request;
import utils.Utils;


/**
 * Created by Quentin on 18/10/15.
 */
public class SendFeedBackJob extends AsyncTask<Void,Void,Request> {

    private WeakReference<SuiviMatchs> mActivity = null;

    public SendFeedBackJob(SuiviMatchs suiviMatch)
    {
        link(suiviMatch);
    }


    @Override
    protected Request doInBackground(Void... params) {
        try {
            Request requete = new Request();
            requete.setAddress("192.168.0.37");
            requete.setPort(11111);
            requete.setMethode(Methodes.demandeListMatch);
            byte[] buffout = Request.marshall(requete);
            byte[] buffin = new byte[10000];

            DatagramPacket out = new DatagramPacket(buffout, buffout.length, InetAddress.getByName("192.168.0.52"), 11111);
            DatagramSocket outsocket = new DatagramSocket();

            System.out.println("Envoi de la requête demande list match...");
            outsocket.send(out);
            if(outsocket != null)
                outsocket.close();


            DatagramPacket in = new DatagramPacket(buffin, buffin.length);
            DatagramSocket insocket = new DatagramSocket(11111);

            //insocket.setSoTimeout(5000);

            System.out.println("En attente de réponse demande liste match..");
            insocket.receive(in);
            Request response = Request.unmarshall(in.getData()) ;
            System.out.println("Réponse reçue !");
            if(insocket != null)
                insocket.close();

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

    public void link (SuiviMatchs pActivity) {
        mActivity = new WeakReference<SuiviMatchs>(pActivity);
    }

}
