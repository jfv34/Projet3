package com.vincler.jf.moodtracker;

import android.support.v4.app.Fragment;

public class NormalFragment extends MoodFragment {

    public static NormalFragment newInstance() {

        return new NormalFragment();
    }


    @Override
    public Fragment getDownFragment() {
        return HappyFragment.newInstance();
    }

    @Override
    public Fragment getUpFragment() {
        return DisappointedFragment.newInstance();
    }

    @Override
    public Integer getLayout() {
        return R.layout.normal;
    }
}
