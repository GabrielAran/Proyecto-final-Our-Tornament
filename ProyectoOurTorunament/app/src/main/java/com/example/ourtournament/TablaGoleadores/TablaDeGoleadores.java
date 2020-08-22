package com.example.ourtournament.TablaGoleadores;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Usuario;
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

public class TablaDeGoleadores extends Fragment {

    FragmentManager AdminFragments;
    ListView ListaView;
    int IdTorneo;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.tabla_de_goleadores, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        ListaView = VistaADevolver.findViewById(R.id.ListaDeGoleadores);

        final MainActivity Principal = (MainActivity) getActivity();
        Preferencias P = Principal.CargarSharedPreferences();
        IdTorneo = P.ObtenerInt("IdUsuario",-1);

        if(IdTorneo !=-1)
        {
            TraerGoleadores ASync = new TraerGoleadores();
            ASync.execute(IdTorneo);
        }

        return VistaADevolver;
    }

    private class TraerGoleadores extends AsyncTask<Integer,Void,ArrayList<Usuario>> {
        @Override
        protected ArrayList<Usuario> doInBackground(Integer... voids) {
            ArrayList<Usuario> VecGoleadores = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetGoleadores/Torneo/" + IdTorneo;
                URL miRuta = new URL(miURL);

                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");

                if (miConexion.getResponseCode() == 200) {
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecGol = parseador.parse(lectorJSon).getAsJsonArray();

                    for (int i = 0; i < VecGol.size(); i++) {
                        JsonElement Elemento = VecGol.get(i);
                        Gson gson = new Gson();
                        Usuario U = gson.fromJson(Elemento, Usuario.class);
                        VecGoleadores.add(U);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return VecGoleadores;
        }
        protected void onPostExecute(ArrayList<Usuario> VecGoleadores)
        {
            final MainActivity Principal = (MainActivity) getActivity();
            AdaptadorListaGoleadores Adapter = new AdaptadorListaGoleadores(Principal,R.layout.item_tabla_goleadores,VecGoleadores);
            ListaView.setAdapter(Adapter);
        }
    }
}
