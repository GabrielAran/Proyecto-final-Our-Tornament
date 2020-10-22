package com.example.ourtournament.Loguearse;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Goleadores;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;
import com.example.ourtournament.TablaGoleadores.AdaptadorListaGoleadores;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class IniciarSesion extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Button ConfirmarLogueo;
    EditText Nombre,Contrasenia;
    TextView Incorrecto;
    Preferencias P;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.iniciar_sesion, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        ConfirmarLogueo = VistaADevolver.findViewById(R.id.button);
        Nombre=VistaADevolver.findViewById(R.id.Nombre);
        Contrasenia=VistaADevolver.findViewById(R.id.Contrasenia);
        Incorrecto=VistaADevolver.findViewById(R.id.Texto);

        ConfirmarLogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Nombre.getText().length() >2 & Contrasenia.getText().length() >2)
                {
                    TraerUsuario Tarea = new TraerUsuario();
                    Tarea.execute();
                }else
                {
                    Incorrecto.setText("Debes ingresar minimo 3 caracteres en cada espacio");
                    Incorrecto.setVisibility(View.VISIBLE);
                    //TraerUsuario Tarea = new TraerUsuario();
                    //Tarea.execute();
                }
            }
        });

        return VistaADevolver;
    }

    private class TraerUsuario extends AsyncTask<Integer,Void, Usuario> {
        Usuario Us;
        @Override
        protected Usuario doInBackground(Integer... voids) {
            try {
                String miURL = "http://10.0.2.2:55859/api/GetUsuarioPorContrasenia/Usuario/"+Nombre.getText()+"/contrasenia/"+Contrasenia.getText();
                URL miRuta = new URL(miURL);
                Log.d("conexion", "estoy accediendo a la ruta: " + miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "Me conecte perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();

                    JsonElement Usuario = parseador.parse(lectorJSon).getAsJsonObject();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    Usuario U = gson.fromJson(Usuario, Usuario.class);
                    Us = U;

                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return Us;
        }

        protected void onPostExecute(Usuario U) {
            if (U.GolesEnTorneo != -10)
            {
                final MainActivity Principal = (MainActivity) getActivity();
                P = Principal.CargarSharedPreferences();
                P.GuardarUsuario("InformacionUsuario",U);
                P.GuardarInt("IDTorneo", 1);
                P.GuardarInt("IDUsuario",U.IdUsuario);
                Principal.CargarGeneral();
            }else
            {
                Incorrecto.setText("El nombre de usuario y/o la contraseña son incorrectos");
                Incorrecto.setVisibility(View.VISIBLE);
                Contrasenia.setText("");
            }
        }
    }
}