package com.example.anunciaya.adapter;
/**
 * @Description Esto es una clase que contiene el Adapter para el Recycler View
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.anunciaya.AnuncioUsuario;
import com.example.anunciaya.R;
import com.example.anunciaya.fragments.InicioFragment;
import com.example.anunciaya.fragments.UserFragment;
import com.example.anunciaya.tools.Anuncio;
import com.example.anunciaya.tools.InfoAnuncio;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.Usuario;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    /*Atributos de la clase*/
    private List<ListAnuncios> mDatos;
    private final List<ListAnuncios> mDatosOriginal;
    private final LayoutInflater mInflater; // Describe de donde viene el layout
    private final Context context; // De que clase llamamos el adaptador
    private Fragment fragment; // Fragment donde se ejecuta el recyclerview
    private int layoutResourceId; // Id del layout a usar en el recycler view

    /**
     * Esto es el Contructor principal de la clase
     * @param listAnuncios contiene una lista de tipo ListAnuncios
     * @param context esto contiene el Context
     * @param fragment esto contiene un Fragment
     * @param layoutResourceId esto contiene un Int
     */
    public ListAdapter(List<ListAnuncios> listAnuncios, Context context, Fragment fragment, int layoutResourceId) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.fragment = fragment;
        this.layoutResourceId = layoutResourceId;
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

    /**
     * Metodo que retorna el total del Elementos de la Lista
     * @return retorna un Int
     */
    @Override
    public int getItemCount() {
        return mDatos.size();
    } // Tamaño de elementos de la lista

    /**
     * Esto es un metodo que se encarga de cargar el Adapter por primera vez
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return retorna un ListAdapter
     */
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(layoutResourceId, parent, false); // Usar el nuevo layout
        return new ListAdapter.ViewHolder(view);
    }

    /**
     * Esto es un metodo que se encarga de capturar cuando hemos hecho click en un anuncio
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        ListAnuncios anuncio = mDatos.get(position);
        holder.bindData(anuncio);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos m = new Metodos();
                int idAnuncio = anuncio.getIdAnuncio(); // Obtiene el id anuncio seleccionado
                // Fragmento actual ejecutado
                Fragment currentFragment = fragment;

                // Si se ejecuta el recycler view desde InicioFragment
                if (currentFragment instanceof InicioFragment) {
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

                    context.startActivity(intent);

                    // Si se ejecuta el recycler view desde UserFragment
                } else if (currentFragment instanceof UserFragment) {
                    Intent intent = new Intent(context, AnuncioUsuario.class);

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

                    context.startActivity(intent);
                }
            }
        });
    }

    /**
     * Esto es una clase ViewHolder que se ayuda del ListAdapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /*Esto son los atributos principales de la clase*/
        ImageSlider iconImagen;
        TextView tvTitulo;
        TextView tvPrecio;
        TextView tvUbicacion;
        TextView tvDireccion;
        TextView tvCiudad;

        /**
         * Esto es el constructor principal de la clase
         * @param itemView contiene una Vista
         */
        ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            iconImagen = itemView.findViewById(R.id.isFotosAnuncioPrev);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvCiudad = itemView.findViewById(R.id.tvCiudad);
        }

        /**
         * Método que vincula los datos de un elemento de la lista con las vistas
         * correspondientes en el ViewHolder. Se encarga de configurar las vistas
         * según los datos del elemento y el layout utilizado en el RecyclerView.
         * @param item Objeto ListAnuncios que contiene los datos del elemento.
         */
        void bindData(final ListAnuncios item) {
            ArrayList<SlideModel> imageList = new ArrayList<>();
            // Obtiene todas las fotos y con el separados ; usa la primera
            String[] fotos = item.getFoto().split(";");

            tvTitulo.setText(item.getTitulo());
            tvPrecio.setText(item.getPrecio());

            if(layoutResourceId == R.layout.list_anuncios){tvUbicacion.setText(item.getUbicacion());}
            if(layoutResourceId == R.layout.list_pedidos){
                tvDireccion.setText(item.getDireccion());
                tvCiudad.setText(item.getCiudad());
            }

            if (fotos != null && fotos.length > 0) {
                for (String foto : fotos) {
                    if (!foto.isEmpty()) {
                        imageList.add(new SlideModel(foto, ScaleTypes.CENTER_CROP));
                    }
                }
            }

            // Asignar la lista de imágenes al ImageSlider
            iconImagen.setImageList(imageList);
        }
    }
}

