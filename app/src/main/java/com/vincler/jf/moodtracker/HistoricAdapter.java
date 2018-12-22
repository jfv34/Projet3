package com.vincler.jf.moodtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoricAdapter extends RecyclerView.Adapter {
    private List<SaveMood> historic;
    private Context context;

    public HistoricAdapter(Context context, List<SaveMood> historic) {
        this.context = context;
        this.historic = historic;
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
        Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            historicMoodTextView = itemView.findViewById(R.id.timestamp);
        }

        public void bind(final SaveMood data) {

            // color display
            int color;
            switch (data.mood) {
                case "Super_HappyFragment":
                    color = context.getResources().getColor(R.color.colorSuper_Happy);
                    break;
                case "HappyFragment":
                    color = context.getResources().getColor(R.color.colorHappy);
                    break;
                case "DisappointedFragment":
                    color = context.getResources().getColor(R.color.colorDisappointed);
                    break;
                case "SadFragment":
                    color = context.getResources().getColor(R.color.colorSad);
                    break;
                default:
                    color = context.getResources().getColor(R.color.colorNormal);
                    break;
            }

            itemView.setBackgroundColor(color);

            // Text display
            RelativeTimeTextView v = itemView.findViewById(R.id.timestamp);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = null;
            try {
                date = formatter.parse(data.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long mills = date != null ? date.getTime() : 0;
            v.setReferenceTime(mills);
            historicMoodTextView.setText(data.date);

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
