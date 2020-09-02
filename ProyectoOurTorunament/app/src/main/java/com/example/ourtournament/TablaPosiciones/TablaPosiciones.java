package com.example.ourtournament.TablaPosiciones;

import androidx.annotation.Nullable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.Loguearse.Loguear;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;
import com.google.gson.Gson;
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
    ImageView Carga;
    int ID;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.tabla_de_posiciones, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        listaposiciones = VistaADevolver.findViewById(R.id.ListaDePosiciones);
        Carga = VistaADevolver.findViewById(R.id.Carga);

        ObjectAnimator Animacion = ObjectAnimator.ofFloat(Carga,"rotation",0,8000);
        Animacion.setDuration(7000);
        AnimatorSet SetDeAnimacion = new AnimatorSet();
        SetDeAnimacion.play(Animacion);
        SetDeAnimacion.start();
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
                String miURL = "http://10.0.2.2:55859/api/GetPosiciones/Torneo/" + ID;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecPos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecPos.size(); i++) {
                        JsonElement Elemento = VecPos.get(i);
                        Gson gson = new Gson();
                        Equipo E = gson.fromJson(Elemento, Equipo.class);
                        VecPosiciones.add(E);
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
            Carga.setVisibility(View.GONE);
        }
    }

}
