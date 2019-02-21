package com.observerapp.mobile.appforobserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tab2 extends AppCompatActivity {

    Button obj;
    Button acti;
    Button Persn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        obj = findViewById(R.id.object);
        Persn = findViewById(R.id.person);
        acti= findViewById(R.id.activity);
    }

    public void RedirectObject(View v){
        Intent i = new Intent(Tab2.this,SuspiciousPOA.class);
        startActivity(i);
    }


    public void RedirectPerson(View v){
        Intent i = new Intent(Tab2.this,SuspiciousPerson.class);
        startActivity(i);
    }


    public void RedirectActivity(View v){
        Intent i = new Intent(Tab2.this,SuspiciousActivity.class);
        startActivity(i);
    }



}

