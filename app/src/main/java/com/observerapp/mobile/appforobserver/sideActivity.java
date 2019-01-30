package com.observerapp.mobile.appforobserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class sideActivity extends AppCompatActivity {
ImageButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_side );
        b1 = (ImageButton)findViewById( R.id.b1 );
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( sideActivity.this,MainActivity.class );
                startActivity( i );
                overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left );
            }
        } );
    }
}

