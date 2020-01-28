package com.example.elec390_ass1;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class gradeActivity extends AppCompatActivity {


    private static final String TAG = "__GradeActivity";
    ArrayList<Course> courseList = new ArrayList<>();
    ArrayList<HashMap<String,String>> myHashList = new ArrayList<HashMap<String, String>>();

    ListView listView_Courses;
    private customAdapter adapter;
    boolean numVal = true; //used to toggle letter/numerical grades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG,"onCreate");

        setupUI();
        generateCourses();
        generateListView(numVal);

    }

    protected void setupUI(){
        listView_Courses = (ListView) findViewById(R.id.listView_Courses);
    }

    // Creates action button in menu
    //  This uses XML file from menu resource folder
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_grades, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_actionButton_grades){
            numVal = convertGrades(numVal);
            Toast.makeText(this,"Convert Grades",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
    protected boolean convertGrades(boolean numVal){
        adapter = new customAdapter(getApplicationContext(), R.layout.custom_layout, courseList,!numVal);
        listView_Courses.setAdapter(adapter);
        return !numVal;

    }
    protected void generateCourses(){
        Random rnd = new Random();
        int courseNo = rnd.nextInt(5);
        for(int j=0; j<5; j++) {
            Course course = Course.generateRandomCourse();
            courseList.add(course);
//            Log.d(TAG,"Course Title: "+course.getCourseTitle());
//            for (int i = 0; i < assignments.size(); i++) {
//
//                Log.d(TAG,"Assignment: " + assignments.get(i).getAssignmentTitle()
//                        + " " + assignments.get(i).getAssignmentGrade());
//            }
        }
    }
    protected void generateListView(boolean numVal){
        adapter = new customAdapter(getApplicationContext(), R.layout.custom_layout, courseList,numVal);
        listView_Courses.setAdapter(adapter);
    }
}
