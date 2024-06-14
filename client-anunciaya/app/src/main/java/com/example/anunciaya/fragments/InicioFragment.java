package com.example.anunciaya.fragments;
/**
 * @Description Esto es el Fragmento principal encargado de mostrar los Anuncios en la primera pagina
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 1.0
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anunciaya.R;
import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.adapter.ListAnuncios;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;

import java.util.ArrayList;
import java.util.List;
/*Clase principal InicioFragment*/

public class InicioFragment extends Fragment {
    /*Atributos de la Clase*/
    private AutoCompleteTextView actvUbicacion;
    private TextView tvNoAnuncios;
    private SearchView searchView;
    private Spinner spinner;
    private View view;
    private ListAdapter listAdapter;
    private int idUsuario;
    private String categoSelect;
    private Metodos m;

    /**
     * Constructor Principal
     */
    public InicioFragment() {}

    /**
     * Metodo que se lanza al abrir el fragment del Home es como un (Init)
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        initComponents();
        searchView.clearFocus();
        autocompletarUbicacion();
        tvNoAnuncios.setVisibility(View.GONE);

        /*
         * Evento que controla el texto introducido en el SearchView
         * para realizar un filtrado de anuncios por título
         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String txtMinuscula = newText.toLowerCase(); // Convertir texto a minúsculas
                listAdapter.filter(txtMinuscula); // Aplicar el filtro con el texto en minúsculas
                return false;
            }
        });

        /*
         * Evento que controla el spinner dependiendo de la categoria
         * seleccionada se muestran los anuncios que contienen dicha categoria
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoSelect = (String) parent.getItemAtPosition(position);
                actvUbicacion.setText("");
                actvUbicacion.clearFocus();
                if (categoSelect.equals("None")) {
                    listAdapter.updateData(m.getAnunciosInicio(new String[]{String.valueOf(idUsuario)}));
                    updateNoAnuncios();
                } else {
                    int idCategoria = m.getCategoriaId(new String[]{categoSelect});
                    listAdapter.updateData(m.getAnunciosIdCategoria(new String[]{String.valueOf(idCategoria), String.valueOf(idUsuario)}));
                    updateNoAnuncios();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        /*
         * Evento que controla el AutoCompleteTextView dependiendo de la ubicación
         *  seleccionada filtra anuncios
         */
        actvUbicacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textoSeleccionado = actvUbicacion.getText().toString().trim();

