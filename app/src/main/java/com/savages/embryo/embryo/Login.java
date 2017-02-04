package com.savages.embryo.embryo;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.savages.embryo.embryo.Bean.Constants;

import com.savages.embryo.embryo.Fragments.LoginFragment;

public class Login extends AppCompatActivity {
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getPreferences(0);
        initFragment();
    }
    private void initFragment(){
        Fragment fragment= new Fragment();
        if(pref.getBoolean(Constants.IS_LOGGED_IN,true)){
            fragment = new LoginFragment();
        }else {

        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }
    private void goToFeed() {
        Intent intent = new Intent(Login.this,MyProfile.class);
        startActivity(intent);
        Login.this.finish();
    }
}
