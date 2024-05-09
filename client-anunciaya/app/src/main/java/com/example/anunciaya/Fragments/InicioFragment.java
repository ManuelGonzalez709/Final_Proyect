package com.example.anunciaya.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.anunciaya.R;
import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;

public class InicioFragment extends Fragment {
    private Spinner spinner;
    private View view;
    private BundleRecoverry Almacen;
    private int idUsuario;

    public InicioFragment() {}

    /**
     * Metodo que se encarga de devolver la vista del fragment y llama a initComponets()
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
        return view; // Devolver la vista inflada correctamente
    }

    /**
     * Metodo que se encarga de Iniciar los componentes principales(Anuncios, spinners....) de dicho fragments
     */
    private void initComponents() {
        // Inflar y encontrar el Spinner

        Context context = requireActivity().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        Almacen = new BundleRecoverry(sharedPreferences);
        idUsuario = Almacen.recuperarInt("logginId");

        spinner = view.findViewById(R.id.spCategorias);
        Metodos m = new Metodos();

        ListAdapter listAdapter = new ListAdapter(m.getAnunciosInicio(new String[]{Integer.toString(idUsuario)}), requireContext());
        RecyclerView recyclerView = view.findViewById(R.id.rvAnunciosHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);

        // Crear y configurar el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, m.getCategorias());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinner.setAdapter(adapter);

        spinner.setSelection(m.getCategorias().indexOf("None"));
    }
}
