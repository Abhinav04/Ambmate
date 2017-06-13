package com.ambmate;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.Console;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static android.accounts.AccountManager.KEY_PASSWORD;


public class Login extends AppCompatActivity {

    public static final String LOGIN_URL = "http://192.168.43.58:3001/login";

   private EditText ambid,ambpass;
   private Button login;

    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ambid = (EditText) findViewById(R.id.ambid);
        ambpass = (EditText) findViewById(R.id.password);


        login = (Button) findViewById(R.id.buttonlogin);

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               username = ambid.getText().toString().trim();
               password = ambpass.getText().toString().trim();

               StringRequest strreq = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                       if(response.trim().equals("OK"))
                       {
                           Intent i = new Intent(Login.this,Info.class);
                           startActivity(i);
                       }

                       else{
                           Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                       }


                   }
               },
                           new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG ).show();
                           System.out.println(error.toString());
                       }
                   }){
                       @Override
                       protected Map<String, String> getParams() throws AuthFailureError {
                           Map<String,String> map = new HashMap<String,String>();
                           map.put("userid",username);
                           map.put("password",password);
                           return map;
                       }
                   };

               int socketTimeout = 5000; // 30 seconds. You can change it
               RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                       DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                       DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

               strreq.setRetryPolicy(policy);

               RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
               requestQueue.add(strreq);



           }
       });
    }

}
