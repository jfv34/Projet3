package com.vincler.jf.moodtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Pair;
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
    private Gson gson;
    List<Pair<String, String>> comments;
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    String dateToday = dateFormat.format(date);

    public static CommentDialogFragment newInstance() {

        return new CommentDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        gson = new Gson();
        comments = new ArrayList<>();
       // preferences = getSharedPreferences("historicSharedPreference", MODE_PRIVATE);

        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.comment_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String commentsJson = preferences.getString("commentsJson", null);
        if (commentsJson != null) {
            Type listType = new TypeToken<ArrayList<Pair<String, String>>>() {
            }.getType();
            comments = gson.fromJson(commentsJson, listType);
        }

        builder.setView(alertDialogView);
        builder.setMessage(R.string.dialog_comment)
                .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = alertDialogView.findViewById(R.id.CommentEditText);
                        String currentComment = editText.getText().toString();

                        comments.add(new Pair(currentComment, dateToday));
                        preferences.edit().putString("comments", gson.toJson(comments)).apply();


                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}