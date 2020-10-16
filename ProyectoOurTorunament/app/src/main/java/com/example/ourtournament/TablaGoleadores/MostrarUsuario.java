package com.example.ourtournament.TablaGoleadores;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Goleadores;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;
import com.example.ourtournament.TablaPosiciones.TablaPosiciones;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MostrarUsuario extends Fragment {
    TextView Nombre,Edad, Email, Equipo, Contrasenia,GolesEnTorneo,TXT4,TXT3;
    ImageView Foto;
    Button Volver;
    MainActivity Principal;
    Preferencias P;
    Goleadores G;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_usuario, GrupoDeLaVista, false);
        Find(VistaADevolver);

        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();

        String JSON = P.ObtenerString("ListaGoleadores","...");
        int GoleadorElegido = P.ObtenerInt("GoleadorElegido",-1);

        if (!JSON.equals("..."))
        {
            try {
                JsonParser parseador = new JsonParser();
                JsonArray VecGoleadores = parseador.parse(JSON).getAsJsonArray();
                JsonElement Elemento = VecGoleadores.get(GoleadorElegido);
                Gson gson = new Gson();
                G = gson.fromJson(Elemento, Goleadores.class);

            } catch (Exception e) {
                Log.d("conexion","Hubo un error:"+e);
            }

        }

        TraerUsuario Tarea = new TraerUsuario();
        Tarea.execute();

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                TablaDeGoleadores TG = new TablaDeGoleadores();
                Principal.IrAFragment(TG);
            }
        });
        return VistaADevolver;
    }

    private class TraerUsuario extends AsyncTask<Void,Void,Usuario> {
        Usuario U;
        @Override
        protected Usuario doInBackground(Void... voids) {
            try {
                String miURL = "http://10.0.2.2:55859/api/GetUsuarioPorID/Usuario/"+G.IDUsuario1;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonObject Us = parseador.parse(lectorJSon).getAsJsonObject();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    U = gson.fromJson(Us, Usuario.class);
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return U;
        }
        protected void onPostExecute(Usuario U)
        {
            int Anio = U.FechaDeNacimiento.getYear();
            int Mes = U.FechaDeNacimiento.getMonth();
            int Dia = U.FechaDeNacimiento.getDay();
            //Period edad = Period.between(LocalDate.of(Anio, Mes, Dia), LocalDate.now());
            //Edad.setText(String.valueOf(edad.getYears()));

            Nombre.setText(G.NombreUsuario1);
            GolesEnTorneo.setText(String.valueOf(U.GolesEnTorneo));
            String Ruta = "http://10.0.2.2:55859/Imagenes/Usuarios/PerfilDefault.JPG";
            Picasso.get().load(Ruta).into(Foto);
        }
    }

    public void Find(View Vista)
    {
        TXT4 = Vista.findViewById(R.id.TXT4);
        Contrasenia = Vista.findViewById(R.id.Contrasenia);
        Contrasenia.setVisibility(View.GONE);
        TXT4.setVisibility(View.GONE);

        TXT3 = Vista.findViewById(R.id.TXT3);
        Email = Vista.findViewById(R.id.Email);
        TXT3.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);

        Nombre = Vista.findViewById(R.id.Nombre);
        Edad = Vista.findViewById(R.id.Edad);
        Equipo = Vista.findViewById(R.id.Equipo);
        Foto = Vista.findViewById(R.id.foto);
        GolesEnTorneo = Vista.findViewById(R.id.GolesEnTorneo);

        Volver =  Vista.findViewById(R.id.Volver);
    }


}
