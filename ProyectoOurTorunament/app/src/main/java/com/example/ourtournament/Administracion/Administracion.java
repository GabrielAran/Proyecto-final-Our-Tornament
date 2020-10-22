package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.ourtournament.Fixture.MostrarPartido;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Administracion extends Fragment {
    FragmentManager AdminFragments;
    Button btn_Perfil, btn_Config;
    View VistaADevolver = null;
    private FragmentTransaction TransaccionesDeFragment;
    AdaptadorTorneos Adaptador;
    ListView ListaSeguidos,ListaParticipados;
    MainActivity Principal;
    Preferencias P;
    int IDUsuario;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if (VistaADevolver == null) {
            VistaADevolver = inflador.inflate(R.layout.administracion, GrupoDeLaVista, false);
            AdminFragments = getFragmentManager();

            Referencias();
            SetearListeners();
        }
        IDUsuario = P.ObtenerInt("IDUsuario",-1);
        AsyncTasks();
        return VistaADevolver;
    }

    private class TraerTorneosSeguidosPorUsuario extends AsyncTask<Integer,Void,ArrayList<Torneo>> {
        @Override
        protected ArrayList<Torneo> doInBackground(Integer... voids) {
            ArrayList<Torneo> ArrayTorneos = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetTorneosSeguidosPorUsuario/Usuario/"+IDUsuario;
                URL miRuta = new URL(miURL);
                Log.d("conexion","estoy accediendo a la ruta: "+miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion","Me conecte perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecTorneos = parseador.parse(lectorJSon).getAsJsonArray();

                    for (int i = 0; i < VecTorneos.size(); i++) {
                        JsonElement Elemento = VecTorneos.get(i);
                        Gson gson = new Gson();
                        Torneo T = gson.fromJson(Elemento, Torneo.class);
                        ArrayTorneos.add(T);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return ArrayTorneos;
        }
        protected void onPostExecute(final ArrayList<Torneo> ArrayTorneos)
        {
            int Cantidad=ArrayTorneos.size();
            if (ArrayTorneos.size()>5)
            {
                Cantidad = 5;
            }
            Adaptador = new AdaptadorTorneos(getContext(), R.layout.item_lista_torneos_seguidos, ArrayTorneos);
            //ListaSeguidos.getLayoutParams().height = 165 * Cantidad;
            ListaSeguidos.setAdapter(Adaptador);
            ListaSeguidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    VerTorneo VT = new VerTorneo();
                    VT.setTorneoElegido(ArrayTorneos.get(i));
                    Principal.IrAFragment(VT);
                }
            });
        }
    }

    private class TraerTorneosParticipadosPorUsuario extends AsyncTask<Integer,Void,ArrayList<Torneo>> {
        @Override
        protected ArrayList<Torneo> doInBackground(Integer... voids) {
            ArrayList<Torneo> ArrayTorneos = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetTorneosParticipadosPorUsuario/Usuario/"+IDUsuario;
                URL miRuta = new URL(miURL);
                Log.d("conexion","estoy accediendo a la ruta: "+miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion","Me conecte perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecTorneos = parseador.parse(lectorJSon).getAsJsonArray();

                    for (int i = 0; i < VecTorneos.size(); i++) {
                        JsonElement Elemento = VecTorneos.get(i);
                        Gson gson = new Gson();
                        Torneo T = gson.fromJson(Elemento, Torneo.class);
                        ArrayTorneos.add(T);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return ArrayTorneos;
        }
        protected void onPostExecute(final ArrayList<Torneo> ArrayTorneos)
        {
            Adaptador = new AdaptadorTorneos(getContext(), R.layout.item_lista_torneos_seguidos, ArrayTorneos);
            //ListaParticipados.getLayoutParams().height = 165 * Cantidad;
            ListaParticipados.setAdapter(Adaptador);
            ListaParticipados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    VerTorneo VT = new VerTorneo();
                    VT.setTorneoElegido(ArrayTorneos.get(i));
                    Principal.IrAFragment(VT);
                }
            });
        }
    }
    private void SetearListeners() {
        btn_Perfil.setOnClickListener(clickP);
        btn_Config.setOnClickListener(clickF);
    }

    private void Referencias() {
        ListaSeguidos = VistaADevolver.findViewById(R.id.ListaTorneosSeguidos);
        ListaParticipados = VistaADevolver.findViewById(R.id.ListaTorneosParticipados);
        btn_Perfil = VistaADevolver.findViewById(R.id.btn_Perfil);
        btn_Config = VistaADevolver.findViewById(R.id.btn_Config);
        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
    }

    private void AsyncTasks(){
        TraerTorneosSeguidosPorUsuario TorneoXusuario = new TraerTorneosSeguidosPorUsuario();
        TorneoXusuario.execute();
        TraerTorneosParticipadosPorUsuario Tarea = new TraerTorneosParticipadosPorUsuario();
        Tarea.execute();
    }

    private View.OnClickListener clickP = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Perfil perf = new Perfil();
            IrAFragment(perf);
        }
    };

    private View.OnClickListener clickF = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Configuracion conf = new Configuracion();
            IrAFragment(conf);
        }
    };

    public void IrAFragment(Fragment fragment){
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fragment);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }
}
