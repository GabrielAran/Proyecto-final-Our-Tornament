package com.example.ourtournament.TablaPosiciones;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.Loguearse.Loguear;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TablaPosiciones extends Fragment {

    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    ListView listaposiciones;
    int ID;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.tabla_de_posiciones, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        listaposiciones = VistaADevolver.findViewById(R.id.ListaDePosiciones);

        final MainActivity Principal = (MainActivity) getActivity();
        Preferencias P = Principal.CargarSharedPreferences();
        ID = P.ObtenerInt("IDTorneo",-1);

        if(ID!=-1)
        {
            TraerPosiciones Tarea = new TraerPosiciones();
            Tarea.execute(ID);
        }
        return VistaADevolver;
    }

    private class TraerPosiciones extends AsyncTask<Integer,Void,ArrayList<Equipo>> {
        @Override
        protected ArrayList<Equipo> doInBackground(Integer... voids) {
            ArrayList<Equipo> VecPosiciones = new ArrayList<>();
            try {
                Log.d("conexion", "estoy accediendo al torneo " + ID);
                String miURL = "http://10.0.2.2:55859/api/GetPosiciones/Torneo/" + ID;
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
                    JsonArray VecPos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecPos.size(); i++) {
                        JsonObject objeto = VecPos.get(i).getAsJsonObject();
                        JsonElement Elemento = objeto.get("IDEquipo");
                        int IDEquipo = Elemento.getAsInt();
                        Elemento = objeto.get("Nombre");
                        String NombreEquipo = Elemento.getAsString();
                        Elemento = objeto.get("IDTorneo");
                        int IDTorneo = Elemento.getAsInt();
                        Elemento = objeto.get("Puntos");
                        int Puntos = Elemento.getAsInt();
                        Elemento = objeto.get("GolesAFavor");
                        int GolesAFavor = Elemento.getAsInt();
                        Elemento = objeto.get("GolesEnContra");
                        int GolesEnContra = Elemento.getAsInt();
                        Elemento = objeto.get("PartidosJugados");
                        int PartidosJugados = Elemento.getAsInt();
                        Equipo E = new Equipo(IDEquipo,NombreEquipo,IDTorneo,Puntos,GolesAFavor,GolesEnContra,PartidosJugados);
                        VecPosiciones.add(E);
                        Log.d("conexion",String.valueOf(VecPosiciones.size()));
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return VecPosiciones;
        }
        protected void onPostExecute(ArrayList<Equipo> VecPosiciones)
        {
            final MainActivity Principal = (MainActivity) getActivity();
            AdaptadorListaPosiciones Adaptador = new AdaptadorListaPosiciones(Principal,R.layout.item_lista_posiciones,VecPosiciones);
            listaposiciones.setAdapter(Adaptador);
        }
    }

}
