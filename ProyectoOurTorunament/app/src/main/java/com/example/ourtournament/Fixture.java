package com.example.ourtournament;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourtournament.ListaPartidos;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Fixture extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    ArrayList<String> Jornadas;
    Spinner LasJornadas;
    ArrayAdapter<String> Adaptador;

    ArrayList<String> Equipo1;
    ArrayList<String> Equipo2;

    String Jornada;
    Button Fixture;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        Log.d("conexion", "entre");
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        Fixture = VistaADevolver.findViewById(R.id.Fixture);
        AdminFragments=getFragmentManager();

        Jornadas = new ArrayList<String>();
        MainActivity Principal;
        Principal = (MainActivity) getActivity();
        Jornadas = Principal.getJornadas();
        LasJornadas = VistaADevolver.findViewById(R.id.Jornadas);
        Adaptador = Principal.getAdaptador();
        LasJornadas.setAdapter(Adaptador);

        MostrarListaPartidos();

        return VistaADevolver;
    }


    public void MostrarListaPartidos()
    {
        FragmentoParaListaPartidos lista = new FragmentoParaListaPartidos();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame, lista);
        TransaccionesDeFragment.commit();
    }
}
