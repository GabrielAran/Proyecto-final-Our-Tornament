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
import java.util.List;

public class Fixture extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    Spinner Jornadas;
    Spinner LasJornadas;
    ArrayAdapter<String> Adaptador;

    ArrayList<String> Equipo1;
    ArrayList<String> Equipo2;

    String Jornada;
    Button Fixture;
    private Object AdapterView;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        Log.d("conexion", "entre");
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        MainActivity Principal;
        Principal = (MainActivity) getActivity();

        Jornadas = (Spinner) VistaADevolver.findViewById(R.id.Jornadas);

        ArrayList<String> list = new ArrayList<>();
        list.add("RANJITH");
        list.add("ARUN");
        list.add("JEESMON");
        list.add("NISAM");
        list.add("SREEJITH");
        list.add("SANJAY");
        list.add("AKSHY");
        list.add("FIROZ");
        list.add("RAHUL");
        list.add("ARJUN");
        list.add("SAVIYO");
        list.add("VISHNU");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(android.R.layout.simple_spinner_item, Jornadas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Jornadas.setAdapter(adapter);
        Jornadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        Jornadas = VistaADevolver.findViewById(R.layout.Jornadas);

        Jornadas = Principal.getJornadas();
        LasJornadas = VistaADevolver.findViewById(R.id.Jornadas);
        Adaptador = Principal.getAdaptador();
        LasJornadas.setAdapter(Adaptador);

         */
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
