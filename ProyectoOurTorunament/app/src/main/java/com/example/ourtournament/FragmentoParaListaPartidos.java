package com.example.ourtournament;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

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

        Fixture act;
        act = (Fixture) getActivity();

        ListaEquipos1 = act.getEquipos1();
        ListaEquipos2 = act.getEquipos2();

        ListaPartidos Adaptador;
        Adaptador = new ListaPartidos(ListaEquipos1,ListaEquipos2,act);

        ListView.setAdapter(Adaptador);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Fixture act;
                act = (Fixture) getActivity();

                act.PartidoSeleccionado(ListaEquipos1.get(i),ListaEquipos2.get(i));
            }
        });

        return VistaADevolver;
    }
}
