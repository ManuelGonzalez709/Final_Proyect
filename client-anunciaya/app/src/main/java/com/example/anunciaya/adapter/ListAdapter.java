package com.example.anunciaya.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.anunciaya.InfoAnuncio;
import com.example.anunciaya.R;
import com.example.anunciaya.tools.Anuncio;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.Usuario;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListAnuncios> mDatos;
    private final List<ListAnuncios> mDatosOriginal;
    private final LayoutInflater mInflater; // Describe de donde viene el layout
    private final Context context; // De que clase llamamos el adaptador

    public ListAdapter(List<ListAnuncios> listAnuncios, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mDatos = listAnuncios;
        this.mDatosOriginal = new ArrayList<>();
        mDatosOriginal.addAll(listAnuncios);
    }

    /**
     * Método para actualizar los anuncios del adaptador
     * @param newAnuncios Lista de ListAnuncios
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<ListAnuncios> newAnuncios) {
        if (newAnuncios != null) {
            // Limpiar la lista antes de agregar los nuevos anuncios
            this.mDatos.clear();
            // Agregar los nuevos anuncios a la lista existente
            this.mDatos.addAll(newAnuncios);
            // Notificar al adaptador que los datos han cambiado
            notifyDataSetChanged();
        }
    }

    /**
     * Método que elimina todos los anuncios del RecyclerView
     */
    public void clearData(){this.mDatos.clear();}

    /**
     * Método que obtiene una lista con todos los anuncios actuales
     * del recycler view
     *
     * @return List<ListAnuncios>
     */
    public List<ListAnuncios> getAllItems() {
        return mDatos;
    }

    /**
     * Método que realiza una busqueda filtrada de los anuncios actuales
     * del Recycler View filtrándolos por título
     * @param strSearch cadena filtrado
     */
    public void filter(String strSearch){
        if(strSearch.length() == 0){
            mDatos.clear();
            mDatos.addAll(mDatosOriginal);
        } else{
            List<ListAnuncios> collect =  mDatos.stream().filter(i -> i.getTitulo().toLowerCase().
                    contains(strSearch)).collect(Collectors.toList());
            mDatos.clear();
            mDatos.addAll(collect);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatos.size();
    } // Tamaño de elementos de la lista

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
                int idAnuncio = anuncio.getIdAnuncio(); // obtiene el id anuncio seleccionado

                // Abrir la actividad 'Anuncio' pasando el ID del anuncio
                Intent intent = new Intent(context, InfoAnuncio.class);
                intent.putExtra("idAnuncio", String.valueOf(idAnuncio));
                Anuncio a = m.getAnuncioId(new String[]{String.valueOf(idAnuncio)});
                intent.putExtra("a_fotos", a.getFotos());
                intent.putExtra("a_titulo", a.getTitulo());
                intent.putExtra("a_descripcion", a.getDescripcion());
                intent.putExtra("a_precio", a.getPrecio());
                intent.putExtra("a_ubicacion", a.getUbicacion());
                intent.putExtra("a_descripCategoria",
                        m.getCategoriaDescripcion(new String[]{String.valueOf(a.getidCategoria())}));
                intent.putExtra("a_estado", a.getEstado());

                Usuario u = m.getUsuarioDataId(new String[]{String.valueOf(a.getIdUsuario())});
                intent.putExtra("a_nombCompUsu", u.getNombre() + " " + u.getApellidos());

                context.startActivity(intent); // Inicia la actividad
            }
        });
    }

    // Nuevo Listado
    public void setItems(List<ListAnuncios> items) {
        mDatos = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageSlider iconImagen;
        TextView tvTitulo;
        TextView tvPrecio;
        TextView tvUbicacion;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            iconImagen = itemView.findViewById(R.id.isFotosAnuncioPrev);
        }

        void bindData(final ListAnuncios item) {
            ArrayList<SlideModel> imageList = new ArrayList<>();
            // Obtiene todas las fotos y con el separados ; usa la primera
            String[] fotos = item.getFoto().split(";");

            tvTitulo.setText(item.getTitulo());
            tvPrecio.setText(item.getPrecio());
            tvUbicacion.setText(item.getUbicacion());

            if (fotos != null && fotos.length > 0) {
                // Añadir solo la primera foto anuncio
                String primeraFoto = fotos[0];
                if (!primeraFoto.isEmpty()) {
                    imageList.add(new SlideModel(primeraFoto, ScaleTypes.CENTER_CROP));
                }
            }
            // Asignar la lista de imágenes al ImageSlider
            iconImagen.setImageList(imageList);
        }
    }
}

