package com.example.dimitri.soireehockey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.ParseException;

import Match.*;

public class detailMatch extends AppCompatActivity {

    private TextView textLocal;
    private TextView textVisiteur;
    private TextView textLocalPoint;
    private TextView textVisiteurPoint;
    private TextView textNumPeriode;
    private TextView textTempsRestant;


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

        Intent i = getIntent();



        Match match = (Match) i.getSerializableExtra("match");
        long tempspasse = 0;
        int minutespasse = 0;
        int secondespasse = 0;
        int periode = 0;

        textLocal.setText(match.getEquipeDomicile().getNom());
        textVisiteur.setText(match.getEquipeExterieur().getNom());
        textVisiteurPoint.setText(String.valueOf(match.getNbButsDomicile()));
        textLocalPoint.setText(String.valueOf(match.getNbButsExterieur()));
        try {
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

        } catch (ParseException e) {
            e.printStackTrace();
        }

        textNumPeriode.setText(String.valueOf(periode));
        textTempsRestant.setText(String.valueOf(minutespasse)+":"+String.valueOf(secondespasse));

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
}
