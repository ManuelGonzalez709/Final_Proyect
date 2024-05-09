package com.example.anunciaya.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.anunciaya.InfoAnuncio;
import com.example.anunciaya.R;
import com.example.anunciaya.tools.Anuncio;
import com.example.anunciaya.tools.Metodos;

import androidx.annotation.NonNull;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListAnuncios> mDatos;
    private LayoutInflater mInflater; // Describe de donde viene el layout
    private Context context; // De que clase llamamos el adaptador

    public ListAdapter(List<ListAnuncios> listAnuncios, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mDatos = listAnuncios;
    }

    @Override
    public int getItemCount() {return mDatos.size();} // Tamaño de elementos de la lista

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_anuncios, null); // Asignamos el layout del RecyclerView
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        ListAnuncios anuncio = mDatos.get(position);
        holder.bindData(anuncio);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos m = new Metodos();
                int idAnuncio = anuncio.getIdAnuncio();

                // Abrir la actividad 'Anuncio' pasando el ID del anuncio
                Intent intent = new Intent(context, InfoAnuncio.class);
                intent.putExtra("idAnuncio", idAnuncio);
                Anuncio a = m.getAnuncioId(new String[]{String.valueOf(idAnuncio)});
                intent.putExtra("a_fotos", a.getFotos());
                intent.putExtra("a_titulo", a.getTitulo());
                intent.putExtra("a_descripcion", a.getDescripcion());
                intent.putExtra("a_precio", a.getPrecio());
                intent.putExtra("a_ubicacion", a.getUbicacion());

                context.startActivity(intent); // inicia la actividad
            }
        });
    }

    // Nuevo Listado
    public void setItems(List<ListAnuncios> items){mDatos = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImagen;
        TextView tvTitulo;
        TextView tvPrecio;
        TextView tvDivisa;
        TextView tvUbicacion;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvDivisa = itemView.findViewById(R.id.tvDivisa);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            iconImagen = itemView.findViewById(R.id.ivAnuncio);
        }

        void bindData(final ListAnuncios item) {
            tvTitulo.setText(item.getTitulo());
            tvPrecio.setText(item.getPrecio());
            tvUbicacion.setText(item.getUbicacion());

            String[]fotos = item.getFoto().split(";");

            // Cargar la imagen desde la URL usando Glide
            Glide.with(context)
                    .load(fotos[0])
                    .into(iconImagen);
        }

    }
}

