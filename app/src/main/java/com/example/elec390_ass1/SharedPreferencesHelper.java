package com.example.elec390_ass1;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import java.lang.Object;


public class SharedPreferencesHelper {

    private SharedPreferences sharedPref;

    public SharedPreferencesHelper(Context context){
        sharedPref = context.getSharedPreferences(context.getString(R.string.profileFile),Context.MODE_PRIVATE);

    }

    public void saveProfile (UserProfile uProfile){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("profileName",uProfile.getpName());
        editor.putString("profileAge",uProfile.getpAge());
        editor.putString("profileID",uProfile.getpID());
        editor.apply();

    }

    public UserProfile getProfile(){
        return new UserProfile(sharedPref.getString("profileName",""),sharedPref.getString("profileAge",""),sharedPref.getString("profileID",""));
    }




}
