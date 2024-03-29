package com.jgm.flixnet.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jgm.flixnet.R;
import com.jgm.flixnet.model.Serie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


//El adaptadpor gestiona la visualización de los elementos
public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesHolder> {

    private List<Serie> data;

    //índice sobre el array de elementos sobre el que se despliega el menú contextual
    private int index;

    private SeriesListenerInterface listenerInterface;
    //private View.OnClickListener listener;
    private Context contexto;

    public SeriesAdapter(Context ctx, SeriesListenerInterface sListenerInterface /*View.onClickListener sListener*/) {
        data = new ArrayList<Serie>();
        contexto = ctx;
        listenerInterface = sListenerInterface;
    }

    //seteamos y notificamos
    public void setData(List<Serie> sData) {
        data = sData;
        notifyDataSetChanged();
    }

    public int getIndex() {
        return this.index;
    }

    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(contexto)
                                   .inflate(R.layout.item_layout, parent, false);

        SeriesHolder sh = new SeriesHolder(vista);
        return sh;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesHolder holder, int position) {
        holder.BindHolder(data.get(position));
    }

    //Número de items de la serie
    @Override
    public int getItemCount() {
        return data.size();
    }


    //Clase del Holder
    //Permite almacenar el layout inflado de cada elemento de la lista
    public class SeriesHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView titulo;
        private ImageView poster;

        public SeriesHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.itemTitle);
            poster = itemView.findViewById(R.id.itemPoster);

            //Asociar el menú al objeto
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        //Aqui es donde se recogen los datos para la lista y se bindean a la vista
        public void BindHolder(Serie serie) {
            //Pulsación simple
            itemView.setOnClickListener(v -> listenerInterface.onSerieClickListener(serie));
            titulo.setText(serie.getTitulo());
            Picasso.get().load(serie.getCartel()).resize(300,444).into(poster);

            //Pulsación prolongada
            itemView.setOnLongClickListener( (view) ->{
                index = getAdapterPosition();
                return false;
            });

        }

    }

    public interface SeriesListenerInterface {
        public void onSerieClickListener(Serie item);
    }
}
