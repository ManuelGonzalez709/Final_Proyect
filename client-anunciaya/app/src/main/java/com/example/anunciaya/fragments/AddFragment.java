package com.example.anunciaya.fragments;
/**
 * @Description Esto es el Fragment de Añadir Anuncios
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 1.0
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.anunciaya.R;
import com.example.anunciaya.AddAnuncio;


public class AddFragment extends Fragment {
    /*Atributos de la clase*/
    private View view;

    /**
     * Metodo principal de la Clase
     */

    public AddFragment() {
    }

    /**
     * Metodo que se encarga de lanzar la activity de register
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        Context context = requireActivity().getApplicationContext();
        Intent intent = new Intent(context, AddAnuncio.class);
        startActivity(intent);
        return view;
    }

    /**
     * Metodo que se encarga de capturar cuando el fragment ha sido pausado
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Metodo que captura cuando el fragment vuelve a ser reanudado
     */
    @Override
    public void onResume() {
        super.onResume();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_hostfragment);
        navController.navigate(R.id.page_inicio); // Reemplaza "homeFragment" con el ID de tu fragmento de inicio
    }
}