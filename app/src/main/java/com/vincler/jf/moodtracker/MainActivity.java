package com.vincler.jf.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static SharedPreferences preferences;
    private Gson gson;
    List<SaveMood> historicMood;

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    String dateToday = dateFormat.format(date);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();
        historicMood = new ArrayList<>();
        String currendMood = "";

        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("historicSharedPreference", MODE_PRIVATE);
        String historicMoodJson = preferences.getString("historicMoodJson", null);

        if (historicMoodJson != null) {
            Type listType = new TypeToken<ArrayList<SaveMood>>() {
            }.getType();
            historicMood = gson.fromJson(historicMoodJson, listType);
        }

        if (!historicMood.isEmpty() | historicMood.get(historicMood.size() - 1).date != dateToday) {
            currendMood = historicMood.get(historicMood.size() - 1).mood;
        }

        switch (currendMood) {
            case "Super_HappyFragment":
                switchFragment(Super_HappyFragment.newInstance());
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
            default:
                switchFragment(HappyFragment.newInstance());
                break;
        }
    }

    public void switchFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentMood, fragment);
        ft.commit();

        String currentComment = "";

        if (!historicMood.isEmpty() && historicMood.get(historicMood.size() - 1).date.equals(dateToday)) {
            currentComment = historicMood.get(historicMood.size() - 1).comment;
            historicMood.remove(historicMood.size() - 1);
        }

        historicMood.add(new SaveMood(fragment.getClass().getSimpleName(), dateToday, currentComment));
        preferences.edit().putString("historicMoodJson", gson.toJson(historicMood)).apply();

    }
}
