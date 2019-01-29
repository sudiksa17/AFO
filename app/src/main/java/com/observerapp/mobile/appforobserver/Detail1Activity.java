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

public class Detail1Activity extends AppCompatActivity  implements all_time{
    Button b1;
    EditText e1,e2,e3,e4,e5,e6;
    ProgressBar pB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);
        e5 = (EditText)findViewById(R.id.e5);
        e6 = (EditText)findViewById(R.id.e6);
        b1 = (Button)findViewById(R.id.b1);

        /*
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Detail1Activity.this,Detail2Activity.class );
                startActivity( i );
                overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
            }
        } );
        */
    }

    public void add_user_info1(View view){

        pB1.setVisibility(View.VISIBLE);

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        String s4 = e4.getText().toString();
        String s5 = e5.getText().toString();
        String s6 = e6.getText().toString();

        String param = "?first_name="+s1+"&last_name="+s2+"&age="+s3+"&address="+s4+"&city="+s5+"&adhaar="+s6+"&token=YDHVJKA";
        b1.setEnabled(false);

        pB1.setVisibility(View.GONE);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = API_URL+"/PROJECTS/AFO/add_user_info1.php"+param;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
//                            res.setText("Response is: "+ response);
                            pB1.setVisibility(View.GONE);

                            if(response.equals("1")){

                                Intent i = new Intent( Detail1Activity.this,Detail2Activity.class );
                                startActivity( i );
                                overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
                                /*
                                Toast.makeText(Detail1Activity.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Detail1Activity.this,login000.class);
                                startActivity(i);
                                */
                            }else{

                                Toast.makeText(Detail1Activity.this, "Unable to save & Process your request", Toast.LENGTH_SHORT).show();
                                b1.setEnabled(true);

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    res.setText("That didn't work!");
                    pB1.setVisibility(View.GONE);
                    Toast.makeText(Detail1Activity.this, "Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
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

}
