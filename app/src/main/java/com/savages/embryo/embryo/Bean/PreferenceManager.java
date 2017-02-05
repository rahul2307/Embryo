package com.savages.embryo.embryo.Bean;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hari on 25-05-2016.
 */
public class PreferenceManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    public PreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public  String getKeyDoc() {
        return pref.getString(KEY_DOC,""  );
    }

    public  String getKeyName() {
        return pref.getString(KEY_NAME,""  );
    }

    public  String getKEY_Mobile() {
        return pref.getString(KEY_Mobile,""  );
    }

    public  String getKeyNpp() {
        return pref.getString(KEY_NPP,""  );
    }

    public  String getKeyNom() {
        return pref.getString(KEY_NOM,""  );
    }

    public String getCity() {
        return pref.getString(KEY_City, "");
    }

    public  String getKeyGenDisease() {
        return pref.getString(KEY_GEN_DISEASE,""  );
    }

    public String getKeyDate() {
        return pref.getString(KEY_DATE,""  );
    }

    public static int getKeyVersion() {
        return KEY_VERSION;
    }

    private static final String PREF_NAME_LOGIN = "Login";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_DOC = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "userId";
    private static final String KEY_Mobile = "98066245155";
    private static final String KEY_NPP = "0";
    private static final String KEY_NOM = "0";
    private static final String KEY_City = "raipur";
    private static final String KEY_GEN_DISEASE = "no";
    private static final String KEY_DATE="2017/04/12";
    private static final int KEY_VERSION = 1;

    private static String TAG = "Shared Preference";

    // shared pref mode
    int PRIVATE_MODE = 0;

    public void setCity(String city) {
        editor.putString(KEY_City, city);
        editor.commit();
    }
    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }
    public void setMobile(String mobile) {
        editor.putString(KEY_Mobile, mobile);
        editor.commit();
    }
    public void setNPP(String npp) {
        editor.putString(KEY_NPP, npp);
        editor.commit();
    }
    public void setNOM(String nom) {
        editor.putString(KEY_NOM, nom);
        editor.commit();
    }
    public void setDOC(String doc) {
        editor.putString(KEY_DOC, doc);
        editor.commit();
    } public void setGenDisease(String genDisease) {
        editor.putString(KEY_City, genDisease);
        editor.commit();
    }




    // Shared preferences file name
    private static final String PREF_NAME = "welcome_slide";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";



    public  void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
