package com.ayushsingh.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Questions extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private Button sumbitFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        final String id = intent.getStringExtra(Feedback_Fragment.EXTRA_MESSAGE);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-app-7373b.firebaseio.com/");

        rg1 = findViewById(R.id.RG1);
        rg2 = findViewById(R.id.RG2);
        rg3 = findViewById(R.id.RG3);

        sumbitFeedback = findViewById(R.id.submit_feedback);

        sumbitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rd1 = rg1.getCheckedRadioButtonId();
                r1 = findViewById(rd1);
                String s1 = r1.getText().toString();
                int rd2 = rg2.getCheckedRadioButtonId();
                r2 = findViewById(rd2);
                String s2 = r2.getText().toString();
                int rd3 = rg3.getCheckedRadioButtonId();
                r3 = findViewById(rd3);
                String s3 = r3.getText().toString();
                databaseReference.child("Feedback").child(id).child("Q1").setValue(s1);
                databaseReference.child("Feedback").child(id).child("Q2").setValue(s2);
                databaseReference.child("Feedback").child(id).child("Q3").setValue(s3);
            }
        });
    }
}
