package com.ambmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.ambmate.Login.LOGIN_URL;

public class Info extends AppCompatActivity implements View.OnClickListener {
    String sourcestr,deststr;
    Button Go;
    public static final String LOGIN_URL = "http://192.168.43.58:3001/info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Go = (Button) findViewById(R.id.buttongo);

        Spinner sourcespinner = (Spinner) findViewById(R.id.spinnersource);
        final Spinner destspinner = (Spinner) findViewById(R.id.spinnerdest);

        Go.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        StringRequest strreq = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals("OK"))
                {
                   // Intent i = new Intent(Info.this,Info.class);
                    //startActivity(i);

                    System.out.println("Dhandhan");

                }

                else{
                    Toast.makeText(Info.this,response,Toast.LENGTH_LONG).show();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Info.this,error.toString(),Toast.LENGTH_LONG ).show();
                        System.out.println(error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("src",sourcestr);
                map.put("dest",deststr);
                return map;
            }
        };

        int socketTimeout = 5000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        strreq.setRetryPolicy(policy);

        RequestQueue requestQueue = Volley.newRequestQueue(Info.this);
        requestQueue.add(strreq);
    }
}
