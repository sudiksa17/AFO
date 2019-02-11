package com.observerapp.mobile.appforobserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import static com.observerapp.mobile.appforobserver.all_time.API_URL;

public class Detail2Activity extends AppCompatActivity implements all_time{
    Button b1;
    Spinner Hq;
    EditText e1,e2,e3,e4,e5,e6;
    ProgressBar pB1;
    String userId;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        b1 = (Button)findViewById(R.id.b1);
        Hq = (Spinner)findViewById(R.id.Hq);

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        pB1= (ProgressBar)findViewById(R.id.pB1);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userId = sharedpreferences.getString("userId","");

        pB1.setVisibility(View.GONE);


        String[] HqOpt = new String[]{"10th", "12th", "Diploma", "Graduate", "Post-Graduate", "PHD"};

        ArrayAdapter<String> HqA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, HqOpt);
        Hq.setAdapter(HqA);

        /*
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Detail2Activity.this,Detail3Activity.class );
                startActivity( i );
                overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
            }
        } );
        */
    }

    public void add_user_info2(View view){
        Toast.makeText(Detail2Activity.this, userId, Toast.LENGTH_LONG).show();
        pB1.setVisibility(View.VISIBLE);

        String s1 = Hq.getSelectedItem().toString();
        String s2 = e2.getText().toString();

        String param = "?highest_qualification="+s1+"&degree="+s2+"&user_id="+userId+"&token=YDHVJKA";
        b1.setEnabled(false);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = API_URL+"/PROJECTS/AFO/add_user_info2.php"+param;
            //Toast.makeText(Detail2Activity.this, url, Toast.LENGTH_LONG).show();

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
//                            res.setText("Response is: "+ response);
                            pB1.setVisibility(View.GONE);

                            try {
                                JSONObject res = new JSONObject(response);

                                if(res.get("result").equals("inserted")){
                                    Intent i = new Intent( Detail2Activity.this,Detail3Activity.class );
                                    startActivity( i );
                                    overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
                                }else{
                                    Toast.makeText(Detail2Activity.this, "Unable to Save", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Detail2Activity.this, "Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
                    b1.setEnabled(true);
                }
            });

            // Add the request to the RequestQueue.
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
            b1.setEnabled(false);

        }catch (Exception e){
            Toast.makeText(this, "BAAL BAAL BACHA", Toast.LENGTH_SHORT).show();
            b1.setEnabled(false);
        }

    }
}
