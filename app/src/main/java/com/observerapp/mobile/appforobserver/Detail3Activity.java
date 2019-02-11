package com.observerapp.mobile.appforobserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail3Activity extends AppCompatActivity implements all_time{
    Button b1;
    Spinner Hq;
    RadioGroup rg1;
    RadioButton sd,sn;
    EditText e1,e2,e3,e4,e5,e6;
    ProgressBar pB1;
    String userId;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);

        b1 = (Button)findViewById(R.id.b1);
        rg1 = (RadioGroup) findViewById(R.id.shift);
        sd = (RadioButton) findViewById(R.id.shiftday);
        sn = (RadioButton) findViewById(R.id.shiftnight);

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);

        pB1= (ProgressBar)findViewById(R.id.pB1);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userId = sharedpreferences.getString("userId","");

        pB1.setVisibility(View.GONE);
/*
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Detail3Activity.this,Detail4Activity.class );
                startActivity( i );
                overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
            }
        } );
*/
    }

    public void add_user_info3(View view){
        pB1.setVisibility(View.VISIBLE);

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        int selectedId = rg1.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(selectedId);
        String s3 = rb.getText().toString();


        String param = "?occupation="+s1+"&working_hours="+s2+"&shift="+s3+"&user_id="+userId+"&token=YDHVJKA";
        b1.setEnabled(false);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = API_URL+"/PROJECTS/AFO/add_user_info3.php"+param;
            Toast.makeText(Detail3Activity.this, url, Toast.LENGTH_LONG).show();

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
//                            res.setText("Response is: "+ response);
                            pB1.setVisibility(View.GONE);

                            try {
                                Toast.makeText(Detail3Activity.this, response, Toast.LENGTH_LONG).show();
                                JSONObject res = new JSONObject(response);

                                if(res.get("result").equals("inserted")){
                                    Intent i = new Intent( Detail3Activity.this,Detail4Activity.class );
                                    startActivity( i );
                                    overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
                                }else{
                                    Toast.makeText(Detail3Activity.this, "Unable to Save", Toast.LENGTH_SHORT).show();
                                    b1.setEnabled(true);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                b1.setEnabled(true);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    res.setText("That didn't work!");
                    pB1.setVisibility(View.GONE);
                    Toast.makeText(Detail3Activity.this, "Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
                    b1.setEnabled(true);
                }
            });

            // Add the request to the RequestQueue.
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
            b1.setEnabled(true);

        }catch (Exception e){
            Toast.makeText(this, "BAAL BAAL BACHA", Toast.LENGTH_SHORT).show();
            b1.setEnabled(true);
        }

    }

}
