package com.example.dimitri.soireehockey;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import Match.Match;
import protocole.Methodes;
import protocole.Request;

public class detailMatch extends AppCompatActivity {

    private TextView textLocal;
    private TextView textVisiteur;
    private TextView textLocalPoint;
    private TextView textVisiteurPoint;
    private TextView textNumPeriode;
    private TextView textTempsRestant;
    private TextView textPenaliteDomicile;
    private TextView textPenaliteExterieur;
    private Button parier;
    private Match match;
    private Button sync;
    private ActualisationMatchTask task;
    private TimerTask taskTimer;
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);

        textLocal = (TextView) findViewById(R.id.domicile);
        textVisiteur = (TextView) findViewById(R.id.exterieur);
        textLocalPoint = (TextView) findViewById(R.id.domicilePoint);
        textVisiteurPoint = (TextView) findViewById(R.id.exterieurPoint);
        textNumPeriode = (TextView) findViewById(R.id.numperiode);
        textTempsRestant = (TextView) findViewById(R.id.tempspasse);
        textPenaliteDomicile = (TextView) findViewById(R.id.penaliteDomicile);
        textPenaliteExterieur = (TextView) findViewById(R.id.penaliteExterieur);
        parier = (Button) findViewById(R.id.parier);
        sync = (Button) findViewById(R.id.sync);


        final Intent i = getIntent();
        match = (Match)i.getSerializableExtra("match");

        textLocal.setText(match.getEquipeDomicile().getNom());
        textVisiteur.setText(match.getEquipeExterieur().getNom());
        textVisiteurPoint.setText(String.valueOf(match.getNbButsExterieur()));
        textLocalPoint.setText(String.valueOf(match.getNbButsDomicile()));
        textPenaliteDomicile.setText(String.valueOf(match.getNbPenaliteDomicile()));
        textPenaliteExterieur.setText(String.valueOf(match.getNbPenaliteExterieur()));

        String periodetemps = recupererTemps();

        textNumPeriode.setText(String.valueOf(periodetemps.split(" ")[0]));
        textTempsRestant.setText(periodetemps.split(" ")[1]);


        // Lancement du Timer
        taskTimer = new TimerTask() {
            @Override
            public void run() {
                sync();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(taskTimer, 2000, 45000);

        parier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parier = new Intent(detailMatch.this, Parier.class);
                parier.putExtra("match", match);
                startActivity(parier);
            }
        });

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sync();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_match, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        timer.scheduleAtFixedRate(taskTimer,2000,45000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    public void sync() {
        task = new ActualisationMatchTask(detailMatch.this,match);
        AsyncTask<Match, Void, Match> retour = task.execute();

        try {
            match = retour.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        textLocal.setText(match.getEquipeDomicile().getNom());
        textVisiteur.setText(match.getEquipeExterieur().getNom());
        textVisiteurPoint.setText(String.valueOf(match.getNbButsExterieur()));
        textLocalPoint.setText(String.valueOf(match.getNbButsDomicile()));
        textPenaliteDomicile.setText(String.valueOf(match.getNbPenaliteDomicile()));
        textPenaliteExterieur.setText(String.valueOf(match.getNbPenaliteExterieur()));


        String periodetemps = recupererTemps();

        textNumPeriode.setText(String.valueOf(periodetemps.split(" ")[0]));
        textTempsRestant.setText(periodetemps.split(" ")[1]);
    }

    public String recupererTemps()
    {
        long tempspasse = 0;
        int minutespasse = 0;
        int secondespasse = 0;
        int periode = 0;
        tempspasse = match.getTemps();

        minutespasse = (int) tempspasse/60;
        if(minutespasse < 20)
        {
            periode = 1;
            tempspasse = tempspasse%60;
            secondespasse = (int)tempspasse;
        }
        else if(minutespasse > 20)
        {
            periode = 2;
            minutespasse = minutespasse - 20;
            tempspasse = tempspasse%60;
            secondespasse = (int)tempspasse;
        }
        else if(minutespasse > 40)
        {
            periode = 3;
            minutespasse -= 40;
            tempspasse = tempspasse%60;
            secondespasse = (int)tempspasse;
        }

        String temps = minutespasse + ":" + secondespasse;
        return periode + " " + temps;
    }


}
