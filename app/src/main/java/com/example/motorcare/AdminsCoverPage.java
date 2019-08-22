package com.example.motorcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class AdminsCoverPage extends AppCompatActivity {

    CardView AddMechanic, UploadSparePart, ViewPayments, ViewEmergencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins_cover_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("For Administrative use only");

        AddMechanic = findViewById(R.id.add_mechanic_admin_cdView);
        ViewEmergencies = findViewById(R.id.emergencies_admin_cdView);


        AddMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminsCoverPage.this, CreateAccountMechanic.class);
                startActivity(intent);

            }
        });

        ViewEmergencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminsCoverPage.this, Emergency_List.class);
                startActivity(intent);
            }
        });
    }
}
