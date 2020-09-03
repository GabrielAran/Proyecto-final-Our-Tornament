package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Goleadores;
import com.example.ourtournament.Objetos.Usuario;
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

public class Perfil extends Fragment {
    FragmentManager AdminFragments;
    TextView txt_Nombre, txt_Fecha, txt_Email, txt_Equipo;
    ImageView Foto;
    View VistaADevolver;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        VistaADevolver = inflador.inflate(R.layout.admin_perfil, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        Referencias();

        TraerUsuariosDatos UsuariosAsync = new TraerUsuariosDatos();
        UsuariosAsync.execute();

        return VistaADevolver;
    }

    private void Referencias() {
        txt_Nombre = VistaADevolver.findViewById(R.id.txt_Nombre);
        txt_Fecha = VistaADevolver.findViewById(R.id.txt_Fecha);
        txt_Email = VistaADevolver.findViewById(R.id.txt_Email);
        txt_Equipo = VistaADevolver.findViewById(R.id.txt_Equipo);
        Foto = VistaADevolver.findViewById(R.id.Foto);
    }

    private class TraerUsuariosDatos extends AsyncTask<Integer,Void, ArrayList<Usuario>> {
        @Override
        protected ArrayList<Usuario> doInBackground(Integer... voids) {
            ArrayList<Usuario> VecUsuarios = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetUsuario/Id/1";
                URL miRuta = new URL(miURL);
                Log.d("conexion","estoy accediendo a la ruta: "+miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion","Me conecte perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecGol = parseador.parse(lectorJSon).getAsJsonArray();

                    for (int i = 0; i < VecGol.size(); i++) {
                        JsonElement Elemento = VecGol.get(i);
                        Gson gson = new Gson();
                        Goleadores G = gson.fromJson(Elemento, Goleadores.class);
                        Log.d("conexion",String.valueOf(G.Goles1));
                        VecUsuarios.add(G);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return VecUsuarios;
        }
        protected void onPostExecute(ArrayList<Goleadores> VecGoleadores)
        {

        }
    }
}
