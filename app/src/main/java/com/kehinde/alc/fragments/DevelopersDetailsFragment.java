package com.kehinde.alc.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kehinde.alc.R;
import com.kehinde.alc.models.Developer;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopersDetailsFragment extends Fragment {


    private TextView devDetUName;
    private TextView devDetProfileUrl;
    private CircleImageView circleImageView;
    private Button devShareBtn;
    Developer developer;

    public DevelopersDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView= inflater.inflate(R.layout.fragment_developers_details, container, false);

        Bundle b=getArguments();
        if (b!=null){
            developer=new Developer(b);
        }

        circleImageView= (CircleImageView)mView.findViewById(R.id.devDetImage);
        devDetUName=(TextView) mView.findViewById(R.id.devDetUName);
        devDetProfileUrl= (TextView) mView.findViewById(R.id.devDetProfileUrl);
        devShareBtn= (Button) mView.findViewById(R.id.devShareBtn);

        //Set Views
        devDetUName.setText(developer.getLogin());
        devDetProfileUrl.setText(developer.getHtml_url());
        Glide.with(this)
                .load(developer.getAvatar_url())
                .placeholder(R.drawable.profile_avatar)
                .into(circleImageView);


        //OnClick for the Profile Url TextView and the Button
        devDetProfileUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent urlIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(developer.getHtml_url()));
                urlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(urlIntent);
            }
        });

        devShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer "+"@"+developer.getLogin()+", "+developer.getHtml_url());
                sendIntent.setType("text/plain");
                getActivity().startActivity(Intent.createChooser(sendIntent, "Share Profile"));
            }
        });

        return mView;
    }

}
