package com.vincler.jf.moodtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoricAdapter extends RecyclerView.Adapter {
    private List<Pair<String, String>> historic;
    private Context context;


    public HistoricAdapter(Context context, List<Pair<String, String>> historic) {
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

        public void bind(Pair<String, String> data) {

            // color display
            int color;
            switch (data.first) {
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
            RelativeTimeTextView v = (RelativeTimeTextView) itemView.findViewById(R.id.timestamp);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = null;
            try {
                date = (Date) formatter.parse(data.second);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long mills = date != null ? date.getTime() : 0;
            v.setReferenceTime(mills);
            historicMoodTextView.setText(data.second);


        }

    }
}
