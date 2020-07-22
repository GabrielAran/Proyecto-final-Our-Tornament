package com.example.ourtournament.Fixture;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentoParaListaPartidos extends Fragment
{
    ListView ListView;
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.lista_partidos, GrupoDeLaVista, false);

        ListView = VistaADevolver.findViewById(R.id.ListaPartidos);

        /*
        ArrayList<Partido> lista = new ArrayList<>();
        Partido P = new Partido(1,null,"boca","river",2,1);
        lista.add(P);
        lista.add(P);
        lista.add(P);
        lista.add(P);
        lista.add(P);

         */

        TraerPartidos Tarea = new TraerPartidos();
        Tarea.execute();

        return VistaADevolver;
    }

    private class TraerPartidos extends AsyncTask<Void,Void,ArrayList<Partido>> {
        @Override
        protected ArrayList<Partido> doInBackground(Void... voids) {
            ArrayList<Partido> listaPartidos = new ArrayList<>();
            try {
                Log.d("conexion", "estoy accediendo al torneo ");
                String miURL = "http://10.0.2.2:55859/api/GetPartidos/Jornada/1/Torneo/1";
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                Log.d("conexion", "la conexion me devolvio: " + miConexion.getResponseCode());
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecPartidos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecPartidos.size(); i++) {

                        JsonObject Partido = VecPartidos.get(i).getAsJsonObject();

                        int IDPartido = Partido.get("IDPartido").getAsInt();
                        String NombreEquipoLocal = Partido.get("NombreEquipoLocal").getAsString();
                        String NombreEquipoVisitante = Partido.get("NombreEquipoVisitante").getAsString();
                        int GolesLocal= Partido.get("GolesLocal").getAsInt();
                        int GolesVisitante= Partido.get("GolesVisitante").getAsInt();

                        Partido P = new Partido(IDPartido,null,NombreEquipoLocal,NombreEquipoVisitante,GolesLocal,GolesVisitante);
                        listaPartidos.add(P);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listaPartidos;
        }
        protected void onPostExecute(ArrayList<Partido> lista)
        {
            MainActivity Principal = (MainActivity) getActivity();
            AdaptadorPartidos Adaptador = new AdaptadorPartidos(lista,R.layout.item_lista_partidos,Principal);

            ListView.setAdapter(Adaptador);

            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    MainActivity act;
                    act = (MainActivity) getActivity();

                    act.PartidoSeleccionado(i);
                }
            });

        }
    }



}
