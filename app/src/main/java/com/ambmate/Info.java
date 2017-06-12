package com.ambmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Info extends AppCompatActivity {
    String sourcestr,deststr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Spinner sourcespinner = (Spinner) findViewById(R.id.spinnersource);
        final Spinner destspinner = (Spinner) findViewById(R.id.spinnerdest);


        ArrayAdapter <CharSequence> sourceadapter = ArrayAdapter.createFromResource(this,R.array.sources,android.R.layout.simple_spinner_item);
        sourceadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        sourcespinner.setAdapter(sourceadapter);

        ArrayAdapter <CharSequence> destadapter = ArrayAdapter.createFromResource(this,R.array.destination,android.R.layout.simple_spinner_item);
        destadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        destspinner.setAdapter(destadapter);

        sourcespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("source",(String) adapterView.getItemAtPosition(i));

                sourcestr = (String) adapterView.getItemAtPosition(i);

                Toast.makeText(Info.this,(String) adapterView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        destspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("Destination",(String) adapterView.getItemAtPosition(i));

                deststr = (String) adapterView.getItemAtPosition(i);

                Toast.makeText(Info.this,(String) adapterView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
