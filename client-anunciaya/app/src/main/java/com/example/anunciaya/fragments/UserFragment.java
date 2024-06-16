package com.example.anunciaya.fragments;
/**
 * @Description Esto es el Fragment Usuario que se encarga de Mostrar la ventana de Usuario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anunciaya.Login;
import com.example.anunciaya.Pedidos;
import com.example.anunciaya.R;
import com.example.anunciaya.Register;
import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.Envios;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.Usuario;
/*Clase Principal*/
public class UserFragment extends Fragment {
    /*Atributos de la Clase*/
    private ImageView loggout;
    private SharedPreferences sharedPreferences ;
    private int idUsuario;
    private View view;
    private BundleRecoverry almacen;
    private Context context;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private Metodos m;
    private TextView nombreUsu,correoUsu,telefonoUsu;
    private Usuario usuario;
    private CardView btAjustesUsuario, btPedidosUsuario, btEnviosUsuario;

    /**
     * Constructor Principal de la Clase
     */
    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Esto es el primer metodo que se ejecuta al Iniciar este Fragment
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return retorna la Vista
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /*En este metodo lo que hacemos es establecer los atributos de la clase y cojemos sus id*/
        view = inflater.inflate(R.layout.fragment_user, container, false);
        context = requireActivity().getApplicationContext();
        sharedPreferences = context.getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        almacen = new BundleRecoverry(sharedPreferences);
        m = new Metodos();

        btAjustesUsuario = view.findViewById(R.id.btUserAjustes);
        btEnviosUsuario = view.findViewById(R.id.btUserEnvios);
        btPedidosUsuario = view.findViewById(R.id.btUserPedidos);
        loggout = view.findViewById(R.id.btLoggout);

        loggout.setOnClickListener(view -> Loggout());
        btAjustesUsuario.setOnClickListener(view -> LanzarAjustesUsuario());
        btPedidosUsuario.setOnClickListener(view -> LanzarMisPedidos());
        btEnviosUsuario.setOnClickListener(view -> LanzarMisEnvios());

        recyclerView = view.findViewById(R.id.rvAnunciosUsuario); // Declaramos cual es el recyclerView
        nombreUsu = view.findViewById(R.id.userNombreUsuario);
        telefonoUsu = view.findViewById(R.id.userTelefonoUsuario);
        correoUsu = view.findViewById(R.id.userCorreoUsuario);
        /*Llamamaos a el cargador de los anuncios del usuario*/
        setAnunciosUser();
        /*Llamamaos a el cargador de los datos del usuario*/
        CargarDatosUsuario();
        return view;

    }

    /**
     * Esto es un Metodo que se encarga de Lanzar la Activity de mis Envios
     */
    private void LanzarMisEnvios(){
        Intent intent = new Intent(getActivity(), Envios.class);
        startActivity(intent);
    }

    /**
     * Esto es un Metodo que se encarga de Lanzar la Activity de mis Pedidos
     */
    private void LanzarMisPedidos(){
        Intent intent = new Intent(getActivity(), Pedidos.class);
        startActivity(intent);
    }

    /**
     * Esto es un Metodo que se encarga de Lanzar la Activity de ajustes de Usuario
     */
    private void LanzarAjustesUsuario(){
        Intent intent = new Intent(getActivity(), Register.class);
        intent.putExtra("fromMainActivity", true);
        startActivity(intent);
    }

    /**
     * Esto es un Metodo que se encarga de hacer loggout de la App
     */
    private void Loggout(){
        almacen.guardarInt("logginId",-1);
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * Esto es un Metodo que se encarga de cargar los datos del Usuario
     */
    private void CargarDatosUsuario(){
        String[]params = {Integer.toString(idUsuario)};
        usuario = m.getUsuarioDataId(params);
        if(usuario!= null){
            nombreUsu.setText(usuario.getNombre());
            telefonoUsu.setText("Tf: "+usuario.getTelefono());
            correoUsu.setText(usuario.getEmail());
        }else Toast.makeText(context, "Ocurrio un error al Obtener los datos del Usuario", Toast.LENGTH_SHORT).show();
    }

    /**
     * Esto es un Metodo que se encarga de establecer los anuncuios del Usuario para mostrarlos por pantalla
     */
    private void setAnunciosUser(){
        idUsuario = almacen.recuperarInt("logginId");
        listAdapter = new ListAdapter(m.getAnunciosIdUsuario(new String[]{Integer.toString(idUsuario)}), requireContext(),this, R.layout.list_anuncios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listAdapter);
    }

    /**
     * Esto es un metodo que captura cuando el fragment ha sido renaudado para recargar los datos que se muestran por pantalla
     */
    @Override
    public void onResume() {
        super.onResume();
        setAnunciosUser();
        CargarDatosUsuario();
    }
}