package com.observerapp.mobile.appforobserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class terms_and_condition_1 extends AppCompatActivity {
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition_1);
        b1 = (Button)findViewById( R.id.b1 );
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( terms_and_condition_1.this,Guide1Activity.class );
                startActivity( i );
            }
        } );

    }
}
