package com.vincler.jf.moodtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HistoricAdapter extends RecyclerView.Adapter {
    private List<SaveMood> historic;
    private Context context;
    private int cellHeight;

    public HistoricAdapter(Context context, List<SaveMood> historic, int cellHeight) {
        this.context = context;
        this.historic = historic;
        this.cellHeight = cellHeight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_historic, viewGroup, false);
        return new ViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder) viewHolder).bind(historic.get(i));
    }

    @Override
    public int getItemCount() {
        return historic.size() > 7 ? 7 : historic.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView historicMoodTextView;
        ConstraintLayout background;
        ConstraintLayout container;
        Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            historicMoodTextView = itemView.findViewById(R.id.timestamp);
            background = itemView.findViewById(R.id.background);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final SaveMood data) {

            // color display
            int color;
            float width;
            switch (data.mood) {
                case "Super_HappyFragment":
                    color = context.getResources().getColor(R.color.colorSuper_Happy);
                    width = 0.9999f;
                    break;
                case "HappyFragment":
                    color = context.getResources().getColor(R.color.colorHappy);
                    width = 0.84f;
                    break;
                case "DisappointedFragment":
                    color = context.getResources().getColor(R.color.colorDisappointed);
                    width = 0.51f;
                    break;
                case "SadFragment":
                    color = context.getResources().getColor(R.color.colorSad);
                    width = 0.35f;
                    break;
                default:
                    color = context.getResources().getColor(R.color.colorNormal);
                    width = 0.68f;
                    break;
            }
            background.setBackgroundColor(color);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(container);
            constraintSet.constrainPercentWidth(background.getId(), width);
            constraintSet.applyTo(container);

            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.height = cellHeight;
            itemView.setLayoutParams(params);

            // Text display

            String timestampText[] = {"Il y a une semaine", "Il y a six jours", "Il y a cinq jours",
                    "Il y a quatre jours", "Il y a trois jours", "Avant-hier", "Hier"};

            TextView historicMoodTextView = itemView.findViewById(R.id.timestamp);
            historicMoodTextView.setText(timestampText[getAdapterPosition()]);

            // Comment button

            ImageButton commentButton = itemView.findViewById(R.id.historicCommentButton);
            if (data.comment.equals("")) {
                commentButton.setVisibility(View.INVISIBLE);
            } else {
                commentButton.setVisibility(View.VISIBLE);
            }
            commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, data.comment, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
