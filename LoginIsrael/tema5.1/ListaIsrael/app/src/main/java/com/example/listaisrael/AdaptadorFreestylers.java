package com.example.listaisrael;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaisrael.Freestyler;
import com.example.listaisrael.R;

import java.util.ArrayList;

public class AdaptadorFreestylers
        extends RecyclerView.Adapter<AdaptadorFreestylers.ViewHolder> {

    // La lista de datos que va a pintar
    private final ArrayList<Freestyler> freestylers;

    public AdaptadorFreestylers(ArrayList<Freestyler> freestylers) {
        this.freestylers = freestylers;
    }

    // El ViewHolder guarda las vistas de UNA fila
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo;
        TextView tvSubtitulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_freestyler, parent, false);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Freestyler freestyler = freestylers.get(position);
        holder.tvTitulo.setText(freestyler.getNombre());
        holder.tvSubtitulo.setText(freestyler.getResultado());
        holder.ivImagen.setImageResource(freestyler.getImagen());
    }

    @Override
    public int getItemCount() {
        return freestylers.size();
    }
}