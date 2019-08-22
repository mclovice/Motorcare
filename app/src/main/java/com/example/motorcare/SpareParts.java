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

public class SpareParts extends AppCompatActivity {

    EditText SpareName, Cost, Model;
    TextView SparePhoto;
    Button Cancel, SubmitSpare;
    private FirebaseAuth mAuth;
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference("SpareParts").push();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Spare parts");

        mAuth = FirebaseAuth.getInstance();

        SpareName = findViewById(R.id.spare_name);
        Cost = findViewById(R.id.spare_cost);
        Model = findViewById(R.id.spare_model);
        SparePhoto = findViewById(R.id.spare_attach_file);
        Cancel = findViewById(R.id.spare_cancel_btn);
        SubmitSpare = findViewById(R.id.spare_submit_btn);


        //Creating account
        SubmitSpare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converting texts to string

                final String Spare = SpareName.getText().toString();
                final String SpareCost = Cost.getText().toString();
                final String SpareModel = Model.getText().toString();
               // final String Photo = SparePhoto.getText().toString();


                if(Spare.isEmpty() && Spare.length()<3){
                    Toast.makeText(SpareParts.this,"Please enter Spar Part name",Toast.LENGTH_LONG).show();
                }

                else if(SpareCost.isEmpty() && SpareCost.length()<2 ){
                    Toast.makeText(SpareParts.this,"Please enter the Price",Toast.LENGTH_LONG).show();

                }else if(SpareModel.isEmpty()){
                    Toast.makeText(SpareParts.this,"Please enter spare part Model",Toast.LENGTH_LONG).show();
                }else if(SpareModel.length() < 4 && SpareModel.length() > 25){
                    Toast.makeText(SpareParts.this,"Model field should be at least 4 characters",Toast.LENGTH_LONG).show();
                }

//                else if(Photo.isEmpty()){
//                    Toast.makeText(SpareParts.this,"You did not attach a photo",Toast.LENGTH_LONG).show();
//                }

                else {
                    final ProgressDialog dialog = new ProgressDialog(SpareParts.this);
                    dialog.setTitle("UPLOADING SPARE PART");
                    dialog.setMessage("Please wait.....");
                    dialog.show();

                                String spares = mAuth.getCurrentUser().getUid();
                                Map spareInfo = new HashMap();
                                spareInfo.put("SparePartName",Spare);
                                spareInfo.put("Cost",SpareCost);
                                spareInfo.put("Model",SpareModel);
                                //spareInfo.put("Photo",Photo);
                                reference.setValue(spareInfo);
                                Toast.makeText(SpareParts.this,"Spare part upload Successful",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(SpareParts.this, Mechanic_Access.class);
                                startActivity(intent);
                                dialog.dismiss();

                            }
                        }
                    });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpareParts.this, Mechanic_Access.class);
                startActivity(intent);
            }
        });
    }
}
