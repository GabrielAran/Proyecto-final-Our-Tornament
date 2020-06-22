package com.example.ourtournament.Inicio;

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

public class FragmentoParaListaNoticias extends Fragment
{
    ArrayList<MainActivity.Noticia> ListaNoticias;
    ListView ListView;
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        Log.d("conexion", "entre");
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.lista_noticias, GrupoDeLaVista, false);

        ListView = VistaADevolver.findViewById(R.id.listaNoticias);

        MainActivity Principal = (MainActivity) getActivity();

        ListaNoticias = Principal.getNoticias();

        ListaNoticias Adaptador;
        Adaptador = new ListaNoticias(ListaNoticias, Principal);

        ListView.setAdapter(Adaptador);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return VistaADevolver;
    }
}
