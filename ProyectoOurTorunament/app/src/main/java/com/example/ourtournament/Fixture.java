package com.example.ourtournament;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourtournament.ListaPartidos;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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

    private Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        MainActivity Principal;
        Principal = (MainActivity) getActivity();
        ArrayList<String> ListaJornadas = Principal.getJornadas();

        spinner = VistaADevolver.findViewById(R.id.Jornadas);

        spinner = VistaADevolver.findViewById(R.id.Jornadas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,paths);
        spinner.setAdapter(adapter);
        Log.d("conexion", "entre");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
