package com.savages.embryo.embryo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.savages.embryo.embryo.Adapter.ProfileAdapter;
import com.savages.embryo.embryo.Adapter.ProfileList;
import com.savages.embryo.embryo.Bean.PreferenceManager;

import java.util.ArrayList;
import java.util.Calendar;

public class Profile3rdVisit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private TextView navUser;
    private Button visit1st;
    private Button visit2nd;
    private Button visit3rd;
    private Button visit4th;

    private static String LOG_TAG = "CardViewActivity";
    private com.savages.embryo.embryo.Bean.PreferenceManager PreferenceManager;
    ArrayList<ProfileList> profileLists = new ArrayList<>();

    private final String test_name[]={
            "Tests And Vaccines"



    };
    private final int categories_imgId[] = {

            R.drawable.download
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile3rd_visit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PreferenceManager = new PreferenceManager(this);

        initViews();
           visit1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
        visit2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile2ndVisit();
            }
        });
        /*visit3rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile3rdVisit();
            }
        });*/
        visit4th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile4thVisit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navUser = (TextView)hView.findViewById(R.id.nav_user);
        navUser.setText(PreferenceManager.getKeyName());
    }
    private void goToProfile2ndVisit() {
        Intent intent = new Intent(Profile3rdVisit.this,Profile2ndVisit.class);
        startActivity(intent);
    }
    private void goToProfile() {
        Intent intent = new Intent(Profile3rdVisit.this,MyProfile.class);
        startActivity(intent);
    }
    private void goToProfile4thVisit() {
        Intent intent = new Intent(Profile3rdVisit.this,Profile4thVisit.class);
        startActivity(intent);
    }
    private void initViews(){
        visit1st= (Button) findViewById(R.id.btn_frst);
        visit2nd= (Button) findViewById(R.id.btn_scnd);
        visit3rd= (Button) findViewById(R.id.btn_third);
        visit4th= (Button) findViewById(R.id.btn_fourth);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<ProfileList> androidVersions = prepareData();
        adapter = new ProfileAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(Profile3rdVisit.this, PreferenceManager.getCity(), Toast.LENGTH_SHORT).show();
                    startAlarm(true,true);
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private ArrayList<ProfileList> prepareData() {
        for(int i=0;i<test_name.length;i++){
            ProfileList profile = new ProfileList();
            profile.setTest(test_name[i]);
            profile.setId(categories_imgId[i]);

            profileLists.add(i,profile);
        }
        return profileLists;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        Toast.makeText(Profile3rdVisit.this, "ye chala", Toast.LENGTH_SHORT).show();
        myIntent = new Intent(Profile3rdVisit.this,AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);


        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis()+3000,pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis()+3000,60*1000,pendingIntent);
    }
}
