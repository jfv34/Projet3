package com.vincler.jf.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

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
    List<Pair<String, String>> historicMood;
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    String dateString = dateFormat.format(date);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();
        historicMood = new ArrayList<>();
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("historicSharedPreference", MODE_PRIVATE);


        String historicMoodJson = preferences.getString("historicMoodJson", null);
        if (historicMoodJson != null) {
            Type listType = new TypeToken<ArrayList<Pair<String, String>>>() {
            }.getType();
            historicMood = gson.fromJson(historicMoodJson, listType);
        }

        String currendMood = "NormalFragment";
        if (!historicMood.isEmpty()) {
            currendMood = historicMood.get(historicMood.size() - 1).first;
        }

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

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentMood, fragment);
        ft.commit();


        dateString = dateFormat.format(date);
        if (!historicMood.isEmpty() && historicMood.get(historicMood.size() - 1).second.equals(dateString)) {
            historicMood.remove(historicMood.size() - 1);
        }

        historicMood.add(new Pair(fragment.getClass().getSimpleName(), dateString));
        preferences.edit().putString("historicMoodJson", gson.toJson(historicMood)).apply();


    }
}
