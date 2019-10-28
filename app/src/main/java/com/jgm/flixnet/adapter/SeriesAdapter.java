package com.jgm.flixnet.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jgm.flixnet.model.Serie;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesHolder> {

    private List<Serie> data;

    public SeriesAdapter(List<Serie> list) {
        data = list;
    }

    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesHolder holder, int position) {

    }

    //Número de items de la serie
    @Override
    public int getItemCount() {
        return data.size();
    }


    //Clase del Holder
    public class SeriesHolder extends RecyclerView.ViewHolder {

        public SeriesHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
