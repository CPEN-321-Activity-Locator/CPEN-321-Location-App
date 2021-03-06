package com.cpen321.ubclocationbroadcaster;

/*Stores the details of the user logged in on the front end.*/
public class UserdetailsUtil {
    public static String username = "user1";
    public static String name;
    public static String major;
    public static String[] courseRegistered ;
    public static String school;
    public static String phone;
    public static boolean privatePublic;
    public static boolean inactivity;
    public static String activityID;

    public static boolean signedIn = false;
    public static boolean tokenGenerated = false;
    public static String token;

    public static Double lat;
    public static Double lon;

    public static Double activitylat;
    public static Double activitylon;

    public static String getURL(){
        return "http://40.117.186.20:3000";
    }

    /**This is called on sign out*/
    public static void cleanup() {
        username = null;
        name = null;
        major = null;
        courseRegistered = null;
        school = null;
        phone = null;
        privatePublic = false;
        inactivity = false;
        activityID = null;
        signedIn = false;
        lat = null;
        lon = null;
        activitylat = null;
        activitylon = null;
    }
}
