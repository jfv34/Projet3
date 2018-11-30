package com.vincler.jf.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    SharedPreferences preferences;
    Gson gson = new Gson();
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yy-DDD-hh", Locale.US);
    String dateString = dateFormat.format(date);
    HashMap historicMood = new HashMap();

    public String historicMoodJson = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getPreferences(MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        // get the historicMood;

        historicMoodJson = preferences.getString("historicMoodJson", "{}");

        JSONObject root = null;
        String currendMood = "NormalFragment";
        try {
            root = new JSONObject(historicMoodJson);
            currendMood = root.optString(dateString);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // displays the fragment of the last memorized mood

        switch (currendMood) {
            case "Super_HappyFragment":
                switchFragment(Super_HappyFragment.newInstance());
                break;
            case "HappyFragment":
                switchFragment(HappyFragment.newInstance());
                break;
            case "DisappointedFragment":
                switchFragment(DisappointedFragment.newInstance());
                break;
            case "SadFragment":
                switchFragment(SadFragment.newInstance());
                break;
            default:
                switchFragment(NormalFragment.newInstance());
                break;

        }
    }


    public void switchFragment(Fragment fragment) {
        // displays the fragment with the mood
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentMood, fragment);
        ft.commit();

        // memorize the current mood for the activity_historic (by dates)

        dateString = dateFormat.format(date);

        historicMood.put(dateString, fragment.getClass().getSimpleName());
        preferences.edit().putString("historicMoodJson", gson.toJson(historicMood)).apply();

    }
}

