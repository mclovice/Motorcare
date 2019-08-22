package com.example.motorcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Mechanic_Access extends AppCompatActivity {

    CardView Spares, Emergency_view, CareInfo;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic__access);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mechanic Home");


        mAuth = FirebaseAuth.getInstance();

        Spares = findViewById(R.id.upload_spare_cardView);
        Emergency_view = findViewById(R.id.view_emergency_cardView);
        CareInfo = findViewById(R.id.mechanic_upload_info_cardView);


        Spares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mechanic_Access.this, SpareParts.class);
                startActivity(intent);
            }
        });

       Emergency_view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent emerge = new Intent(Mechanic_Access.this, Recycler_Emergency_retrieve_Mech.class);

               startActivity(emerge);
           }
       });

       CareInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Mechanic_Access.this, Mechanic_Info_Upload.class);
               startActivity(intent);
           }
       });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                mAuth.signOut();
                Intent intent = new Intent(Mechanic_Access.this,DefaultActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
