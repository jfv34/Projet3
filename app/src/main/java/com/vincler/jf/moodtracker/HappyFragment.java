package com.vincler.jf.moodtracker;

import android.support.v4.app.Fragment;

public class HappyFragment extends MoodFragment {

    public static HappyFragment newInstance() {

        return new HappyFragment();
    }

    @Override
    public Fragment getDownFragment() {
        return Super_HappyFragment.newInstance();
    }

    @Override
    public Fragment getUpFragment() {
        return NormalFragment.newInstance();
    }

    @Override
    public Integer getLayout() {
        return R.layout.happy;
    }
}