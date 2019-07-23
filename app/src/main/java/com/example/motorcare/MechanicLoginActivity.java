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

public class MechanicLoginActivity extends AppCompatActivity {
    EditText Username, Password;
    Button Login, Register;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_login);
        Register = findViewById(R.id.createAccount_mechanic);
        Username = findViewById(R.id.username_mechanic);
        Password =findViewById(R.id.password_mechanic);
        Login = findViewById(R.id.login_btn_mechanic);
        mAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createaccount = new Intent(MechanicLoginActivity.this,CreateAccountMechanic.class);
                startActivity(createaccount);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(MechanicLoginActivity.this,"Please field username is empty",Toast.LENGTH_LONG).show();

                } else  if(password.isEmpty()){
                    Toast.makeText(MechanicLoginActivity.this,"Please field password is empty",Toast.LENGTH_LONG).show();

                }else {
                    final ProgressDialog dialog = new ProgressDialog(MechanicLoginActivity.this);
                    dialog.setTitle("SIGNING IN");
                    dialog.setMessage("Please wait..........");
                    dialog.show();
                    mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(MechanicLoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        dialog.dismiss();
                                        Toast.makeText(MechanicLoginActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                                        Username.setText("");
                                        Password.setText("");

                                    }else {
                                        Intent homescreen = new Intent(MechanicLoginActivity.this, MechanicHomeActivity.class);
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
