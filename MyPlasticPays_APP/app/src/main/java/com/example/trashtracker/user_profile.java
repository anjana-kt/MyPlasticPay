package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_profile extends AppCompatActivity {

    //Button btscan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        btscan = (Button)findViewById(R.id.qrbtn);
//        btscan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               startActivity(new Intent(getApplicationContext(),scannerView.class));
//            }
//        });
    }
}