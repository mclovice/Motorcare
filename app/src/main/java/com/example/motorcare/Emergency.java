package com.example.motorcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Emergency extends AppCompatActivity {

    EditText Problem, location, ClientContact;
    Button Submit, Cancel;
    private FirebaseAuth mAuth;
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference("Emergencies").push();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Emergency");



        mAuth = FirebaseAuth.getInstance();

        Problem = findViewById(R.id.problem);
        location = findViewById(R.id.emergency_location);
        ClientContact = findViewById(R.id.emergency_phone);

        Submit = findViewById(R.id.submit_prob);
        Cancel = findViewById(R.id.cancel_prob);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Eprob = Problem.getText().toString();
                final String Elocation = location.getText().toString();
                final String EclientContact = ClientContact.getText().toString();

                if(Eprob.isEmpty()){
                    Toast.makeText(Emergency.this,"Please enter Car problem",Toast.LENGTH_LONG).show();
                }
                else if(Elocation.isEmpty() && Elocation.length()<5){
                    Toast.makeText(Emergency.this,"Please enter immediate location (where are you Now!)",Toast.LENGTH_LONG).show();
                }
                else if(EclientContact.isEmpty() && EclientContact.length() < 10){
                    Toast.makeText(Emergency.this,"Please enter immediate contact",Toast.LENGTH_LONG).show();
                }
                else {
                    final ProgressDialog dialog = new ProgressDialog(Emergency.this);
                    dialog.setTitle("SUBMITTING YOUR EMERGENCY");
                    dialog.setMessage("Please wait.....");
                    dialog.show();

                               // String users = mAuth.getCurrentUser().getUid();

                                Map EmergencyInformation = new HashMap();
                                EmergencyInformation.put("Problem",Eprob);
                                EmergencyInformation.put("Location",Elocation);
                                EmergencyInformation.put("Contact",EclientContact);

                                reference.setValue(EmergencyInformation);
                                Toast.makeText(Emergency.this,"Emergency Submitted",Toast.LENGTH_LONG).show();
                                Toast.makeText(Emergency.this,"Please wait for a Mechanic in a few minutes",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Emergency.this, HomeCustomer.class);
                                startActivity(intent);
                                dialog.dismiss();

                            }
                        }
                    });


                }
            }


