package com.example.motorcare;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeCustomer extends AppCompatActivity {
    CardView Mechanic,Info,Emergency1,SpareP;
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Services");
        Mechanic = findViewById(R.id.mechanic);
        Info = findViewById(R.id.info_cd_view);
        Emergency1 = findViewById(R.id.emergency_cd_view);
        SpareP = findViewById(R.id.spares_cd_view);
        mAuth = FirebaseAuth.getInstance();
        Mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mechanic = new Intent(HomeCustomer.this, AvailableMechanics.class);
                startActivity(mechanic);
            }
        });
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(HomeCustomer.this, Recycler_Info_Retrieve.class);
                startActivity(info);
            }
        });
        Emergency1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emerge = new Intent(HomeCustomer.this, Emergency.class);
                startActivity(emerge);
            }
        });
        SpareP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent spare = new Intent(HomeCustomer.this, Recycler_Spare_Retrieve_CarOwner.class);
                startActivity(spare);
            }
        });
    }
    //Implementing a mymenu


    @Override
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
              Intent intent = new Intent(HomeCustomer.this,DefaultActivity.class);
              startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
