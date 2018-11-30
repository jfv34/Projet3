package com.vincler.jf.moodtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

public class HistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        RelativeLayout mainFragment = findViewById(R.id.main_fragment);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, HistoricFragment.newInstance());
        ft.commit();

        final RecyclerView rv = (RecyclerView) findViewById(R.id.historiclist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new HistoricAdapter());

    }
}

