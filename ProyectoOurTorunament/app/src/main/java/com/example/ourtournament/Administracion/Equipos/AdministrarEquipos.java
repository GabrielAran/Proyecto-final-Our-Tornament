package com.example.ourtournament.Administracion.Equipos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;

public class AdministrarTorneo extends Fragment {

    MainActivity Principal;
    Preferencias P;
    View VistaADevolver = null;
    ListView ListaEquipos;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if (VistaADevolver == null) {
            VistaADevolver = inflador.inflate(R.layout.administrar_equipos, GrupoDeLaVista, false);
            Principal = (MainActivity) getActivity();
            P = Principal.CargarSharedPreferences();
            ListaEquipos = VistaADevolver.findViewById(R.id.listaequipos);
        }
        return VistaADevolver;
    }
}