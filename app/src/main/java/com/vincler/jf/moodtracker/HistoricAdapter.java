package com.vincler.jf.moodtracker;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return new ViewHolder(context,view);
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
            this.context=context;
            historicMoodTextView = itemView.findViewById(R.id.tv_historic_mood);
        }

        public void bind(Pair<String, String> data) {

            int color;
            switch (data.first) {
                case "Super_HappyFragment":
                    color=context.getResources().getColor(R.color.colorSuper_Happy);
                case "HappyFragment":
                    color=context.getResources().getColor(R.color.colorHappy);
                    break;
                case "DisappointedFragment":
                    color=context.getResources().getColor(R.color.colorDisappointed);
                    break;
                case "SadFragment":
                    color=context.getResources().getColor(R.color.colorSad);
                    break;
                default:
                    color=context.getResources().getColor(R.color.colorNormal);
                    break;
            }

            itemView.setBackgroundColor(color);
            historicMoodTextView.setText(data.second);

            //
            // Date todayDate = new SimpleDateFormat("yyyy/MM/dd", Locale.US).parse(historic.get(historic.size() - 1).second);
            // Date dataDate = new SimpleDateFormat("yyyy/MM/dd", Locale.US).parse(data.second);
            // Period period = Period.between(todayDate, dataDate);


        }

    }
}
