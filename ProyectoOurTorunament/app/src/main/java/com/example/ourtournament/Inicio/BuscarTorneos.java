package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BuscarTorneos extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    ArrayList<Torneo> ListaTorneos = new ArrayList<>();
    EditText Buscador;
    ArrayList<Torneo> ListaTorneosSeleccionados = new ArrayList<>();
    ListView ListaAMostrar;
    String NombreABuscar ="NULL";
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.buscar_torneos, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        ListaAMostrar = VistaADevolver.findViewById(R.id.listaTorneos);
        Buscador = VistaADevolver.findViewById(R.id.Buscar);

        LLenarListaTorneos Tarea = new LLenarListaTorneos();
        Tarea.execute();

        Buscador.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                ListaTorneosSeleccionados.removeAll(ListaTorneosSeleccionados);
                if(s.length() == 0)
                {
                    NombreABuscar = "NULL";
                }else
                {
                    NombreABuscar = String.valueOf(s);
                }
                LLenarListaTorneos Tarea = new LLenarListaTorneos();
                Tarea.execute();
            }
        });

        return VistaADevolver;
    }

    private class LLenarListaTorneos extends AsyncTask<Void,Void,ArrayList<Torneo>> {
        @Override
        protected ArrayList<Torneo> doInBackground(Void... voids) {
            ArrayList<Torneo> listaTorneos = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetTorneosPorNombre/Nombre/" + NombreABuscar;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecTorneos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecTorneos.size(); i++) {

                        JsonElement Elemento = VecTorneos.get(i);
                        Gson gson = new Gson();
                        Torneo T = gson.fromJson(Elemento, Torneo.class);
                        listaTorneos.add(T);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listaTorneos;
        }

        protected void onPostExecute(ArrayList<Torneo> lista) {
            MainActivity Principal = (MainActivity) getActivity();
            AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal, R.layout.item_lista_torneos, lista);
            ListaAMostrar.setAdapter(Adaptador);
        }
    }
}