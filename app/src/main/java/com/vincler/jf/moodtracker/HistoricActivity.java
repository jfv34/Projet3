package com.vincler.jf.moodtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class HistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentMood, HappyFragment.newInstance());
        ft.commit();
    }
}

