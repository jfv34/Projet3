package com.vincler.jf.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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