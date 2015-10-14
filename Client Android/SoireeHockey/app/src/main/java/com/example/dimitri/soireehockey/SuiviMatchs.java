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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuiviMatchs extends AppCompatActivity {

    private ListView listematch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi_matchs);

        Equipe torronto = new Equipe("Torronto");
        Equipe washington = new Equipe("Washington");
        Equipe montreal = new Equipe("Montreal");
        Equipe ottawa = new Equipe("Ottawa");
        Equipe chicago = new Equipe("Chicago");
        Equipe newYork = new Equipe("New York");
        final Match match1 = new Match(torronto,washington);
        Match match2 = new Match(montreal,ottawa);
        Match match3 = new Match(chicago, newYork);

        final List<Match> listeDesMatchs= new ArrayList<Match>();
        listeDesMatchs.add(match1);
        listeDesMatchs.add(match2);
        listeDesMatchs.add(match3);

        listematch = (ListView) findViewById(R.id.listeMatch);
        List<HashMap<String,String>> liste = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> exemple = new HashMap<String,String>();
        exemple.put("local", match1.getEquipeLocal().getNom());
        exemple.put("versus","versus");
        exemple.put("visiteur", match1.getEquipeVisiteur().getNom());
        liste.add(exemple);

        HashMap<String,String> exemple1 = new HashMap<String,String>();
        exemple1.put("local", match2.getEquipeLocal().getNom());
        exemple1.put("versus", "versus");
        exemple1.put("visiteur", match2.getEquipeVisiteur().getNom());
        liste.add(exemple1);

        HashMap<String,String> exemple2 = new HashMap<String,String>();
        exemple2.put("local",match3.getEquipeLocal().getNom());
        exemple2.put("versus","versus");
        exemple2.put("visiteur", match3.getEquipeVisiteur().getNom());
        liste.add(exemple2);

        SimpleAdapter adapter = new SimpleAdapter(this,liste, R.layout.layout_match, new String[]{"local","versus","visiteur"}, new int []{R.id.local, R.id.versus, R.id.visiteur });

        listematch.setAdapter(adapter);

        listematch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) listematch.getItemAtPosition(position);
                Intent detailmatch = new Intent(SuiviMatchs.this,detailMatch.class);
                detailmatch.putExtra("local", map.get("local"));
                detailmatch.putExtra("visiteur",map.get("visiteur"));
                detailmatch.putExtra("match",listeDesMatchs.get(position));
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
