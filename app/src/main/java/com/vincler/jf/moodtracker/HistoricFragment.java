package com.vincler.jf.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class HistoricFragment extends Fragment {

    public static SharedPreferences preferences;
    List<SaveMood> historicMood;
    List<SaveMood> historicMoodWithoutToday;
    List<SaveMood> historicMoodCompleted;
    List<SaveMood> historicMoodDisplayed;

    public static HistoricFragment newInstance() {
        Bundle args = new Bundle();
        HistoricFragment fragment = new HistoricFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_historic, container, false);
        preferences = getActivity().getSharedPreferences("historicSharedPreference", MODE_PRIVATE);
        Gson gson = new Gson();
        historicMood = new ArrayList<>();
        String historicMoodJson = preferences.getString("historicMoodJson", null);
        if (historicMoodJson != null) {
            Type listType = new TypeToken<ArrayList<SaveMood>>() {
            }.getType();
            historicMood = gson.fromJson(historicMoodJson, listType);
        }


        // Don't show mood of the day in historic
        historicMoodWithoutToday = new ArrayList<>();
        for (int i = 0; i < historicMood.size() - 1; i++) {
            historicMoodWithoutToday.add(i, historicMood.get(i));
        }

        // Adding dates without connections in the historic
        historicMoodCompleted = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 7; i++) {

            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date d = calendar.getTime();
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            String dateBack = df.format(d);
            boolean dateFound = false;
            for (int j = 0; j < historicMoodWithoutToday.size(); j++) {
                if (dateBack.equals(historicMoodWithoutToday.get(j).date)) {
                    historicMoodCompleted.add(i, historicMoodWithoutToday.get(j));
                    dateFound = true;
                }
            }
            if (!dateFound) {
                historicMoodCompleted.add(i, new SaveMood("HappyFragment", dateBack, ""));
            }
        }


        final RecyclerView rv = fragmentView.findViewById(R.id.historiclist);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        final ViewTreeObserver observer = rv.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int cellHeight = rv.getHeight() / 7;
                        rv.setAdapter(new HistoricAdapter(getContext(), historicMoodCompleted, cellHeight));
                        rv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

        return fragmentView;
    }
}
