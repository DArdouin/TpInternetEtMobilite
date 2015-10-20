package com.example.dimitri.soireehockey;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Match.Match;
import Paris.Paris;
import protocole.Request;

public class Parier extends AppCompatActivity {

    private Match match;
    private ListView choixmatch;
    private EditText montant;
    private Button envoyer;
    private EnvoyerParisTask task;
    private Paris paris;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paris);

        choixmatch = (ListView) findViewById(R.id.choixmatch);
        montant = (EditText) findViewById(R.id.montant);
        envoyer = (Button) findViewById(R.id.buttonParis);


        Intent i = getIntent();
        match = (Match) i.getSerializableExtra("match");

        List<HashMap<String,String>> liste = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> exemple = new HashMap<>();
        exemple.put("equipe", match.getEquipeDomicile().getNom());
        liste.add(exemple);

        HashMap<String,String> exemple1 = new HashMap<>();
        exemple1.put("equipe", match.getEquipeExterieur().getNom());
        liste.add(exemple1);

        SimpleAdapter adapter = new SimpleAdapter(this,liste, android.R.layout.simple_list_item_single_choice, new String[]{"equipe"}, new int []{android.R.id.text1 });

        choixmatch.setAdapter(adapter);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textmontant = montant.getText().toString();

                if(choixmatch.getCheckedItemCount() > 0)
                {
                    if((textmontant != null) && (textmontant.trim().length() > 0))
                    {
                        paris = new Paris();
                        paris.setSomme(Integer.parseInt(montant.getText().toString()));
                        if(choixmatch.getCheckedItemPosition() == 0)
                        {
                            paris.setNomDeLequipe(match.getEquipeDomicile().getNom());
                        }
                        if(choixmatch.getCheckedItemPosition() == 1)
                        {
                            paris.setNomDeLequipe(match.getEquipeExterieur().getNom());
                        }

                        task = new EnvoyerParisTask(Parier.this, paris,match);
                        AsyncTask<Match, Void, Boolean> retour = task.execute();
                        try {

                            Boolean result = retour.get();
                            if(result == true)
                                Toast.makeText(Parier.this, "Votre paris a été envoyé",Toast.LENGTH_LONG).show();
                            if(result == false)
                                Toast.makeText(Parier.this, "Votre paris n'a pas été envoyé",Toast.LENGTH_LONG).show();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                    else
                        Toast.makeText(Parier.this, "Veuillez entrez le montant de votre pari", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Parier.this, "Veuillez choisir une equipe",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paris, menu);
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
