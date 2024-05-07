package com.example.anunciaya.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anunciaya.R;
import com.example.anunciaya.addAnuncio;


public class AddFragment extends Fragment {
    private View view;

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        Context context = requireActivity().getApplicationContext();
        Intent intent = new Intent(context, addAnuncio.class);
        startActivity(intent);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    /*Metodo que se encarga de cuando se cierre la activity de add vuelva al fragment del main*/
    @Override
    public void onResume() {
        super.onResume();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_hostfragment);
        navController.navigate(R.id.page_inicio); // Reemplaza "homeFragment" con el ID de tu fragmento de inicio
    }
}