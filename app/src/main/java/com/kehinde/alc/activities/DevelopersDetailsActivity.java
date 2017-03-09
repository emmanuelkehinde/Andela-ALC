package com.kehinde.alc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kehinde.alc.R;
import com.kehinde.alc.data.Constants;
import com.kehinde.alc.fragments.DevelopersDetailsFragment;
import com.kehinde.alc.fragments.DevelopersListFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class DevelopersDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers_details);

        Toolbar detToolbar= (Toolbar) findViewById(R.id.detToolbar);
        setSupportActionBar(detToolbar);

        DevelopersDetailsFragment developersDetailsFragment=new DevelopersDetailsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.detailContainer,developersDetailsFragment)
                .commit();

        Bundle bundle=getIntent().getBundleExtra(Constants.BUNDLE);
        developersDetailsFragment.setArguments(bundle);

    }
}
