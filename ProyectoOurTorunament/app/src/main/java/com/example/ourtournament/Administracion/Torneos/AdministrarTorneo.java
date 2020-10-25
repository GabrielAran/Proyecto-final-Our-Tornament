package com.example.ourtournament.Administracion.Torneos;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdministrarTorneo extends Fragment{

    Torneo T = null;
    MainActivity Principal;
    Preferencias P;
    ImageView Perfil;
    Button Volver;
    TextView NombreTorneo;
    View VistaADevolver = null;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if (VistaADevolver == null)
        {
            VistaADevolver = inflador.inflate(R.layout.administrar_torneo, GrupoDeLaVista, false);
            Perfil = VistaADevolver.findViewById(R.id.foto);
            Volver = VistaADevolver.findViewById(R.id.Volver);
            NombreTorneo = VistaADevolver.findViewById(R.id.NombreTorneo);
            Principal = (MainActivity) getActivity();
            P = Principal.CargarSharedPreferences();
        }

        String Ruta = "http://10.0.2.2:55859/Imagenes/Torneos/ID"+T.IDTorneo+"_Perfil.JPG";
        Picasso.get().load(Ruta)
                .into(Perfil, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Perfil.setImageResource(R.drawable.icono_torneo);
                    }

                });
        NombreTorneo.setText(T.NombreTorneo);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.IrAAdministracion(VistaADevolver);
            }

        });

        return VistaADevolver;
    }

    public void setTorneoElegido(Torneo torneoElegido) {
        T = torneoElegido;
    }

}
