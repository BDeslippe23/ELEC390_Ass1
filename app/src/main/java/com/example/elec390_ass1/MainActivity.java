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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    protected static final String TAG = "__MainActivity";

    Button button_goToGrades = null;
    Button button_goToProfile = null;

    protected SharedPreferencesHelper profileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        profileHelper = new SharedPreferencesHelper(MainActivity.this);
        //Clears all preferences at startup.
        //  Used to repeatedly test inputs at app startup
        //  This code is not necessary and will remain commented out for submission
        /*
        sharedPref = getSharedPreferences(getString(R.string.profileFile),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
         */

        button_goToGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGradeActivity();
                driver();
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
        //sharedPref = getSharedPreferences(getString(R.string.profileFile), Context.MODE_PRIVATE);

        //String name = sharedPref.getString(getString(R.string.profileName),"");

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
    protected void driver(){
        for(int j=0; j<5; j++) {
            Course course = Course.generateRandomCourse();
            ArrayList<Assignment> assignments = course.getAssignments();
            System.out.println(course.getCourseTitle());
            for (int i = 0; i < assignments.size(); i++) {
                System.out.println(assignments.get(i).getAssignmentTitle()
                        + " " + assignments.get(i).getAssignmentGrade());
            }
        }
    }
}
