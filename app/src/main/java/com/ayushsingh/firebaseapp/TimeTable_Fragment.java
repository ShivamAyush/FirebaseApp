package com.ayushsingh.firebaseapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimeTable_Fragment extends Fragment {

    private Spinner spinnerBranch;
    private Spinner spinnerSem;
    private Spinner spinnerCourse;
    private Spinner spinnerTime;
    private CalendarView calendarView;
    private Button submit;
    private ArrayAdapter<CharSequence> adapterBranch;
    private ArrayAdapter<CharSequence> adapterSem;
    private ArrayAdapter<CharSequence> adapterCourse;
    private ArrayAdapter<CharSequence> adapterTime;
    private DatabaseReference databaseReference;
    private String batchId;
    private String sem;
    private String course;
    private String date;
    private String time;
    private int i = 0;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_time_table_, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-app-7373b.firebaseio.com/TimeTable");

        spinnerBranch = view.findViewById(R.id.spinner_branch);
        spinnerSem = view.findViewById(R.id.spinner_sem);
        spinnerCourse = view.findViewById(R.id.spinner_course);
        spinnerTime = view.findViewById(R.id.spinner_time);
        calendarView = view.findViewById(R.id.calendarView);
        submit = view.findViewById(R.id.submit_date);

        adapterBranch = ArrayAdapter.createFromResource(getActivity(),R.array.Select_Branch,android.R.layout.simple_spinner_item);
        adapterBranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(adapterBranch);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                batchId  = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterSem = ArrayAdapter.createFromResource(getActivity(),R.array.Select_sem,android.R.layout.simple_spinner_item);
        adapterSem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSem.setAdapter(adapterSem);
        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterCourse = ArrayAdapter.createFromResource(getActivity(),R.array.Select_course,android.R.layout.simple_spinner_item);
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapterCourse);
        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterTime = ArrayAdapter.createFromResource(getActivity(),R.array.Select_time,android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapterTime);
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + month + "/" + year;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = i + 1;
                databaseReference.child(batchId).child(sem).child(course).child("Lecture" + i).setValue(date + " " + time);
                Toast.makeText(getActivity(),"TimeTable Updated...",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
