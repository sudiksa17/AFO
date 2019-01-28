package com.observerapp.mobile.appforobserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class register000 extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    Button b1;
    ProgressBar pB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register000);

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);
        e5 = (EditText)findViewById(R.id.e5);
        b1 = (Button)findViewById(R.id.b1);
        pB1= (ProgressBar) findViewById(R.id.pB1);

        pB1.setVisibility(View.GONE);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
    }

    public void register(View view){

        pB1.setVisibility(View.VISIBLE);

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        String s4 = e4.getText().toString();
        String s5 = e5.getText().toString();

        String param = "?f_name="+s1+"&l_name="+s2+"&email="+s3+"&mobile="+s4+"&password="+s5+"&type=mobile"+"&token=YDHVJKA";

        b1.setEnabled(false);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = "https://e7230f98.ngrok.io/PROJECTS/AFO/register.php"+param;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
//                            res.setText("Response is: "+ response);
                            pB1.setVisibility(View.GONE);

                            if(response.equals("1")){
                                Toast.makeText(register000.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(register000.this,login000.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(register000.this, response, Toast.LENGTH_SHORT).show();
                                b1.setEnabled(true);

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    res.setText("That didn't work!");
                    pB1.setVisibility(View.GONE);
                    Toast.makeText(register000.this, " \n Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
                    b1.setEnabled(true);
                }
            });

            // Add the request to the RequestQueue.
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            queue.add(stringRequest);


        }catch (Exception e){
            Toast.makeText(this, "BAAL BAAL BACHA", Toast.LENGTH_SHORT).show();
        }

    }

    public void redirect_login(View view){
        Intent i = new Intent(register000.this,login000.class);
        startActivity(i);
    }

}
