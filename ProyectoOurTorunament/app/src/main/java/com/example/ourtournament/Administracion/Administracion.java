package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Goleadores;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.Objetos.TorneoSeguido;
import com.example.ourtournament.R;
import com.example.ourtournament.TablaGoleadores.AdaptadorListaGoleadores;
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
    ListView ListaDeAdministracion;
    MainActivity Principal;
    Preferencias P;
    int IDUsuario;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if (VistaADevolver == null) {
            VistaADevolver = inflador.inflate(R.layout.administracion, GrupoDeLaVista, false);
            AdminFragments = getFragmentManager();

            Referencias();
            IDUsuario = P.ObtenerInt("IDUsuario",-1);
            SetearListeners();
        }
        AsyncTasks();
        return VistaADevolver;
    }

    private class TraerTorneosSeguidosPorUsuario extends AsyncTask<Integer,Void,ArrayList<TorneoSeguido>> {
        @Override
        protected ArrayList<TorneoSeguido> doInBackground(Integer... voids) {
            ArrayList<TorneoSeguido> ArrayTorneos = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetTorneosPorNombre/Nombre/()/Usuario/"+IDUsuario;
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
                        TorneoSeguido TS = gson.fromJson(Elemento, TorneoSeguido.class);
                        if (TS.Siguiendo)
                        {
                            ArrayTorneos.add(TS);
                        }
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
        protected void onPostExecute(ArrayList<TorneoSeguido> ArrayTorneos)
        {
            int Cantidad=ArrayTorneos.size();
            if (ArrayTorneos.size()>4)
            {
                Cantidad = 4;
            }
            Adaptador = new AdaptadorTorneos(getContext(), R.layout.item_lista_torneos_seguidos, ArrayTorneos);
            ListaDeAdministracion.getLayoutParams().height = 155 * Cantidad;
            ListaDeAdministracion.setAdapter(Adaptador);
        }
    }

    private void SetearListeners() {
        btn_Perfil.setOnClickListener(clickP);
        btn_Config.setOnClickListener(clickF);
    }

    private void Referencias() {
        ListaDeAdministracion = VistaADevolver.findViewById(R.id.ListaDeAdministracion);
        btn_Perfil = VistaADevolver.findViewById(R.id.btn_Perfil);
        btn_Config = VistaADevolver.findViewById(R.id.btn_Config);
        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
    }

    private void AsyncTasks(){
        TraerTorneosSeguidosPorUsuario TorneoXusuario = new TraerTorneosSeguidosPorUsuario();
        TorneoXusuario.execute();
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
