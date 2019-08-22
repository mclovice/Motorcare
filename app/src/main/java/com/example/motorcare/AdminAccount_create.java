package com.example.motorcare;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class AdminAccount_create extends AppCompatActivity {

        private  FirebaseAuth mAuth;
        EditText Fullname, Nin, Phone,Username,Password,ConfirmPassword;
        Button Register,Cancel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_account);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("For Administrative use only");
            mAuth = FirebaseAuth.getInstance();

            Fullname = findViewById(R.id.fullname);
            Phone = findViewById(R.id.phone_number);
            Username = findViewById(R.id.usernama_or_email);
            Password = findViewById(R.id.password);
            ConfirmPassword = findViewById(R.id.confirm_password);
            Register = findViewById(R.id.register);
            Cancel = findViewById(R.id.cancel);

            //Creating account
            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Converting texts to string

                    final String Fname = Fullname.getText().toString();
                    final String Contact = Phone.getText().toString();
                    final String UName = Username.getText().toString();
                    final String Pass = Password.getText().toString();
                    final String CPass = ConfirmPassword.getText().toString();

                    if(Fname.isEmpty() && Fname.length()<3){
                        Toast.makeText(AdminAccount_create.this,"Please enter name that is not less than three character",Toast.LENGTH_LONG).show();
                    }

                    else if(Contact.isEmpty() && Contact.length()<10 ){
                        Toast.makeText(AdminAccount_create.this,"Please enter phone number not less than 10 digits",Toast.LENGTH_LONG).show();

                    }else if(UName.isEmpty()){
                        Toast.makeText(AdminAccount_create.this,"Please enter User name",Toast.LENGTH_LONG).show();
                    }else if(UName.length() < 4 && UName.length() > 20){
                        Toast.makeText(AdminAccount_create.this,"User name should be between 6 to 18 characters",Toast.LENGTH_LONG).show();
                    }

                    else if(Pass.isEmpty()){
                        Toast.makeText(AdminAccount_create.this,"Please enter password",Toast.LENGTH_LONG).show();
                    }else if(Pass.length() < 6){
                        Toast.makeText(AdminAccount_create.this,"Password should have at least 6 characters",Toast.LENGTH_LONG).show();
                    }

                    else if(!CPass.equals(Pass)){
                        Toast.makeText(AdminAccount_create.this,"Password fields not matching",Toast.LENGTH_LONG).show();

                    }else {
                        final ProgressDialog dialog = new ProgressDialog(AdminAccount_create.this);
                        dialog.setTitle("CREATING ACCOUNT");
                        dialog.setMessage("Please wait.....");
                        dialog.show();
                        mAuth.createUserWithEmailAndPassword(UName,Pass).addOnCompleteListener(AdminAccount_create.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    dialog.dismiss();
                                    Toast.makeText(AdminAccount_create.this,"Account creation failed",Toast.LENGTH_LONG).show();
                                    //Log.e(this,"")
                                }else {
                                    String users = mAuth.getCurrentUser().getUid();
                                    Map userInformation = new HashMap();
                                    userInformation.put("FullName",Fname);
                                    userInformation.put("PhoneNumber",Contact);
                                    userInformation.put("UserName",UName);
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Administrators").child(users);
                                    databaseReference.setValue(userInformation);
                                    Toast.makeText(AdminAccount_create.this,"Account created",Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(AdminAccount_create.this, AdminsCoverPage.class);
                                    startActivity(intent);
                                    dialog.dismiss();

                                }
                            }
                        });


                    }

                }
            });

            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminAccount_create.this, AdminsLogin.class);
                    startActivity(intent);
                }
            });
        }
    }
