package com.observerapp.mobile.appforobserver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.observerapp.mobile.appforobserver.MainActivity;
import com.observerapp.mobile.appforobserver.R;
import com.observerapp.mobile.appforobserver.register000;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class login000 extends AppCompatActivity implements all_time,View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

//// G-Sign in Objects
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager callbackManager;

    private LinearLayout prof_section;
    private Button Signout;
    private Button Continue;
    private SignInButton Signin;
    private TextView name , email;
    private ImageView prof_pic;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
/////////

    EditText e1,e2,e3,e4,e5;
    Button b1;
    ProgressBar pB1;
    public static final String userId = "userId";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login000);

        Signin = (SignInButton) findViewById(R.id.Signin);
        Signin.setOnClickListener(this);
        final ProgressDialog[] mDialog = new ProgressDialog[1];

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this , this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        callbackManager = CallbackManager.Factory.create();


        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        b1 = (Button)findViewById(R.id.b1);
        pB1= (ProgressBar) findViewById(R.id.pB1);

        pB1.setVisibility(View.GONE);

        sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE);

        if(sharedpreferences.contains(username)) {
            Intent I = new Intent(login000.this,MainActivity.class);
            startActivity(I);
            finish();
            //Toast.makeText(this, "THERE IS SOMETHING IN SHARED PREFERENCE", Toast.LENGTH_SHORT).show();
        }

    }

    public void putSharedPref(String K, String V){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(K,V);
        editor.commit();

        Intent i = new Intent(login000.this,MainActivity.class);
        startActivity(i);

    }

    public void login(View view){

        pB1.setVisibility(View.VISIBLE);

        final String s1 = e1.getText().toString();
        final String s2 = e2.getText().toString();

        String param = "?username="+s1+"&password="+s2+"&token=YDHVJKA";

        b1.setVisibility(View.GONE);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = API_URL+"/PROJECTS/AFO/login.php"+param;

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

                                if(res.get("result").equals("authenticated")){
                                    String userIdVal = res.get("userId").toString();
                                    Toast.makeText(login000.this, "Welcome "+res.get("username")+"("+userIdVal+")", Toast.LENGTH_SHORT).show();
                                    putSharedPref(username,s1);
                                    putSharedPref(userId,userIdVal);
                                }else{
                                    Toast.makeText(login000.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                                    b1.setEnabled(true);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    res.setText("That didn't work!");
                    pB1.setVisibility(View.GONE);
                    Toast.makeText(login000.this, " \n Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
                    b1.setEnabled(true);
                }
            });

            // Add the request to the RequestQueue.
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            queue.add(stringRequest);


        }catch (Exception e){
            Toast.makeText(this, "BAAL BAAL BACHA", Toast.LENGTH_SHORT).show();
        }

        b1.setVisibility(View.VISIBLE);

    }

    public void redirect_register(View view){
        Intent i = new Intent(login000.this,register000.class);
        startActivity(i);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Signin:
                SignIn();
                break;

            case R.id.Signout:
                //SignOut();
                break;
        }
    }

    private void SignIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    private void SignOut(){
//        Toast.makeText(this,"Tesing",Toast.LENGTH_SHORT).show();
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                //updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result) throws IOException {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            updateUI(true);
        }else{
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin){
        if(isLogin){
            Signin.setVisibility(View.GONE);
            Intent i = new Intent(login000.this,terms_and_condition_1.class);
            startActivity(i);
        }else{
            Signin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            try {
                handleResult(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Continue(View v){
        //Toast.makeText(this,"Tesing",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(login000.this,terms_and_condition_1.class);
        startActivity(i);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
