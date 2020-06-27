package com.example.ourtournament.Fixture;

import androidx.annotation.Nullable;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Fixture extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    int ID;
    private Spinner spinner;
    TextView Seleccion;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.fixture, GrupoDeLaVista, false);
        Seleccion = VistaADevolver.findViewById(R.id.Seleccione);
        spinner = VistaADevolver.findViewById(R.id.Jornadas);
        AdminFragments=getFragmentManager();

        final MainActivity Principal = (MainActivity) getActivity();
        ID = Principal.getIDTorneo();

        final ArrayList<String> ListaJornadas = TraerJornadas(ID);
        Principal.SetListaJornadas(ListaJornadas);

        Seleccion.setVisibility(View.VISIBLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,ListaJornadas);
        spinner.setAdapter(adapter);
        Log.d("conexion", "entre");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    Principal.SetJornadaElegida(i);
                    MostrarListaPartidos();
                    Seleccion.setVisibility(View.GONE);
                }

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return VistaADevolver;
    }

    public void MostrarListaPartidos()
    {
        FragmentoParaListaPartidos lista = new FragmentoParaListaPartidos();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.FragmentoListaPartidos, lista);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }



    protected ArrayList<String> TraerJornadas(int... Parametros) {
        ArrayList<String> ListaJornadas= new ArrayList<String>();
        try {
            Log.d("conexion", "mando ID "+ID);
            String miURL = "http://10.0.2.2:55859/api/OBTJornadas/Torneo/" + ID;
            URL miRuta = new URL(miURL);
            HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
            Log.d("conexion", "llegue");
            miConexion.setRequestMethod("GET");

            int num;
            Log.d("conexion", "me devolvio: ");
            if (miConexion.getResponseCode() == 200) {
                Log.d("conexion", "me conecte");
                InputStream lector = miConexion.getInputStream();
                InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                JsonParser parseador = new JsonParser();
                JsonObject objetoJSon = parseador.parse(lectorJSon).getAsJsonObject();

                num = objetoJSon.getAsInt();
                ListaJornadas.add("jornada " +num);

            } else {
                Log.d("Conexion", "Me pude conectar pero algo malo pasó");
            }
            miConexion.disconnect();
        } catch (Exception ErrorOcurrido) {
            Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
        }

        return ListaJornadas;
    }
}
