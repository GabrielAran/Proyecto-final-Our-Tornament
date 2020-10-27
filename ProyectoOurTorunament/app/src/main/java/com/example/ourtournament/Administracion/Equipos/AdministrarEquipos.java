package com.example.ourtournament.Administracion.Equipos;

import android.app.Fragment;
import android.media.audiofx.DynamicsProcessing;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.Fixture.AdaptadorPartidos;
import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdministrarEquipos extends Fragment {

    MainActivity Principal;
    Preferencias P;
    View VistaADevolver = null;
    ListView ListaEquipos;
    int IDTorneo;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if (VistaADevolver == null) {
            VistaADevolver = inflador.inflate(R.layout.administrar_equipos, GrupoDeLaVista, false);
            Principal = (MainActivity) getActivity();
            P = Principal.CargarSharedPreferences();
            ListaEquipos = VistaADevolver.findViewById(R.id.listaequipos);
        }

        IDTorneo = P.ObtenerInt("IDTorneo",-1);
        TraerEquiposPorTorneo Tarea = new TraerEquiposPorTorneo();
        Tarea.execute();

        return VistaADevolver;
    }

    private class TraerEquiposPorTorneo extends AsyncTask<Void,Void, ArrayList<Equipo>>
    {
        @Override
        protected ArrayList<Equipo> doInBackground(Void... voids) {
            ArrayList<Equipo> ListaEquipos= new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetEquiposXTorneo/Torneo/"+IDTorneo;
                Log.d("conexion", "estoy accediendo a la ruta "+miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecEquipos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecEquipos.size(); i++)
                    {
                        JsonElement Elemento = VecEquipos.get(i);
                        Gson gson = new Gson();
                        Equipo E = gson.fromJson(Elemento, Equipo.class);
                        ListaEquipos.add(E);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return ListaEquipos;
        }
        protected void onPostExecute(ArrayList<Equipo> lista)
        {
            AdaptadorAdminEquipos Adaptador = new AdaptadorAdminEquipos(Principal,R.layout.item_admin_equipos,lista);
            ListaEquipos.setAdapter(Adaptador);
        }
    }
}