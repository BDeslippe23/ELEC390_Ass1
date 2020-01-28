package com.example.elec390_ass1;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    protected SharedPreferencesHelper profileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupUI();
        profileHelper = new SharedPreferencesHelper(profileActivity.this);

        // Button commands
        button_saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserProfile savedProfile = new UserProfile();
                pName = editText_name.getText().toString();
                pAge = editText_age.getText().toString();
                pID = editText_studentID.getText().toString();

                // if the three inputs pass the check requirements, they are saved to shared preferences
                if(checkFields()){
                    savedProfile.setpName(pName);
                    savedProfile.setpAge(pAge);
                    savedProfile.setpID(pID);
                    profileHelper.saveProfile(savedProfile);
                    button_saveProfile.setVisibility(View.INVISIBLE);
                    disableEditText();
                    Toast.makeText(getApplicationContext(),"Profile Saved",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //updates the text fields when the activity is on screen
        editText_name.setText(profileHelper.getProfile().getpName());
        editText_age.setText(profileHelper.getProfile().getpAge());
        editText_studentID.setText(profileHelper.getProfile().getpID());
        Log.d(TAG,"onResume calls shared preferences");
    }

    // Creates action button in menu
    //  This uses XML file from menu resource folder
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_actionButton){
            enableEditText();
            editText_name.requestFocus();
            button_saveProfile.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Edit Profile Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    //SetupIO and other visual-aid functions
    protected void setupUI(){
        editText_age = findViewById(R.id.editText_age);
        editText_name = findViewById(R.id.editText_Name);
        editText_studentID = findViewById(R.id.editText_studentID);
        button_saveProfile = findViewById(R.id.button_saveProfile);

        disableEditText();
        button_saveProfile.setVisibility(View.GONE);
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

    //Function to check all inputs before file saving
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
            Log.d(TAG,"ID failed test");
            return false;
        }
        else return true;
    }
}
