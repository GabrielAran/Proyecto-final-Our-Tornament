package com.example.ourtournament.Administracion;

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

public class VerTorneo extends Fragment{

    Torneo torneoElegido = null;
    MainActivity Principal;
    Preferencias P;
    ImageView Perfil;
    Button Volver;
    TextView NombreTorneo;
    View VistaADevolver = null;
    Torneo T;

    public void setTorneoElegido(Torneo torneoElegido) {
        this.torneoElegido = torneoElegido;
    }

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

        int Torneo = P.ObtenerInt("TorneoElegido",-1);
        String ListaTorneos = P.ObtenerString("ListaTorneos","...");

        if (Torneo != -1 && ListaTorneos != "...")
        {
            try {
                JsonParser parseador = new JsonParser();
                JsonArray VecTorneos = parseador.parse(ListaTorneos).getAsJsonArray();
                JsonElement Elemento = VecTorneos.get(Torneo);
                Gson gson = new Gson();
                T = gson.fromJson(Elemento, Torneo.class);
                Log.d("conexion","Traje torneo "+T.IDTorneo);

            } catch (Exception e) {
                Log.d("conexion","Hubo un error:"+e);
            }
        }

        String Ruta = "http://10.0.2.2:55859/Imagenes/Torneos/ID"+torneoElegido.IDTorneo+"_Perfil.JPG";
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
        NombreTorneo.setText(torneoElegido.NombreTorneo);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.IrAAdministracion(VistaADevolver);
            }

        });

        return VistaADevolver;
    }

}
