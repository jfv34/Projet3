package com.vincler.jf.moodtracker;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content,SadFragment.newInstance(0,""));
        ft.commit();

        FragmentTransaction ft2= getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.content2,HappyFragment.newInstance(0,""));
        ft2.commit();
    }

}

