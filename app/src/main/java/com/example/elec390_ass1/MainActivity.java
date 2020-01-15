package com.example.elec390_ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "Main Activity";
    Button button_goToProfile = null;
    Button button_goToGrades = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_goToGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGradeActivity();
            }
        });
    }

    protected void setupUI(){
        Log.d(TAG,"SetupUI Started");
        button_goToGrades = (Button) findViewById(R.id.button_goToGrades);
        button_goToProfile = (Button) findViewById(R.id.button_goToProfile);

    }

    void goToGradeActivity(){
        Intent goToGradeActivity = new Intent(MainActivity.this, gradeActivity.class);
        startActivity(goToGradeActivity);
    }
}
