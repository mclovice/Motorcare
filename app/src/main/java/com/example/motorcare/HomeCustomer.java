package com.example.motorcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeCustomer extends AppCompatActivity {
    CardView Mechanic,Fuel,CarHiring,WashingBay;
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        Mechanic = findViewById(R.id.mechanic);
        Fuel = findViewById(R.id.fuel);
        CarHiring = findViewById(R.id.carhiring);
        WashingBay = findViewById(R.id.washingbay);
        mAuth = FirebaseAuth.getInstance();
        Mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mechanic = new Intent(HomeCustomer.this, AvailableMechanics.class);
                startActivity(mechanic);
            }
        });
        Fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fuel = new Intent(HomeCustomer.this, FuelActivity.class);
                startActivity(fuel);
            }
        });
        CarHiring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hiring = new Intent(HomeCustomer.this, CarHiringActivity.class);
                startActivity(hiring);
            }
        });
        WashingBay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent washing = new Intent(HomeCustomer.this, WashingBayActivity.class);
                startActivity(washing);
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
