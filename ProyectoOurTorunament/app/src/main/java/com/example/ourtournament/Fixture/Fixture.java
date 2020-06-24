package com.example.ourtournament.Fixture;

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

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class Fixture extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    private Spinner spinner;
    TextView Seleccion;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        Seleccion = VistaADevolver.findViewById(R.id.Seleccione);
        spinner = VistaADevolver.findViewById(R.id.Jornadas);
        AdminFragments=getFragmentManager();

        final MainActivity Principal = (MainActivity) getActivity();
        final ArrayList<String> ListaJornadas = Principal.getListaJornadas();

        Seleccion.setVisibility(View.VISIBLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,ListaJornadas);
        spinner.setAdapter(adapter);
        Log.d("conexion", "entre");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    Principal.SetJornadaElegida(i);
                    MostrarListaPartidos();
                    Seleccion.setVisibility(View.GONE);
                }

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (Principal.getVolver()==true)
        {
            FragmentoParaListaPartidos lista = new FragmentoParaListaPartidos();
            TransaccionesDeFragment=AdminFragments.beginTransaction();
            TransaccionesDeFragment.replace(R.id.FragmentoListaPartidos, lista);
            TransaccionesDeFragment.commit();
            Seleccion.setVisibility(View.GONE);
        }
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
