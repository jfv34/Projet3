package com.vincler.jf.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

abstract class MoodFragment extends Fragment {

    public abstract Fragment getDownFragment();

    public abstract Fragment getUpFragment();

    public abstract Integer getLayout();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new SwipeListener() {


            @Override
            public boolean onSwipe(Direction direction) {
                Fragment downFragment = getDownFragment();
                if (direction == Direction.down && downFragment != null) {
                    ((MainActivity) getActivity()).switchFragment(downFragment);

                }
                Fragment upFragment = getUpFragment();
                if (direction == Direction.up && upFragment != null) {
                    ((MainActivity) getActivity()).switchFragment(upFragment);

                }
                return true;
            }
        });


        // Swip view:
        final View fragmentView = inflater.inflate(getLayout(), container, false);

        fragmentView.setOnTouchListener(new View.OnTouchListener() {
                                            @Override
                                            public boolean onTouch(View v, MotionEvent event) {
                                                gestureDetector.onTouchEvent(event);
                                                return true;
                                            }

                                        }
        );
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

                Intent historicActivity = new Intent(getActivity(), HistoricActivity.class);

                startActivity(historicActivity);
            }

        });

        return fragmentView;


    }


}