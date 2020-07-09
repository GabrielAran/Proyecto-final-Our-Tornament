package com.example.ourtournament.Fixture;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class Partido extends Fragment {
    TextView Jorn,E1,E2;

    ArrayList<String> Goles1;
    ListView LosGoles1;
    ArrayAdapter<String> Adaptador;

    ArrayList<String> Goles2;
    ListView LosGoles2;
    ArrayAdapter<String> Adaptador2;

    Button Volver;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_partido, GrupoDeLaVista, false);

        MainActivity Principal = (MainActivity) getActivity();

        Jorn = VistaADevolver.findViewById(R.id.Jornada);
        E1 = VistaADevolver.findViewById(R.id.Equipo1);
        E2 = VistaADevolver.findViewById(R.id.Equipo2);
        Volver = VistaADevolver.findViewById(R.id.Volver);


        String Jornada = Principal.GetJornadaElegida();
        String Equipo1 = Principal.GetEquipoElegido1();
        String Equipo2 = Principal.GetEquipoElegido2();

        Jorn.setText(Jornada);
        E1.setText(Equipo1);
        E2.setText(Equipo2);

        Goles1 = new ArrayList<>();
        LosGoles1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
        Adaptador = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles1);
        Goles1.add("No hay goles");
        LosGoles1.setAdapter(Adaptador);

        Goles2 = new ArrayList<>();
        LosGoles2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
        Adaptador2 = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles2);
        Goles2.add("No hay goles");
        LosGoles2.setAdapter(Adaptador2);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.Volver();
            }

        });
        return VistaADevolver;
    }
}
