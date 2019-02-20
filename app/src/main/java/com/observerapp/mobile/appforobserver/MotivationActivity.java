package com.observerapp.mobile.appforobserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MotivationActivity extends AppCompatActivity implements View.OnClickListener,all_time {
    CheckBox curious, eager, experience, observer,law;
    Button b1;
    public static final String userId = "userId";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        Intent i = new Intent(MotivationActivity.this,Tab1.class);
        startActivity(i);

        b1 = (Button) findViewById(R.id.b1);
        curious = (CheckBox) findViewById(R.id.curious);
        curious.setOnClickListener(this);
        eager = (CheckBox) findViewById(R.id.eager);
        eager.setOnClickListener(this);
        experience = (CheckBox) findViewById(R.id.experience);
        experience.setOnClickListener(this);
        observer = (CheckBox) findViewById(R.id.observer);
        observer.setOnClickListener(this);
        law = (CheckBox) findViewById(R.id.law);
        law.setOnClickListener(this);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if(sharedpreferences.contains(username)) {
            Intent I = new Intent(MotivationActivity.this,MainActivity.class);
            startActivity(I);
            finish();
            //Toast.makeText(this, "THERE IS SOMETHING IN SHARED PREFERENCE", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.curious:
                if (curious.isChecked())
                    Toast.makeText(getApplicationContext(), "You are curious to know about the app!", Toast.LENGTH_LONG).show();
                break;
            case R.id.eager:
                if (eager.isChecked())
                    Toast.makeText(getApplicationContext(), "Your eagerness is worth appreciating!", Toast.LENGTH_LONG).show();
                break;
            case R.id.experience:
                if (experience.isChecked())
                    Toast.makeText(getApplicationContext(), "We can understand your view!", Toast.LENGTH_LONG).show();
                break;
            case R.id.observer:
                if (observer.isChecked())
                    Toast.makeText(getApplicationContext(), "That's great!", Toast.LENGTH_LONG).show();
                break;
            case R.id.law:
                if (law.isChecked())
                    Toast.makeText(getApplicationContext(), "You are a well-aware citizen!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void proceed(View view){
        Intent i = new Intent(MotivationActivity.this,login000.class);
        startActivity(i);
    }
}
