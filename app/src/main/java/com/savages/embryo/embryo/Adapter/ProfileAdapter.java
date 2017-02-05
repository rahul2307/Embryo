package com.savages.embryo.embryo.Adapter;

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
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private ArrayList<ProfileList> profileLists;
    private Context context;

    public ProfileAdapter(Context context, ArrayList<ProfileList> profileLists) {
        this.context = context;
        this.profileLists = profileLists;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.test_txt.setText(profileLists.get(i).getTest());
        viewHolder.testimg.setImageResource(profileLists.get(i).getId());

    }

    @Override
    public int getItemCount() {
        return profileLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView test_txt;
        ImageView testimg;
        public ViewHolder(View view) {
            super(view);

            test_txt = (TextView)view.findViewById(R.id.TxtViewCat);
            testimg = (ImageView)view.findViewById(R.id.ImgViewCat);
        }
    }
}
