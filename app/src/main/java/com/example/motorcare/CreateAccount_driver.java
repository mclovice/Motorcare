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

public class CreateAccount_driver extends AppCompatActivity {
    private  FirebaseAuth mAuth;


    EditText Fullname, Nin, Phone,Username,Password,ConfirmPassword;
    Button Register,Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
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
                    Toast.makeText(CreateAccount_driver.this,"Please enter name that is not less than three character",Toast.LENGTH_LONG).show();
                } else if(Contact.isEmpty()){
                    Toast.makeText(CreateAccount_driver.this,"Please enter phone number",Toast.LENGTH_LONG).show();

                }else if(UName.isEmpty()){
                    Toast.makeText(CreateAccount_driver.this,"Please enter User name",Toast.LENGTH_LONG).show();

                }else if(Pass.isEmpty()){
                    Toast.makeText(CreateAccount_driver.this,"Please enter password",Toast.LENGTH_LONG).show();

                }else if(!CPass.equals(Pass)){
                    Toast.makeText(CreateAccount_driver.this,"Password fields not matching",Toast.LENGTH_LONG).show();

                }else {
                    final ProgressDialog dialog = new ProgressDialog(CreateAccount_driver.this);
                    dialog.setTitle("CREATING ACCOUNT");
                    dialog.setMessage("Please wait........");
                    dialog.show();
                    mAuth.createUserWithEmailAndPassword(UName,Pass).addOnCompleteListener(CreateAccount_driver.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                dialog.dismiss();
                                Toast.makeText(CreateAccount_driver.this,"Account creation failed",Toast.LENGTH_LONG).show();
                                //Log.e(this,"")
                            }else {
                                String users = mAuth.getCurrentUser().getUid();
                                Map userInformation = new HashMap();
                                userInformation.put("FullName",Fname);
                                userInformation.put("PhoneNumber",Contact);
                                userInformation.put("UserName",UName);
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Drivers").child(users);
                                databaseReference.setValue(userInformation);
                                Toast.makeText(CreateAccount_driver.this,"Account creation successful",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(CreateAccount_driver.this, MainActivity.class);
                                startActivity(intent);
                                dialog.dismiss();

                            }
                        }
                    });


                }

            }
        });
    }
}
