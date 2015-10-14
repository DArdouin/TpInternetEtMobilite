package com.example.dimitri.soireehockey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class detailMatch extends AppCompatActivity {

    private TextView textLocal;
    private TextView textVisiteur;
    private TextView textLocalPoint;
    private TextView textVisiteurPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);

        textLocal = (TextView) findViewById(R.id.local);
        textVisiteur = (TextView) findViewById(R.id.visiteur);
        textLocalPoint = (TextView) findViewById(R.id.localPoint);
        textVisiteurPoint = (TextView) findViewById(R.id.visiteurPoint);

        Intent i = getIntent();

        String local = i.getStringExtra("local");
        String visiteur = i.getStringExtra("visiteur");

        Match match = i.getParcelableExtra("match");


        textLocal.setText(local);
        textVisiteur.setText(visiteur);
        textVisiteurPoint.setText(String.valueOf(match.getEquipeVisiteur().getScore()));
        textLocalPoint.setText(String.valueOf(match.getEquipeLocal().getScore()));

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
