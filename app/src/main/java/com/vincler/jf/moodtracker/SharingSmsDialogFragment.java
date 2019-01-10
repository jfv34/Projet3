package com.vincler.jf.moodtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SharingSmsDialogFragment extends DialogFragment {

    public static SharedPreferences preferences;
    private Gson gson;
    List<SaveMood> historicMood;

    public static SharingSmsDialogFragment newInstance() {

        return new SharingSmsDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.sharing_sms_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(alertDialogView);
        builder.setMessage(R.string.dialog_sharing_sms)
                .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText editText = alertDialogView.findViewById(R.id.SharingSmsEditText);
                        String phone = editText.getText().toString();
                        sendingMessage(phone);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

    private void sendingMessage(String phone) {

        if (phone.length() == 10) {
            String message = createMessage();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
            intent.putExtra("sms_body", message);
            startActivity(intent);
        } else {
            toast("Phone number error");
        }
    }

    private String createMessage() {
        preferences = getActivity().getSharedPreferences("historicSharedPreference", MODE_PRIVATE);
        gson = new Gson();
        historicMood = new ArrayList<>();
        String historicMoodJson = preferences.getString("historicMoodJson", null);
        if (historicMoodJson != null) {
            Type listType = new TypeToken<ArrayList<SaveMood>>() {
            }.getType();
            historicMood = gson.fromJson(historicMoodJson, listType);
        }
        String currentMood = historicMood.get(historicMood.size() - 1).mood;
        if (currentMood != null) {
            String mood = currentMood.replaceFirst("Fragment", "");
            String message = "Hello. Today, my mood is " + mood;
            return message;
        }
        return "";
    }

    private void toast(String toastMessage) {
        Toast.makeText(getActivity(),
                toastMessage, Toast.LENGTH_LONG).show();
    }
}