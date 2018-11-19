package com.vincler.jf.moodtracker;

import android.support.v4.app.Fragment;

public class DisappointedFragment extends MoodFragment {

    public static DisappointedFragment newInstance() {

        return new DisappointedFragment();
    }


    @Override
    public Fragment getDownFragment() {
        return NormalFragment.newInstance();
    }

    @Override
    public Fragment getUpFragment() {
        return SadFragment.newInstance();
    }

    @Override
    public Integer getLayout() {
        return R.layout.disappointed;
    }
}