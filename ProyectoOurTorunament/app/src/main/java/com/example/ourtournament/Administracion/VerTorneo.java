package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.Objetos.TorneoSeguido;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VerTorneo extends Fragment{
    MainActivity Principal;
    Preferencias P;
    ImageView Perfil;
    View VistaADevolver = null;
    TorneoSeguido T;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if (VistaADevolver == null)
        {
            VistaADevolver = inflador.inflate(R.layout.administrar_torneo, GrupoDeLaVista, false);
            Perfil = VistaADevolver.findViewById(R.id.foto);
            Principal = (MainActivity) getActivity();
            P = Principal.CargarSharedPreferences();
        }

        int Torneo = P.ObtenerInt("TorneoElegido",-1);
        String ListaTorneos = P.ObtenerString("ListaTorneos","...");

        if (Torneo != -1 && ListaTorneos != "...")
        {
            try {
                JsonParser parseador = new JsonParser();
                JsonArray VecPartidos = parseador.parse(ListaTorneos).getAsJsonArray();
                JsonElement Elemento = VecPartidos.get(Torneo);
                Gson gson = new Gson();
                T = gson.fromJson(Elemento, TorneoSeguido.class);

            } catch (Exception e) {
                Log.d("conexion","Hubo un error:"+e);
            }
        }

        String Ruta = "http://10.0.2.2:55859/Imagenes/Torneos/ID" + T.IDTorneo + "_Perfil.JPG";
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
        return VistaADevolver;
    }
}
