package com.example.motorcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarHiringActivity extends AppCompatActivity {
    EditText Message, Address, Period;
    Button send, cancel;
    DatabaseHelper databaseHelper;
    String Body,AD,PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_hiring);
        Message = findViewById(R.id.body);
        Address = findViewById(R.id.address1);
        Period = findViewById(R.id.days);
        send = findViewById(R.id.send);
        cancel = findViewById(R.id.cancle_btn);
        databaseHelper = new DatabaseHelper(this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Body = Message.getText().toString();
                AD = Address.getText().toString();
                PD = Period.getText().toString();
                if(Body.isEmpty() && AD.isEmpty() && PD.isEmpty()){
                    Toast.makeText(CarHiringActivity.
                            this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }else{
                    databaseHelper.insertData(Body,AD,PD);
                    Toast.makeText(CarHiringActivity.
                            this,"Data submitted",Toast.LENGTH_LONG).show();
                     Message.setText("");
                     Period.setText("");
                     Address.setText("");

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_consult,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.view_consult:
               // Intent intent = new Intent(CarHiringActivity.this,ViewConsultation.class);
               // startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
