package com.example.elec390_ass1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profileActivity extends AppCompatActivity {

    EditText editText_name = null;
    EditText editText_age = null;
    EditText editText_studentID = null;
    Button button_saveProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupUI();

        button_saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO create save profile function
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // Creates action button in menu
    //  This uses XML file from menu resource folder
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //TODO make function to run here that enables editing profile fields.
        if(item.getItemId()==R.id.item_actionButton){
            Toast.makeText(this,"Edit Profile Selected",Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    protected void setupUI(){
        editText_age = findViewById(R.id.editText_age);
        editText_name = findViewById(R.id.editText_Name);
        editText_studentID = findViewById(R.id.editText_studentID);
        button_saveProfile = findViewById(R.id.button_saveProfile);

        editText_studentID.setEnabled(false);
        editText_name.setEnabled(false);
        editText_age.setEnabled(false);
    }
}
