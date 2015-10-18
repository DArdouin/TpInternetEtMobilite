package com.example.dimitri.soireehockey;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Match.ListeDesMatchs;
import protocole.Methodes;
import protocole.Request;


public class SuiviMatchs extends AppCompatActivity {

    private ListView listematch;
    private ListeDesMatchs listeDesMatchs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi_matchs);


        for(int i = 0; i < 10; i++) {
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
                System.out.println("Réponse reçue !");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        listematch = (ListView) findViewById(R.id.listeMatch);
        List<HashMap<String,String>> liste = new ArrayList<HashMap<String,String>>();
        try {
            listeDesMatchs = new ListeDesMatchs();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < listeDesMatchs.getMatchs().size(); i++)
        {
            HashMap<String,String> exemple = new HashMap<String,String>();
            exemple.put("domicile", listeDesMatchs.getMatchs().get(i).getEquipeDomicile().getNom());
            exemple.put("versus","versus");
            exemple.put("exterieur", listeDesMatchs.getMatchs().get(i).getEquipeExterieur().getNom());
            liste.add(exemple);

        }


        SimpleAdapter adapter = new SimpleAdapter(this,liste, R.layout.layout_match, new String[]{"domicile","versus","exterieur"}, new int []{R.id.domicile, R.id.versus, R.id.exterieur });

        listematch.setAdapter(adapter);

        listematch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) listematch.getItemAtPosition(position);
                Intent detailmatch = new Intent(SuiviMatchs.this,detailMatch.class);
                detailmatch.putExtra("match", listeDesMatchs.getMatchs().get(position));

                startActivity(detailmatch);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suivi_matchs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
