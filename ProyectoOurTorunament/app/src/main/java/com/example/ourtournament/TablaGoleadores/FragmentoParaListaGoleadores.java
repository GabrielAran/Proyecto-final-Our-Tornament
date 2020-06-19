package com.example.ourtournament.TablaGoleadores;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class FragmentoParaListaGoleadores extends Fragment
{
    ArrayList<String> ListaJugador;
    ArrayList<String> ListaEquipos1;
    ArrayList<String> ListaGoles;
    ListView ListView;

    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.lista_goleadores, GrupoDeLaVista, false);

        ListView = VistaADevolver.findViewById(R.id.ListaDeGoleadores);

        MainActivity Principal = (MainActivity) getActivity();

        ListaJugador = Principal.getListaJugador();
        ListaEquipos1 = Principal.getListaEquipos1();
        ListaGoles = Principal.getListaGoles();

        ListaGoleadores Adaptador;
        Adaptador = new ListaGoleadores(ListaJugador, ListaEquipos1, ListaGoles, Principal);

        ListView.setAdapter(Adaptador);

        return VistaADevolver;
    }
}
