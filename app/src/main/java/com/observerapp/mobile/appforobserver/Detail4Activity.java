package com.observerapp.mobile.appforobserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail4Activity extends AppCompatActivity implements all_time{
    Button b1;
    RadioGroup rg1;
    ProgressBar pB1;
    String userId;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail4);
        b1 = (Button)findViewById( R.id.b1 );
        rg1 = (RadioGroup) findViewById(R.id.radio);

        pB1= (ProgressBar)findViewById(R.id.pB1);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userId = sharedpreferences.getString("userId","");

        pB1.setVisibility(View.GONE);


/*


        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Detail4Activity.this,MainActivity.class );
                startActivity( i );
                overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
            }
        } );
*/
    }

    public void add_user_info4(View view){
        pB1.setVisibility(View.VISIBLE);

        int selectedId = rg1.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(selectedId);
        String s3 = rb.getText().toString();

        String param = "?criminal_record="+s3+"&user_id="+userId+"&token=YDHVJKA";
        b1.setEnabled(false);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = API_URL+"/PROJECTS/AFO/add_user_info4.php"+param;
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
                                    Intent i = new Intent( Detail4Activity.this,MainActivity.class );
                                    startActivity( i );
                                    overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
                                }else{
                                    Toast.makeText(Detail4Activity.this, "Unable to Save", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Detail4Activity.this, "Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
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
