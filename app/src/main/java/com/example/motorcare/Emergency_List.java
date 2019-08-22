package com.example.motorcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Emergency_List extends AppCompatActivity {

    Button Pending, Active, Serviced, Ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Available Emergencies");


        Ok = findViewById(R.id.emergency_ok_btn);

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Emergency_List.this, Mechanic_Access.class);
                startActivity(intent);
            }
        });

    }
}
