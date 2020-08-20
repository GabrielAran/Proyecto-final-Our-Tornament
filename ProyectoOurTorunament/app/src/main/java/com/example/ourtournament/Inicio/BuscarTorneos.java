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
import com.google.gson.JsonArray;
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
    String NombreABuscar;
    final MainActivity Principal = (MainActivity) getActivity();
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
                NombreABuscar = String.valueOf(s);
                ListaTorneosSeleccionados.removeAll(ListaTorneosSeleccionados);
                for (int a=0;a<ListaTorneos.size();a++)
                {
                    if(ListaTorneos.get(a)._nombreTorneo.contains(s))
                    {
                        ListaTorneosSeleccionados.add(ListaTorneos.get(a));
                    }
                }
                AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal,R.layout.item_lista_torneos,ListaTorneosSeleccionados);
                ListaAMostrar.setAdapter(Adaptador);
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

                        JsonObject Torneo = VecTorneos.get(i).getAsJsonObject();

                        int IDTorneo = Torneo.get("IDTorneo").getAsInt();
                        String NombreTorneo = Torneo.get("NombreTorneo").getAsString();
                        String ContraseniaDeAdministrador = Torneo.get("ContraseniaDeAdministrador").getAsString();
                        String LinkParaUnirse = Torneo.get("LinkParaUnirse").getAsString();
                        int TipoDeTorneo = Torneo.get("TipoDeTorneo").getAsInt();

                        Torneo T = new Torneo(IDTorneo, NombreTorneo, ContraseniaDeAdministrador, LinkParaUnirse, TipoDeTorneo);
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
            AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal, R.layout.item_lista_torneos, lista);
            ListaAMostrar.setAdapter(Adaptador);
        }
    }
}