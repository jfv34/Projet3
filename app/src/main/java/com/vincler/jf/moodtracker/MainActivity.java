package com.vincler.jf.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the last memorized mood (or by default: normal mood)
        preferences = getPreferences(MODE_PRIVATE);


        setContentView(R.layout.activity_main);

        // displays the fragment of the last memorized mood
        switch (preferences.getString("currentMood", "NormalFragment")){
            case "Super_HappyFragment":
                switchFragment(Super_HappyFragment.newInstance());
                break;
            case "HappyFragment":
                switchFragment(HappyFragment.newInstance());
                break;
            case "NormalFragment":
                switchFragment(NormalFragment.newInstance());
                break;
            case "DisappointedFragment":
                switchFragment(DisappointedFragment.newInstance());
                break;
            case "SadFragment":
                switchFragment(SadFragment.newInstance());
                break;

        }
    }


    public void switchFragment(Fragment fragment) {
        // displays the fragment with the mood
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentMood, fragment);
        ft.commit();

        // memorize the current mood
        preferences.edit().putString("currentMood", fragment.getClass().getSimpleName()).apply();
    }
}

