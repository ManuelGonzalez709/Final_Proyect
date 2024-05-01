package com.example.anunciaya.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.anunciaya.R;
import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.tools.Metodos;

public class InicioFragment extends Fragment {
    private Spinner spinner;
    private View view;

    public InicioFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        initComponents();

        return view; // Devolver la vista inflada correctamente
    }

    private void initComponents() {
        // Inflar y encontrar el Spinner
        spinner = view.findViewById(R.id.spCategorias);
        Metodos m = new Metodos();

        ListAdapter listAdapter = new ListAdapter(m.getAnunciosInicio(new String[]{"2"}), requireContext());
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
