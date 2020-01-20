package com.example.elec390_ass1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profileActivity extends AppCompatActivity {

    protected static final String TAG = "profileActivity";

    EditText editText_name = null;
    EditText editText_age = null;
    EditText editText_studentID = null;

    Button button_saveProfile = null;

    private String pName;
    private String pAge;
    private String pID;

    SharedPreferences sharedPref;

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
                pName = editText_name.getText().toString();
                pAge = editText_age.getText().toString();
                pID = editText_studentID.getText().toString();

                if(checkFields()){
                    saveProfile();
                }
                disableEditText();
                //Logging for personal use
                /*
                if(pName.equals("")){
                    Log.d(TAG,"pName = empty");
                }
                if(pName == null){
                    Log.d(TAG,"pName = null");
                }

                 */
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences(getString(R.string.profileFile),Context.MODE_PRIVATE);
        editText_name.setText(sharedPref.getString(getString(R.string.profileName),""));
        editText_age.setText(sharedPref.getString(getString(R.string.profileAge),""));
        editText_studentID.setText(sharedPref.getString(getString(R.string.profileID),""));
    }

    // Menu Inflator
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
        if(item.getItemId()==R.id.item_actionButton){
            enableEditText();
            Toast.makeText(this,"Edit Profile Selected",Toast.LENGTH_SHORT).show();
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

        disableEditText();
    }           //Initializes UI
    protected void enableEditText(){
        editText_studentID.setEnabled(true);
        editText_name.setEnabled(true);
        editText_age.setEnabled(true);
    }    //Enables all edit text fields in this activity
    protected void disableEditText(){
        editText_studentID.setEnabled(false);
        editText_name.setEnabled(false);
        editText_age.setEnabled(false);
    }   //Disables all edit text fields in this activity


    protected Boolean checkFields(){           //Checking if all the profile fields meet the requirements.
        if(pName.equals("") || pAge.equals("") || pID.equals("")){
            Toast.makeText(this,"all fields must be filled",Toast.LENGTH_SHORT).show();
            return false;
        } else if( Integer.parseInt(pAge)<18){
            editText_age.setText("");
            Log.d(TAG,"checkField Age = empty");
            editText_age.requestFocus();
            Toast.makeText(this,"Age limit not in range (18-99)",Toast.LENGTH_SHORT).show();
            return false;
        } else if( Integer.parseInt(pID)<100000){
            editText_studentID.setText("");
            Log.d(TAG,"checkField ID = empty");
            editText_studentID.requestFocus();
            Toast.makeText(this,"ID must be 8 digits and cannot start with 0",Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }

    protected void saveProfile(){
        sharedPref = getSharedPreferences(getString(R.string.profileFile), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.profileName),pName);
        editor.putString(getString(R.string.profileAge),pAge);
        editor.putString(getString(R.string.profileID),pID);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Profile Saved",Toast.LENGTH_SHORT).show();
    }
}
