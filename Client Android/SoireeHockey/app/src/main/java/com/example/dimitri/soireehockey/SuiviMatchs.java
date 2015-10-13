package com.example.dimitri.soireehockey;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuiviMatchs extends AppCompatActivity {

    private Map<TextView,Button> listeMatch;
    private LinearLayout linearLayout;
    private ListView listematch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi_matchs);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);

        TextView textView = new TextView(this);
        textView.setId(0);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("Match 9");
        textView.setTextSize(15);
        textView.setTextColor(0x112233);
        linearLayout.addView(textView);

        listematch = (ListView) findViewById(R.id.listeMatch);
        List<String> exemple = new ArrayList<String>();
        exemple.add("Ottawa vs Montreal");
        exemple.add("Chicago vs New york");
        exemple.add("Torronto vs Washington");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exemple);


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
