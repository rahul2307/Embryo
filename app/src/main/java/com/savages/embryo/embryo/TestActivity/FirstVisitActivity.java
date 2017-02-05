package com.savages.embryo.embryo.TestActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.savages.embryo.embryo.Bean.TestAdapter;
import com.savages.embryo.embryo.Bean.TestLists;
import com.savages.embryo.embryo.R;

import java.util.ArrayList;

public class FirstVisitActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private static String LOG_TAG = "CardViewActivity";
    ArrayList<TestLists> testlists = new ArrayList<>();
    private final String testlists_tests[]={
            "UltraSound Test",
            "Blood test (PAPP-A) ",
            "General blood test to check-"



    };
    private final String testlists_discription[]={
            "To examine the area at the back of the fetal neck for increased fluid or thickening.",
            "Abnormality in its level, increases risk for chromosome abnormality",
            "Haemoglobin,Syphilis,HIV ,Blood group"



    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_visit);
        initViews();
    }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<TestLists> testlists = prepareData();
        adapter = new TestAdapter(getApplicationContext(),testlists);
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
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(getApplicationContext(),testlists.get(position).getTest(), Toast.LENGTH_SHORT).show();
                   /* switch (categories.get(position).getCategory()){
                        case "Book":{
                            goToRegisterBookAd();
                            break;
                        }
                        case "TV Series":{
                            goToRegisterTvSeriesAd();
                            break;
                        }
                        case "Movies":{
                            goToRegisterMovieAd();
                            break;
                        }
                        case "Games":{
                            goToRegisterGameAd();
                            break;
                        }
                    }*/

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
    private ArrayList<TestLists> prepareData() {
        for(int i=0;i<testlists_tests.length;i++){
           TestLists testLists = new TestLists();
            testLists.setTest(testlists_tests[i]);
          testLists.setDiscription(testlists_discription[i]);

            testlists.add(i,testLists);
        }
        return testlists;
    }
}
