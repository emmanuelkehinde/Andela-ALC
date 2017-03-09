package com.kehinde.alc.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kehinde.alc.R;
import com.kehinde.alc.activities.DevelopersDetailsActivity;
import com.kehinde.alc.data.Constants;
import com.kehinde.alc.fragments.DevelopersDetailsFragment;
import com.kehinde.alc.models.Developer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by kehinde on 2/7/17.
 */

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.TaskViewHolder>{

    private final FragmentActivity mContext;
    private final ArrayList<Developer> devList;

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        public TextView devUName;
        public CircleImageView devProfilePic;

        public TaskViewHolder(final View itemView) {
            super(itemView);

            devUName= (TextView) itemView.findViewById(R.id.devName);
            devProfilePic= (CircleImageView) itemView.findViewById(R.id.devImage);

        }

    }

    public DevAdapter(FragmentActivity mContext, ArrayList<Developer> devList) {
        this.mContext=mContext;
        this.devList=devList;

    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_developers_list,parent,false);

        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        final Developer developer=devList.get(position);
        holder.devUName.setText(developer.getLogin());

        Glide.with(mContext)
                .load(developer.getAvatar_url())
                .placeholder(R.drawable.profile_avatar)
                .into(holder.devProfilePic);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isTwoPane=false;
                if (mContext.findViewById(R.id.detailContainer)!=null){
                    isTwoPane=true;
                }

                if (isTwoPane){
                    LinearLayout linearLayout= (LinearLayout)mContext.findViewById(R.id.instruction);
                    if(linearLayout!=null){
                        linearLayout.setVisibility(View.GONE);
                    }

                    DevelopersDetailsFragment developersDetailsFragment=new DevelopersDetailsFragment();
                    developersDetailsFragment.setArguments(developer.toBundle());

                    mContext.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detailContainer,developersDetailsFragment)
                            .commit();
                }else {
                    Intent intent=new Intent(mContext, DevelopersDetailsActivity.class);
                    intent.putExtra(Constants.BUNDLE,developer.toBundle());
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return devList.size();
    }

}
