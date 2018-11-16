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