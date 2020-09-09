package com.example.ourtournament.Fixture;

import androidx.annotation.Nullable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;
import com.example.ourtournament.TablaPosiciones.MostrarEquipo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

public class Fixture extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    ListView ListView;
    ImageView Carga;
    int ID;
    private Spinner spinner;
    Preferencias P;
    ArrayList<Partido> listaPartidos = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        ListView = VistaADevolver.findViewById(R.id.ListaPartidos);
        spinner = VistaADevolver.findViewById(R.id.Jornadas);
        Carga = VistaADevolver.findViewById(R.id.Carga);
        AdminFragments=getFragmentManager();

        ObjectAnimator Animacion = ObjectAnimator.ofFloat(Carga,"rotation",0,8000);
        Animacion.setDuration(7000);
        AnimatorSet SetDeAnimacion = new AnimatorSet();
        SetDeAnimacion.play(Animacion);
        SetDeAnimacion.start();
        final MainActivity Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
        ID = P.ObtenerInt("IDTorneo",-1);

        if(ID!=-1)
        {
            TraerJornadas Tarea = new TraerJornadas();
            Tarea.execute();
        }
        return VistaADevolver;
    }

    public void MostrarListaPartidos()
    {
        TraerPartidos Tarea = new TraerPartidos();
        Tarea.execute();
    }

    private class TraerPartidos extends AsyncTask<Void,Void,ArrayList<Partido>> {
        @Override
        protected ArrayList<Partido> doInBackground(Void... voids) {
            try {
                int Jornada = P.ObtenerInt("JornadaElegida", -1);
                String miURL = "http://10.0.2.2:55859/api/GetPartidos/Jornada/"+Jornada+"/Torneo/"+ID;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                listaPartidos.removeAll(listaPartidos);
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecPartidos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecPartidos.size(); i++) {
                        JsonElement Elemento = VecPartidos.get(i);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        Partido Part = gson.fromJson(Elemento, Partido.class);
                        listaPartidos.add(Part);
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
        protected void onPostExecute(final ArrayList<Partido> lista)
        {
            Carga.setVisibility(View.GONE);
            MainActivity Principal = (MainActivity) getActivity();
            AdaptadorPartidos Adaptador = new AdaptadorPartidos(lista,R.layout.item_lista_partidos,Principal);
            P.GuardarListaPartidos("ListaPartidos",lista);
            ListView.setAdapter(Adaptador);
            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MainActivity Principal = (MainActivity) getActivity();
                    P.GuardarInt("PartidoElegido",i);
                    MostrarPartido MP = new MostrarPartido();
                    Principal.IrAFragment(MP);
                }
            });

        }
    }

    private class TraerJornadas extends AsyncTask<Void,Void,ArrayList<String>>
    {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> listaJornada= new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetJornadas/Torneo/" + ID;
                Log.d("conexion", "estoy accediendo a la ruta "+miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecJornadas = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecJornadas.size(); i++)
                    {
                        int Jornada = VecJornadas.get(i).getAsInt();
                        Log.d("conexion", "Traje jornada "+Jornada);
                        listaJornada.add("jornada "+Jornada);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listaJornada;
        }
        protected void onPostExecute(ArrayList<String> lista)
        {
            P.GuardarListaString("ListaJornadas",lista);
            P.GuardarInt("JornadaElegida", lista.size()-1);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.select_dialog_singlechoice,lista);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        i++;
                        P.GuardarInt("JornadaElegida",i);
                        MostrarListaPartidos();
                }
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner.setSelection(lista.size()-1);
        }
    }




}
