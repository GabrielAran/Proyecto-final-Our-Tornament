package com.example.ourtournament;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;

import java.security.Principal;
import java.util.ArrayList;

public class Partido extends Fragment {
    TextView Jorn,E1,E2;

    ArrayList<String> Goles1;
    ListView LosGoles1;
    ArrayAdapter<String> Adaptador;

    ArrayList<String> Goles2;
    ListView LosGoles2;
    ArrayAdapter<String> Adaptador2;


    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_partido, GrupoDeLaVista, false);

        MainActivity Principal = (MainActivity) getActivity();

        Jorn = VistaADevolver.findViewById(R.id.Jornada);
        E1 = VistaADevolver.findViewById(R.id.Equipo1);
        E2 = VistaADevolver.findViewById(R.id.Equipo2);


        String Jornada = Principal.GetJornadaElegida();
        String Equipo1 = Principal.GetEquipoElegido1();
        String Equipo2 = Principal.GetEquipoElegido2();

        Jorn.setText(Jornada);
        E1.setText(Equipo1);
        E2.setText(Equipo2);

        Goles1 = new ArrayList<>();
        LosGoles1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
        Adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Goles1);
        Goles1.add("No hay goles aun");
        LosGoles1.setAdapter(Adaptador);

        Goles2 = new ArrayList<>();
        LosGoles2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
        Adaptador2 = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Goles2);
        Goles2.add("No hay goles aun");
        LosGoles2.setAdapter(Adaptador2);

        return VistaADevolver;
    }
    public void VolverAFixture(View vista)
    {
        MainActivity Principal = (MainActivity) getActivity();
        Principal.Volver();
    }

}
