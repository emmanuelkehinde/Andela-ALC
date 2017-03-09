package com.kehinde.alc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kehinde.alc.R;

public class DevelopersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers_list);

        Toolbar detToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(detToolbar);
    }
}
