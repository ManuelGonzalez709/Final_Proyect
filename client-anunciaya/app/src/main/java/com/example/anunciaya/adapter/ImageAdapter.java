package com.example.anunciaya.adapter;
/**
 * @Description Esto es una clase usada para las Imagenes del RecyclerView
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 1.0
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anunciaya.R;

import java.util.ArrayList;

/**
 * Clase Principal
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    /*Atributos de la clase*/
    Context context;
    ArrayList<String> arrayList;
    OnItemClickListener onItemClickListener;

    /**
     * Constructor principal de la Clase
     * @param context recibe el contexto de la Aplicacion
     * @param arrayList recibe un Array de Fotos
     */
    public ImageAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    /**
     * Es el primer metodo que se ejecuta al iniciar el Recycler
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return retorna la ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carrousel_img,parent,false);
        return new ViewHolder(view);
    }

    /**
     * Esto recibe el onclick de las Fotos
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(arrayList.get(position)).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(holder.imageView,arrayList.get(position));
            }
        });
    }

    /**
     * Es un metodo que se encarga de Retornar el numero de elementos de la Lista
     * @return retorna un Entero
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    /*Clase Secundaria*/
    public static class ViewHolder extends RecyclerView.ViewHolder{
        /*Atributos de la clase*/
        ImageView imageView;

        /**
         * Constructor principal de la clase
         * @param itemView contiene el ItemView
         */
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_img);
        }
    }

    /**
     * Metodo que captura el CLick en la Imagen
     * @param onItemClickListener contiene el evento del click
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Es una interfaz del oncliclk
     */
    public interface OnItemClickListener{
        void onClick(ImageView imageView,String url);
    }
}
