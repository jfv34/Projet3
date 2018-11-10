package com.vincler.jf.moodtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class CommentDialogFragment extends DialogFragment {

    public static CommentDialogFragment newInstance() {

        return new CommentDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.comment_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(alertDialogView);
        builder.setMessage(R.string.dialog_comment)
                .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = alertDialogView.findViewById(R.id.CommentEditText);
                        String comment = editText.getText().toString();
                        Log.i("comment", comment);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}