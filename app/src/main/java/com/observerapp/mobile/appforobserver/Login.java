package com.observerapp.mobile.appforobserver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    GoogleSignInClient mGoogleSignInClient;

    private LinearLayout prof_section;
    private Button Signout;
    private SignInButton Signin;
    private TextView name , email;
    private ImageView prof_pic;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prof_section = (LinearLayout) findViewById(R.id.prof_section);
        Signout = (Button) findViewById(R.id.Signout);
        Signin = (SignInButton) findViewById(R.id.Signin);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        prof_pic = (ImageView) findViewById(R.id.prof_pic);
        Signin.setOnClickListener(this);
        Signout.setOnClickListener(this);

        //prof_pic.setVisibility(View.GONE);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this , this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Signin:
                SignIn();
                break;

            case R.id.Signout:
                SignOut();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void SignIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    private void SignOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result) throws IOException {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String _name = account.getDisplayName();
            String _email = account.getEmail();
            String _img_url;

            if(account.getPhotoUrl()!=null) {
                _img_url = String.valueOf(account.getPhotoUrl());
            }else{
                _img_url = "http://www.qygjxz.com/data/out/190/5691490-profile-pictures.png";
            }
            name.setText(_name);
            email.setText(_email);

            String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
            ImageView ivBasicImage = (ImageView) findViewById(R.id.prof_pic);
            Picasso.with(this).load(_img_url).into(ivBasicImage);

          // USELESS  Glide.with(this).load(_img_url).into(prof_pic);
            updateUI(true);
        }else{
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin){
        if(isLogin){
            prof_section.setVisibility(View.VISIBLE);
            Signin.setVisibility(View.GONE);
        }else{
            prof_section.setVisibility(View.GONE);
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

}