package com.example.ourtournament;

import androidx.annotation.Nullable;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Fixture extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    private Spinner spinner;
    TextView Fecha;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        Fecha = VistaADevolver.findViewById(R.id.Fecha);
        AdminFragments=getFragmentManager();

        final MainActivity Principal = (MainActivity) getActivity();

        final ArrayList<String> ListaJornadas = Principal.getListaJornadas();

        spinner = VistaADevolver.findViewById(R.id.Jornadas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,ListaJornadas);
        spinner.setAdapter(adapter);
        Log.d("conexion", "entre");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    MostrarListaPartidos();
                    Fecha.setText("07/8/2020");
                    Principal.SetJornada(i);
                }

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return VistaADevolver;
    }

    public void MostrarListaPartidos()
    {
        FragmentoParaListaPartidos lista = new FragmentoParaListaPartidos();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.FragmentoListaPartidos, lista);
        TransaccionesDeFragment.commit();
    }
}
