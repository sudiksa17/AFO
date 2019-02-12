package com.observerapp.mobile.appforobserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
ImageButton b1,b2,b7;
    Button b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        b1 = (ImageButton)findViewById( R.id.b1 );
        b2 = (ImageButton)findViewById( R.id.b2 );
        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,Detail1Activity.class );
                startActivity( i );

            }
        } );
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,sideActivity.class );
                startActivity( i );
                overridePendingTransition( R.anim.slide_in_left,R.anim.slide_out_right );
            }
        } );
        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,Tab1.class );
                startActivity( i );

            }
        } );
        b4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,Tab2.class );
                startActivity( i );

            }
        } );
        b5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,Tab3.class );
                startActivity( i );

            }
        } );
        b6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,Tab4.class );
                startActivity( i );

            }
        } );
    }
}
