package com.example.dimitri.soireehockey;

import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Match.Match;
import protocole.Methodes;
import protocole.Request;

/**
 * Created by Dimitri on 19/10/2015.
 */
public class ActualisationMatchTask extends AsyncTask<Match, Void, Match> {

    private Match match ;

    private WeakReference<detailMatch> mActivity = null;

    public ActualisationMatchTask(detailMatch detailMatch, Match m)
    {
        mActivity = new WeakReference<detailMatch>(detailMatch);
        match = m ;
    }

    @Override
    protected Match doInBackground(Match... params) {
        Request requete = new Request();
        requete.setAddress("192.168.0.37");
        requete.setPort(11111);
        requete.setMethode(Methodes.updateMatchInfo);
        requete.setMatch(match);

        byte[] buffout = Request.marshall(requete);
        byte[] buffin = new byte[10000];


        try {
            DatagramPacket out = new DatagramPacket(buffout, buffout.length, InetAddress.getByName("192.168.0.52"), 11111);
            DatagramSocket outsocket = new DatagramSocket();

            System.out.println("Envoi de la requête demande match info...");
            outsocket.send(out);
            if (outsocket != null)
                outsocket.close();


            DatagramPacket in = new DatagramPacket(buffin, buffin.length);
            DatagramSocket insocket = new DatagramSocket(11111);

            //insocket.setSoTimeout(5000);

            System.out.println("En attente de réponse demande match info..");
            insocket.receive(in);
            Request response = Request.unmarshall(in.getData());
            System.out.println("Réponse reçue !");
            if (insocket != null)
                insocket.close();

            return response.getMatch();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
