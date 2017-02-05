package com.savages.embryo.embryo.Bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.savages.embryo.embryo.R;

import java.util.ArrayList;

/**
 * Created by Asus on 05-02-2017.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private ArrayList<TestLists> testListses;
    private Context context;

    public TestAdapter(Context context, ArrayList<TestLists> testListses) {
        this.context = context;
        this.testListses = testListses;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_test, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.test_txt.setText(testListses.get(i).getTest());
        viewHolder.test_discription.setText(testListses.get(i).getDiscription());


    }

    @Override
    public int getItemCount() {
        return testListses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView test_txt;
        TextView test_discription;
        public ViewHolder(View view) {
            super(view);

            test_txt = (TextView)view.findViewById(R.id.txtTest);
            test_discription = (TextView)view.findViewById(R.id.txtDiscription);

        }
    }
}
