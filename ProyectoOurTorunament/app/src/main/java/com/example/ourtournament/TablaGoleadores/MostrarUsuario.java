package com.example.ourtournament.TablaGoleadores;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.GolesXUsuario;
import com.example.ourtournament.Objetos.Partido;
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
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/*
public class MostrarUsuario extends Fragment {
    TextView Nombre,Edad, Email, Equipo, Contrasenia;
    Button Volver;
    ListView lista;
    MainActivity Principal;
    Preferencias P;
    Usuario U;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_usuario, GrupoDeLaVista, false);

        Nombre  = VistaADevolver.findViewById(R.id.Nombre);
        Edad = VistaADevolver.findViewById(R.id.Edad);
        Email = VistaADevolver.findViewById(R.id.Email);
        Equipo = VistaADevolver.findViewById(R.id.Equipo);
        Contrasenia = VistaADevolver.findViewById(R.id.Contrasenia);
        Volver =  VistaADevolver.findViewById(R.id.Volver);
        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();

        String JSON = P.ObtenerString("ListaGoleadores","...");
        int EquipoElegido = P.ObtenerInt("EquipoElegido",-1);

        if (!JSON.equals("..."))
        {
            try {
                JsonParser parseador = new JsonParser();
                JsonArray VecPartidos = parseador.parse(JSON).getAsJsonArray();
                JsonElement Elemento = VecPartidos.get(EquipoElegido);
                Gson gson = new Gson();
                Log.d("conexion","hasta aca estoy bien");
                E = gson.fromJson(Elemento, Equipo.class);
                Log.d("conexion",E.Nombre);

            } catch (Exception e) {
                Log.d("conexion","Hubo un error:"+e);
            }

        }

        Nombre.setText(E.Nombre);
        Puntos.setText(String.valueOf(E.Puntos));
        PJugados.setText(String.valueOf(E.PartidosJugados));
        GolesAFavor.setText(String.valueOf(E.GolesAFavor));
        GolesEnContra.setText(String.valueOf(E.GolesEnContra));

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                TablaPosiciones TP = new TablaPosiciones();
                Principal.IrAFragment(TP);
            }
        });
        return VistaADevolver;
    }

}

 */