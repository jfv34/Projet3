package com.vincler.jf.moodtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class HappyFragment extends Fragment {

    public static HappyFragment newInstance() {

        return new HappyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Swip view:

        final View fragmentView = inflater.inflate(R.layout.happy, container, false);

        fragmentView.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });

        // Comments:
        ImageButton commentButton = fragmentView.findViewById(R.id.commentButton);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDialogFragment.newInstance().show(getFragmentManager(), "");
            }
        });

        // Historic:

        ImageButton historicButton;
        historicButton = fragmentView.findViewById(R.id.historicButton);

        historicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("historicButton", "clicked");

                // MainActivity.switchFragment(HistoricFragment.newInstance());

            }

        });

        return fragmentView;

    }
}