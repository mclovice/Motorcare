package com.example.motorcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateAccountMechanic extends AppCompatActivity {
    EditText GName,GUsername, GPhone,Location,Password,ConfirmPassword, DirectorName;
    Button Submit,Cancel;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_mechanic);
        mAuth = FirebaseAuth.getInstance();
        GName = findViewById(R.id.garagename);

        Location = findViewById(R.id.location);
        DirectorName = findViewById(R.id.director);
        GPhone = findViewById(R.id.garage_phone);
        GUsername =findViewById(R.id.username_or_email);
        Password = findViewById(R.id.password);

        ConfirmPassword = findViewById(R.id.confirm_password);
        Submit = findViewById(R.id.register);
        Cancel = findViewById(R.id.cancel);

        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String Garage = GName.getText().toString();
                final String PhoneG = GPhone.getText().toString();
                final String location = Location.getText().toString();
                final String Director = DirectorName.getText().toString();
                final String UsernameGarage = GUsername.getText().toString();
                String Pass = Password.getText().toString();
                String Confirm = ConfirmPassword.getText().toString();
                if(Garage.isEmpty()){
                    Toast.makeText(CreateAccountMechanic.this,"Please Garage name field is empty",Toast.LENGTH_LONG).show();
                }else if(Garage.length() < 10 && Garage.length() > 20){
                    Toast.makeText(CreateAccountMechanic.this,"Garage name should be between 10 to 20 characters",Toast.LENGTH_LONG).show();


                }

                else if(PhoneG.isEmpty()){
                    Toast.makeText(CreateAccountMechanic.this,"Please enter phone",Toast.LENGTH_LONG).show();

                }else if(PhoneG.length() < 10 && PhoneG.length() > 15){
                    Toast.makeText(CreateAccountMechanic.this,"Phone should have at least 10 digits",Toast.LENGTH_LONG).show();
                }

                else if(location.isEmpty()){
                    Toast.makeText(CreateAccountMechanic.this,"Please location field is empty"  ,Toast.LENGTH_LONG).show();
                }else if(location.length() < 4 && location.length() > 20){
                    Toast.makeText(CreateAccountMechanic.this,"location should have at least 4 to 20 characters",Toast.LENGTH_LONG).show();
                }

                else if(Director.isEmpty()){
                    Toast.makeText(CreateAccountMechanic.this,"Please enter Director's Name"  ,Toast.LENGTH_LONG).show();
                }else if(Director.length() < 4 && Director.length() > 20){
                    Toast.makeText(CreateAccountMechanic.this,"Director should have at least 4 to 20 characters",Toast.LENGTH_LONG).show();
                }

                else if(UsernameGarage.isEmpty()) {
                    Toast.makeText(CreateAccountMechanic.this, "Please enter Garage UserName", Toast.LENGTH_LONG).show();
                }else if(UsernameGarage.length() < 4 && UsernameGarage.length() > 20){
                    Toast.makeText(CreateAccountMechanic.this,"Garage User name should have at least 6 to 18 characters",Toast.LENGTH_LONG).show();
                }

                else if(Pass.isEmpty()){
                    Toast.makeText(CreateAccountMechanic.this,"Please Password field",Toast.LENGTH_LONG).show();
                }
                else if(Pass.length() < 6){
                    Toast.makeText(CreateAccountMechanic.this,"Password should have at least 6 characters",Toast.LENGTH_LONG).show();
                }

                else if (!Confirm.equals(Pass)){
                    Toast.makeText(CreateAccountMechanic.this,"Password fields not matching",Toast.LENGTH_LONG).show();
                }else {
                    final ProgressDialog dialog = new ProgressDialog(CreateAccountMechanic.this);
                    dialog.setTitle("Creating account");
                    dialog.setMessage("Please wait....");
                    dialog.show();
                    mAuth.createUserWithEmailAndPassword(UsernameGarage, Pass).addOnCompleteListener(CreateAccountMechanic.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        dialog.dismiss();
                                        Toast.makeText(CreateAccountMechanic.this, "Account creation failed", Toast.LENGTH_LONG).show();

                                    } else {
                                        String user_mechanics = mAuth.getCurrentUser().getUid();
                                        Map mechanics = new HashMap();
                                        mechanics.put("garageName", Garage);
                                        mechanics.put("phone", PhoneG);
                                        mechanics.put("Location", location);
                                        mechanics.put("director", Director);
                                        mechanics.put("GarageUserName", UsernameGarage);

                                        DatabaseReference my_reference = FirebaseDatabase.getInstance()
                                                .getReference().child("Mechanics").child(user_mechanics);
                                        my_reference.setValue(mechanics);
                                        dialog.dismiss();
                                        Toast.makeText(CreateAccountMechanic.this,
                                                "Successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(CreateAccountMechanic.this,MechanicLoginActivity.class);
                                        startActivity(intent);

                                    }
                                }
                            });
                }
            }
        });
    }
}
