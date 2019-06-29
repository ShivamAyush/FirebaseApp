package com.ayushsingh.firebaseapp;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseApplication extends Application {
    public void onCreate(){
        super.onCreate();

        if(!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}