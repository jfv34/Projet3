package com.vincler.jf.moodtracker;

import android.support.v4.app.Fragment;

public class Super_HappyFragment extends MoodFragment {

    public static Super_HappyFragment newInstance() {

        return new Super_HappyFragment();
    }


    @Override
    public Fragment getDownFragment() {
        return null;
    }

    @Override
    public Fragment getUpFragment() {
        return HappyFragment.newInstance();
    }

    @Override
    public Integer getLayout() {
        return R.layout.super_happy;
    }
}