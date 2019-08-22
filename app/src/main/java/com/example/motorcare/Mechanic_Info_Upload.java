package com.example.motorcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Mechanic_Info_Upload extends AppCompatActivity {

    EditText InfoTheme, FullInfo;

    Button Cancel, SubmitInfo;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic__info__upload);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Car Maintenance");

        mAuth = FirebaseAuth.getInstance();

        InfoTheme = findViewById(R.id.info_theme);
        FullInfo = findViewById(R.id.maintenance_information_mech);
        Cancel = findViewById(R.id.info_cancel_btn);
        SubmitInfo = findViewById(R.id.info_submit_btn);


        //Creating account
        SubmitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converting texts to string

                final String Theme = InfoTheme.getText().toString();
                final String MainInfo = FullInfo.getText().toString();


                if (Theme.isEmpty() && Theme.length() < 3) {
                    Toast.makeText(Mechanic_Info_Upload.this, "Enter what info is About", Toast.LENGTH_LONG).show();
                } else if (MainInfo.isEmpty() && MainInfo.length() < 10) {
                    Toast.makeText(Mechanic_Info_Upload.this, "Please enter the brief description", Toast.LENGTH_LONG).show();

                } else {
                    final ProgressDialog dialog = new ProgressDialog(Mechanic_Info_Upload.this);
                    dialog.setTitle("UPLOADING INFO");
                    dialog.setMessage("Please wait.....");
                    dialog.show();


                    Map spareInfo = new HashMap();
                    spareInfo.put("Theme", Theme);
                    spareInfo.put("CarMaintenanceInfo", MainInfo);
                    String spares = mAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MaintenanceInfo").child(spares);
                    databaseReference.setValue(spareInfo);
                    Toast.makeText(Mechanic_Info_Upload.this, "Congratulations!! info Uploaded", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Mechanic_Info_Upload.this, Mechanic_Access.class);
                    startActivity(intent);
                    dialog.dismiss();

                }
            }
        });


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mechanic_Info_Upload.this, Mechanic_Access.class);
                startActivity(intent);
            }
        });

    }

}