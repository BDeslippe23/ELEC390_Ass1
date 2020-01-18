package com.example.elec390_ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button_goToGrades = null;
    Button button_goToProfile = null;
    TextView textView_info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

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

}
