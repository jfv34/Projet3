package com.vincler.jf.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
