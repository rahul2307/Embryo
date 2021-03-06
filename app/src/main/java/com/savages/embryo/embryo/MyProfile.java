package com.savages.embryo.embryo;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import com.savages.embryo.embryo.Adapter.AlarmNotificationReciever;
import com.savages.embryo.embryo.Adapter.ProfileAdapter;
import com.savages.embryo.embryo.Adapter.ProfileList;
import com.savages.embryo.embryo.Bean.PreferenceManager;
import com.savages.embryo.embryo.TestActivity.FirstVisitActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MyProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private TextView navUser;
    private Button visit1st;
    private Button visit2nd;
    private Button visit3rd;
    private Button visit4th;
    private Button call;



    private static String LOG_TAG = "CardViewActivity";
    private com.savages.embryo.embryo.Bean.PreferenceManager PreferenceManager;
    ArrayList<ProfileList> profileLists = new ArrayList<>();

    private final String test_name[] = {
            "Tests And Vaccines"


    };
    private final int categories_imgId[] = {

            R.drawable.download

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PreferenceManager = new PreferenceManager(this);
        call= (Button) findViewById(R.id.btn_call);
        visit1st = (Button) findViewById(R.id.btn_frst);
        visit2nd = (Button) findViewById(R.id.btn_scnd);
        visit3rd = (Button) findViewById(R.id.btn_third);
        visit4th = (Button) findViewById(R.id.btn_fourth);
        initViews();
      /*  visit1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });*/
        visit2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile2ndVisit();
            }
        });
        visit3rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile3rdVisit();
            }
        });
        visit4th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile4thVisit();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navUser = (TextView) hView.findViewById(R.id.nav_user);
        navUser.setText("Dear" + PreferenceManager.getKeyName());
    }

    private void call() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:8602534424"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("helloandroid dialing example", "Call failed", e);
        }
    }
    private void goToProfile2ndVisit() {
        Intent intent = new Intent(MyProfile.this,Profile2ndVisit.class);
        startActivity(intent);
    }
    private void goToProfile3rdVisit() {
        Intent intent = new Intent(MyProfile.this,Profile3rdVisit.class);
        startActivity(intent);
    }
    private void goToProfile4thVisit() {
        Intent intent = new Intent(MyProfile.this,Profile4thVisit.class);
        startActivity(intent);
    }

    private void initViews(){


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
                    //Toast.makeText(MyProfile.this, PreferenceManager.getCity(), Toast.LENGTH_SHORT).show();
                    startAlarm(true,true);
                    goToFirstVisitTest();
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

    private void goToFirstVisitTest() {
        Intent intent = new Intent(MyProfile.this,FirstVisitActivity.class);
        startActivity(intent);
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

        //Toast.makeText(MyProfile.this, "ye chala", Toast.LENGTH_SHORT).show();
            myIntent = new Intent(MyProfile.this, AlarmNotificationReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);


        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis()+3000,pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis()+3000,60*1000,pendingIntent);
    }
}
