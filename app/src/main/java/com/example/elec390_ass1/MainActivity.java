package com.example.elec390_ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    protected static final String TAG = "__MainActivity";
    Button button_goToGrades = null;
    Button button_goToProfile = null;

    protected SharedPreferencesHelper profileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"On Create");
        setupUI();
        profileHelper = new SharedPreferencesHelper(MainActivity.this);

        button_goToGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"to Grade Activity");
                gotoGradeActivity();
            }
        });
        button_goToProfile.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"to Profile Activity");
                gotoProfileActivity();
            }
        }));

        checkProfileName();
    }

    @Override
    protected void onResume() {
        //Profile is checked everytime the activity is on screen
        super.onResume();
        checkProfileName();
        Log.d(TAG,"Main Activity Resumed");
    }

    protected void setupUI(){
        button_goToGrades = findViewById(R.id.button_goToGrades);
        button_goToProfile = findViewById(R.id.button_goToProfile);
    }
    protected void gotoGradeActivity(){
        Intent toGrades = new Intent(MainActivity.this,gradeActivity.class);
        startActivity(toGrades);
    }
    protected void gotoProfileActivity(){
        Intent toProfile = new Intent(MainActivity.this,profileActivity.class);
        startActivity(toProfile);
    }
    protected void checkProfileName(){      //If there was a name stored via shared preferences the button text will be updated.
        String name = profileHelper.getProfile().getpName();


        Log.d(TAG,"Name = "+name);
        if(name.equals("")){
            Toast.makeText(getApplicationContext(),"You must create a profile",Toast.LENGTH_SHORT).show();
            gotoProfileActivity();
        }
        else{
            button_goToProfile.setText(name);
        }
    }

}
