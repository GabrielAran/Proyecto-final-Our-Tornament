package com.example.ourtournament.Fixture;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class FragmentoParaListaPartidos extends Fragment
{
    ArrayList<String> ListaEquipos1;
    ArrayList<String> ListaEquipos2;
    ListView ListView;
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        Log.d("conexion", "entre");
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.lista_partidos, GrupoDeLaVista, false);

        ListView = VistaADevolver.findViewById(R.id.ListaPartidos);

        MainActivity Principal = (MainActivity) getActivity();

        ListaEquipos1 = Principal.getListaEquipos1();
        ListaEquipos2 = Principal.getListaEquipos2();

        ListaPartidos Adaptador;
        Adaptador = new ListaPartidos(ListaEquipos1,ListaEquipos2, Principal);

        ListView.setAdapter(Adaptador);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                MainActivity act;
                act = (MainActivity) getActivity();

                act.PartidoSeleccionado(i);
            }
        });

        return VistaADevolver;
    }
}
