package com.vincler.jf.moodtracker;

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


    public HistoricAdapter(List<Pair<String, String>> historic) {
        this.historic = historic;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_historic, viewGroup, false);
        return new ViewHolder(view);
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historicMoodTextView = itemView.findViewById(R.id.tv_historic_mood);
        }

        public void bind(Pair<String, String> data) {

            historicMoodTextView.setText(data.second);

            //
            // Date todayDate = new SimpleDateFormat("yyyy/MM/dd", Locale.US).parse(historic.get(historic.size() - 1).second);
            // Date dataDate = new SimpleDateFormat("yyyy/MM/dd", Locale.US).parse(data.second);
            // Period period = Period.between(todayDate, dataDate);


        }

    }
}
