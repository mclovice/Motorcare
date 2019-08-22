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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText UserName, Password;
    Button Regiseter_btn,Login_btn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("login Page");
        //Assigning values to variables
        UserName = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        //Buttons
        Regiseter_btn = findViewById(R.id.createAccount);
        Login_btn = findViewById(R.id.login_btn);

        //Adding functionality to to button create account
        Regiseter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRegistration = new Intent(MainActivity.this, CreateAccount_driver.class);
                startActivity(openRegistration);
            }
        });
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = UserName.getText().toString();
                String password = Password.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please field username is empty",Toast.LENGTH_LONG).show();

                } else  if(password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please field password is empty",Toast.LENGTH_LONG).show();

                }else {
                    final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("SIGNING IN");
                    dialog.setMessage("Please wait..........");
                    dialog.show();
                   mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(MainActivity.this,
                           new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if(!task.isSuccessful()){
                                       dialog.dismiss();
                                       Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();

                                   }else {
                                       Intent homescreen = new Intent(MainActivity.this, HomeCustomer.class);
                                       startActivity(homescreen);
                                       dialog.dismiss();
                                   }
                               }
                           });
                }
            }
        });

    }
}
