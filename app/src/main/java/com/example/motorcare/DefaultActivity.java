package com.example.motorcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DefaultActivity extends AppCompatActivity {
    CardView Driver,Mechanic;
    Button Admin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        Driver = findViewById(R.id.driverCardView1);
        Mechanic = findViewById(R.id.mechanicCardView1);
        Admin = findViewById(R.id.Administrator_btn1);
        //We are initialising firebase
        mAuth = FirebaseAuth.getInstance();
        //We are checking if there is a current user who is logged in
//       in myListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                if(user != null){
//                    Intent myintent = new Intent(DefaultActivity.this,HomeCustomer.class);
//                    startActivity(myintent);
//                }
//
//                }
//        };
        Driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driver = new Intent(DefaultActivity.this,MainActivity.class);
                startActivity(driver);
            }
        });
        Mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mechanic = new Intent(DefaultActivity.this,MechanicLoginActivity.class);
                startActivity(mechanic);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
       // mAuth.addAuthStateListener(myListener);
    }
}
