package com.vincler.jf.moodtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class CommentDialogFragment extends DialogFragment {

    private static SharedPreferences preferences;
    Gson gson;
    List<SaveMood> historic;
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    String dateToday = dateFormat.format(date);

    public static CommentDialogFragment newInstance() {

        return new CommentDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        gson = new Gson();
        historic = new ArrayList<>();
        preferences = getContext().getSharedPreferences("historicSharedPreference", MODE_PRIVATE);
        String historicMoodJson = preferences.getString("historicMoodJson", null);
        if (historicMoodJson != null) {
            Type listType = new TypeToken<ArrayList<SaveMood>>() {
            }.getType();
            historic = gson.fromJson(historicMoodJson, listType);
        }
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.comment_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(alertDialogView);
        builder.setMessage(R.string.dialog_comment)
                .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = alertDialogView.findViewById(R.id.CommentEditText);
                        String currentComment = editText.getText().toString();
                        SaveMood data = historic.get(historic.size() - 1);
                        historic.remove(historic.size() - 1);
                        historic.add(new SaveMood(data.mood, data.date, currentComment));
                        preferences.edit().putString("historicMoodJson", gson.toJson(historic)).apply();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}