                // Si el texto está vacío, muestra los anuncios actuales
                if (textoSeleccionado.isEmpty()) {
                    actualizarLista();
                }
                // Hay una categoria seleccionada para filtrarla también por ubicación
                String ubiSeleccionada = (String) parent.getItemAtPosition(position);
                if(!categoSelect.equals("None")){
                    // Si se selecciona un elemento de la lista, filtrar los anuncios por el elemento seleccionado
                    int idCategoria = m.getCategoriaId(new String[]{categoSelect});
                    List<ListAnuncios> anuncios = m.getAnunciosUbicacionCategoria(new String[]{ubiSeleccionada,
                            String.valueOf(idCategoria), String.valueOf(idUsuario)});

                    // Verifica si hay anuncios para la ubicación seleccionada
                    if (anuncios != null && !anuncios.isEmpty()) {
                        listAdapter.updateData(anuncios);
                    } else {listAdapter.clearData();}
                    // Actualiza el TextView indicando si hay o no anuncios
                } else {
                    // Si se selecciona un elemento de la lista, filtrar los anuncios por el elemento seleccionado
                    List<ListAnuncios> anuncios = m.getAnunciosUbicacion(new String[]{ubiSeleccionada, String.valueOf(idUsuario)});

                    // Verifica si hay anuncios para la ubicación seleccionada
                    if (anuncios != null && !anuncios.isEmpty()) {
                        listAdapter.updateData(anuncios);
                    } else {listAdapter.clearData();}
                    // Actualiza el TextView indicando si hay o no anuncios
                }
                updateNoAnuncios();
            }
        });

        /*
         * Evento que se ejecuta cuando pierde el foco el AutoCompleteTextView
         * para que cuando se elimine el nombre de una ubicación se muestren anuncios
         * filtrados por la categoria seleccionada
         */
        actvUbicacion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Si el campo pierde el foco (es decir, el usuario sale del campo de texto)
                    String texto = actvUbicacion.getText().toString().trim();
                    if (texto.isEmpty()) {
                        // Si la categoria es None (ninguna) se mostrarán todos los anuncios
                        if(!categoSelect.equals("None")){
                            int idCategoria = m.getCategoriaId(new String[]{categoSelect});
                            listAdapter.updateData(m.getAnunciosIdCategoria(
                                    new String[]{String.valueOf(idCategoria),
                                            String.valueOf(idUsuario)}));
                            updateNoAnuncios();
                            // Si no se mostrarán los anuncios filtrados por la categoria seleccionada
                        } else{
                            actualizarLista();
                            updateNoAnuncios();
                        }
                    }
                }
            }
        });

        return view; // Devolver la vista inflada correctamente
    }

    /**
     * Método que se ejecuta cuando se vuelve al Fragment Inicio
     * para actualizar la lista de anuncios
     */
    @Override
    public void onResume() {
        super.onResume();
        actualizarLista();
        spinner.setSelection(m.getCategorias().indexOf("None"));
        actvUbicacion.setText("");
        actvUbicacion.clearFocus();
    }

    /**
     * Método que actualiza la lista de anuncios actuales y los muestra
     */
    private void actualizarLista() {
        List<ListAnuncios> nuevaLista = m.getAnunciosInicio(new String[]{Integer.toString(idUsuario)});
        listAdapter.updateData(nuevaLista);
    }

    /**
     * Método que muestra o oculta el texto de "no hay anuncios con
     * la categoria seleccionada" dependiendo del tamaño del recycler view
     */
    private void updateNoAnuncios() {
        if (listAdapter.getItemCount() == 0) {
            tvNoAnuncios.setVisibility(View.VISIBLE);
        } else {
            tvNoAnuncios.setVisibility(View.GONE);
        }
    }

    /**
     * Método que inicializa los componentes del fragment
     */
    private void initComponents() {
        m = new Metodos(); // Inicializar clase Metodos

        actvUbicacion = view.findViewById(R.id.actvUbicacion);
        tvNoAnuncios = view.findViewById(R.id.tvNoAnunciosCategoria);
        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        spinner = view.findViewById(R.id.spCategorias);
        RecyclerView recyclerView = view.findViewById(R.id.rvAnunciosHome);

        Context context = requireActivity().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        BundleRecoverry almacen = new BundleRecoverry(sharedPreferences);
        idUsuario = almacen.recuperarInt("logginId"); // idUsuario logeado

        listAdapter = new ListAdapter(m.getAnunciosInicio(new String[]{Integer.toString(idUsuario)}),
                requireContext(),this, R.layout.list_anuncios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);

        // Crear y configurar el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.spinner_categorias_rojo3, m.getCategorias());
        adapter.setDropDownViewResource(R.layout.spinner_categorias_rojo3);

        // Asignar el adaptador al Spinner
        spinner.setAdapter(adapter);

        spinner.setSelection(m.getCategorias().indexOf("None")); // Selecciona por defecto la categoria ninguna
    }

    /**
     * Metodo que se encarga de Autocompletar la Ubicacion del Spinner
     */
    private void autocompletarUbicacion(){
        ServerComunication comunication = new ServerComunication();
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity().getApplicationContext(), R.layout.auto_municipios_rojo3, Municipios);
            adapter.setDropDownViewResource(R.layout.auto_municipios_rojo3);
            // android.R.layout.simple_spinner_dropdown_item
            actvUbicacion.setAdapter(adapter);
        }catch (Exception e){
            Log.i("Error_Ubicacion",e.toString());
        }
    }
}
