package com.example.motorcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallMechanic extends AppCompatActivity implements CatalogAdapter.AdapterCallback {
    EditText PhoneCall;
    Button Calling;
    String Number;
    private final static int REQUEST_CODE1 = 1;
   private CatalogAdapter catalogAdapter;


    @Override
    public void onMethodCallback(String imageData) {
         Number = imageData;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_mechanic);
        ActionBar actionBar = getSupportActionBar();
        //this.catalogAdapter = new CatalogAdapter();
        actionBar.setTitle("Access a mechanic");

        PhoneCall = findViewById(R.id.phone_call);
        Calling = findViewById(R.id.call_btn);


//        Intent intent = getIntent();
//        String Contact = intent.getStringExtra("phone");
//
//        PhoneCall.setText(Contact);




        Calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //101 is a calling code

        if(requestCode == 101){
            if(grantResults[0]== getPackageManager().PERMISSION_GRANTED){

                callPhone();
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case REQUEST_CODE1:
                if (resultCode == RESULT_OK){

                    PhoneCall.setText(Number);
                }
        }
    }

    public void callPhone(){
      //checking phone number
      //22 is an android SDK version
        try{
          if (Build.VERSION.SDK_INT>22){

              if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){ // permitting a call

                  ActivityCompat.requestPermissions(CallMechanic.this, new String[]{
                          Manifest.permission.CALL_PHONE
                  },101);
                  return;
              }
              Intent callintent = new Intent(Intent.ACTION_CALL);
              callintent.setData(Uri.parse("tel:"+PhoneCall));
              startActivity(callintent);
          }
//          else {
//              Intent callintent = new Intent(Intent.ACTION_CALL);
//              callintent.setData(Uri.parse("tel:"+PhoneCall));
//              startActivity(callintent);
//          }




        } catch (Exception e){
            e.printStackTrace();
        }
    }



}
