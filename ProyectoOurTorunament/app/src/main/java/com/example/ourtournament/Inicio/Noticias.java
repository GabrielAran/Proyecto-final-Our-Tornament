package com.example.ourtournament.Inicio;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class Noticias extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    ArrayList<MainActivity.Noticia> listNoticias;
    ListView ListaNoticias;
    ArrayAdapter<MainActivity.Noticia> Adaptador;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.inicio, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        ListaNoticias = VistaADevolver.findViewById(R.id.ListaNoticias);
        MainActivity Principal = (MainActivity) getActivity();

        MainActivity.Noticia Not = new MainActivity.Noticia();
        listNoticias = new ArrayList<>();
        Adaptador = new ArrayAdapter<MainActivity.Noticia>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listNoticias);
        listNoticias = Principal.getNoticias();
        ListaNoticias.setAdapter(Adaptador);

        return VistaADevolver;
    }
}