package com.vincler.jf.moodtracker;

import android.support.v4.app.Fragment;

public class SadFragment extends MoodFragment {

    public static SadFragment newInstance() {

        return new SadFragment();
    }


    @Override
    public Fragment getDownFragment() {
        return DisappointedFragment.newInstance();
    }

    @Override
    public Fragment getUpFragment() {
        return null;
    }

    @Override
    public Integer getLayout() {
        return R.layout.sad;
    }
}