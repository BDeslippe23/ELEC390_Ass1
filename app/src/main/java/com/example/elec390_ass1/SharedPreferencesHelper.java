package com.example.elec390_ass1;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPreferencesHelper {

    private SharedPreferences sharedPref;

    public SharedPreferencesHelper(Context context){
        //Connects the sharedPref object with the shared preferences for the profile
        sharedPref = context.getSharedPreferences(context.getString(R.string.profileFile),Context.MODE_PRIVATE);

    }

    //Stores text values in appropriate key
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
