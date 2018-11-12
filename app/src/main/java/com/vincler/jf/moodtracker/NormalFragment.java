package com.vincler.jf.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class NormalFragment extends Fragment {

    public static NormalFragment newInstance() {

        return new NormalFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new SwipeListener() {

            @Override
            public boolean onSwipe(Direction direction) {
                if (direction == Direction.up) {
                    ((MainActivity) getActivity()).switchFragment(DisappointedFragment.newInstance());

                }

                if (direction == Direction.down) {
                    ((MainActivity) getActivity()).switchFragment(HappyFragment.newInstance());

                }
                return true;
            }
        });

        // Swip view:
        final View fragmentView = inflater.inflate(R.layout.normal, container, false);

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