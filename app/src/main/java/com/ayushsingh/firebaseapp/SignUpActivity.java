package com.ayushsingh.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText mSapId;
    private EditText mEmail;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private Button mSendData;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mSapId = findViewById(R.id.sap_id_sign_up);
        mEmail = findViewById(R.id.email_sign_up);
        mPhoneNumber = findViewById(R.id.phonenumber_sign_up);
        mPassword = findViewById(R.id.password_sign_up);
        mPasswordConfirm = findViewById(R.id.passwordconfirm_sign_up);
        mSendData = findViewById(R.id.onSignUp);

        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-app-7373b.firebaseio.com/Users");

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child(mSapId.getText().toString()).child("Email").setValue(mEmail.getText().toString());
                databaseReference.child(mSapId.getText().toString()).child("Phone").setValue(mPhoneNumber.getText().toString());
                databaseReference.child(mSapId.getText().toString()).child("Password").setValue(mPassword.getText().toString());

                onSignUp();
            }
        });
    }

    private void onSignUp (){
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Posting UnSuccessful",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Posting Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
