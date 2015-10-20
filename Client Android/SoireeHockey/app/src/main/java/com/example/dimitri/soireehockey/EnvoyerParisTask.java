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
import Paris.Paris;
import protocole.Methodes;
import protocole.Request;

/**
 * Created by Dimitri on 19/10/2015.
 */
public class EnvoyerParisTask extends AsyncTask<Match,Void,Boolean> {

    private WeakReference<Parier> mActivity = null;
    private Paris paris;
    private Match match ;


    public EnvoyerParisTask(Parier parier, Paris paris, Match match)
    {
        mActivity = new WeakReference<Parier>(parier);
        this.paris = paris;
        this.match = match ;

    }

    @Override
    protected Boolean doInBackground(Match... params) {
        Request requete = new Request();
        requete.setAddress("192.168.0.37");
        requete.setPort(22222);
        requete.setMethode(Methodes.parier);
        requete.setParis(paris);
        requete.setMatch(match);


        byte[] buffout = Request.marshall(requete);
        byte[] buffin = new byte[3000];


        try {
            DatagramPacket out = new DatagramPacket(buffout, buffout.length, InetAddress.getByName("192.168.0.52"), 22222);
            DatagramSocket outsocket = new DatagramSocket();

            System.out.println("Envoi de la requête pari...");
            outsocket.send(out);
            if (outsocket != null)
                outsocket.close();


            DatagramPacket in = new DatagramPacket(buffin, buffin.length);
            DatagramSocket insocket = new DatagramSocket(22222);

            //insocket.setSoTimeout(5000);

            System.out.println("En attente de réponse pari..");
            insocket.receive(in);
            Request response = Request.unmarshall(in.getData());
            System.out.println("Réponse reçue !");
            if (insocket != null)
                insocket.close();


            Methodes confirmation = response.getMethode();
            if(confirmation.compareTo(Methodes.confirmerParis) == 0)
                return true;
            if(confirmation.compareTo(Methodes.refuserParis) == 0)
                return false;



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
