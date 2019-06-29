package com.ayushsingh.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText mSap_ID;
    private EditText mPassword;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSap_ID = findViewById(R.id.sap_id_sign_in);
        mPassword = findViewById(R.id.password_sign_in);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-app-7373b.firebaseio.com/Users");

    }

    private void sendMainScreen (){
        Intent intent = new Intent(this,MainScreen.class);
        startActivity(intent);
    }

    public void onSignIn (View view){
        final String sapId = mSap_ID.getText().toString();
        final String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(sapId)){
            Toast.makeText(MainActivity.this,"Sap Id can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this,"Password can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(sapId).exists()){
                    if(dataSnapshot.child(sapId).child("Password").getValue().equals(password)){
                        sendMainScreen();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Password is Incorrect",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"User does not exist....",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onSignUp (View view){
        Intent intent = new Intent (this,SignUpActivity.class);
        startActivity(intent);
    }

}
