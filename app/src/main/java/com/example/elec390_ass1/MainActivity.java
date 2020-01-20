package com.example.elec390_ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    protected static final String TAG = "MainActivity";

    SharedPreferences sharedPref;

    Button button_goToGrades = null;
    Button button_goToProfile = null;
    TextView textView_info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        //Clears all preferences at startup.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();

        button_goToGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGradeActivity();
            }
        });
        button_goToProfile.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProfileActivity();
            }
        }));

        checkProfileName();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkProfileName();
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
    protected void checkProfileName(){
        sharedPref = getSharedPreferences(getString(R.string.profileFile), Context.MODE_PRIVATE);
        String name = sharedPref.getString(getString(R.string.profileName),"");
        Log.d(TAG,"Name = "+name);
        if(name.equals("")){
            Toast.makeText(getApplicationContext(),"You must create a profile",Toast.LENGTH_SHORT).show();
            gotoProfileActivity();
        }
    }
}
