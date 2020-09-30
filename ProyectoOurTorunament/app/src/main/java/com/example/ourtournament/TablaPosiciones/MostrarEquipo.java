package com.example.ourtournament.TablaPosiciones;

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
import com.example.ourtournament.Objetos.TorneoSeguido;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;
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

public class MostrarEquipo extends Fragment {
    TextView Nombre,Puntos, PJugados, GolesAFavor, GolesEnContra;
    ImageView Foto;
    Button Volver;
    ListView ListaJugadores;
    MainActivity Principal;
    Preferencias P;
    Equipo E;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_equipo, GrupoDeLaVista, false);

        Nombre  = VistaADevolver.findViewById(R.id.Nombre);
        Puntos = VistaADevolver.findViewById(R.id.Puntos);
        PJugados = VistaADevolver.findViewById(R.id.PartidosJugados);
        GolesAFavor = VistaADevolver.findViewById(R.id.GolesAFavor);
        GolesEnContra = VistaADevolver.findViewById(R.id.GolesEnContra);
        Foto = VistaADevolver.findViewById(R.id.foto);
        Volver = VistaADevolver.findViewById(R.id.Volver);
        ListaJugadores = VistaADevolver.findViewById(R.id.ListaJugadores);

        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();

        String JSON = P.ObtenerString("ListaEquipos","...");
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
        TraerJugadores Tarea = new TraerJugadores();
        Tarea.execute();
        Nombre.setText(E.Nombre);
        Puntos.setText(String.valueOf(E.Puntos));
        PJugados.setText(String.valueOf(E.PartidosJugados));
        GolesAFavor.setText(String.valueOf(E.GolesAFavor));
        GolesEnContra.setText(String.valueOf(E.GolesEnContra));
        String Ruta = "https://as.com/futbol/imagenes/2019/08/28/videos/1567005433_173028_1567005806_noticia_normal.jpg";
        Picasso.get().load(Ruta).into(Foto);

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

    private class TraerJugadores extends AsyncTask<Void,Void,ArrayList<Usuario>>
    {
        @Override
        protected ArrayList<Usuario> doInBackground(Void... voids) {
            ArrayList<Usuario> listajugadores= new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetJugadoresXEquipos/Equipo/"+E.IDEquipo;
                Log.d("conexion", "estoy accediendo a la ruta "+miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecJugadores = parseador.parse(lectorJSon).getAsJsonArray();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    for (int i = 0; i < VecJugadores.size(); i++)
                    {
                        JsonElement Elemento = VecJugadores.get(i);
                        Usuario U = gson.fromJson(Elemento, Usuario.class);
                        listajugadores.add(U);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listajugadores;
        }
        protected void onPostExecute(ArrayList<Usuario> lista)
        {
            Log.d("conexion","Traje: "+lista.size()+" usuarios");
            AdaptadorListaJugadores Adaptador = new AdaptadorListaJugadores(Principal,R.layout.item_lista_jugadores,lista);
            ListaJugadores.setAdapter(Adaptador);
        }
    }
}