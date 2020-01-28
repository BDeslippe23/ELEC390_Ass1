package com.example.elec390_ass1;

public class UserProfile {
    private String pName;
    private String pAge;
    private String pID;

    public UserProfile(){
        pName = "";
        pAge = "";
        pID = "";
    }
    public UserProfile(String Name, String Age, String ID){
        this.pName = Name;
        this.pAge = Age;
        this.pID = ID;
    }

    //Brings in the three fields from a passed profile
    public UserProfile(UserProfile importProfile){
        this.pName = importProfile.getpName();
        this.pAge = importProfile.getpAge();
        this.pID = importProfile.getpID();
    }


    // Setters and Getters
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
        this.pAge = pAge;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }
}
