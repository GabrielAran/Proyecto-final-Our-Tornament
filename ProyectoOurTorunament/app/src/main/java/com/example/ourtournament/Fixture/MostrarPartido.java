package com.example.ourtournament.Fixture;

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
import com.example.ourtournament.Objetos.GolesXUsuario;
import com.example.ourtournament.Objetos.Partido;
import com.example.ourtournament.Objetos.Preferencias;
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

public class MostrarPartido extends Fragment {
    TextView Jorn,E1,E2,jugado,Resultado;
    Partido Par;
    Preferencias P;
    Button Volver;
    ImageView Foto1,Foto2;
    ListView lista1,lista2;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_partido, GrupoDeLaVista, false);

        finds(VistaADevolver);
        MainActivity Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
        String JSON = P.ObtenerString("ListaPartidos","...");
        int PartidoElegido = P.ObtenerInt("PartidoElegido",-1);

        if (JSON != "...")
        {
            try {
                JsonParser parseador = new JsonParser();
                JsonArray VecPartidos = parseador.parse(JSON).getAsJsonArray();
                JsonElement Elemento = VecPartidos.get(PartidoElegido);
                Gson gson = new Gson();
                Par = gson.fromJson(Elemento, Partido.class);

            } catch (Exception e) {
                Log.d("conexion","Hubo un error:"+e);
            }

        }

        if (Par.GolesLocal == -1)
        {
            jugado.setText("El partido se jugara el "+Par.FechaDeEncuentro.getDay()+"/"+Par.FechaDeEncuentro.getMonth()+" a las "+Par.FechaDeEncuentro.getHours()+" horas");
            Resultado.setText("-:-");

            ArrayList<String> Goles1 = new ArrayList<>();
            ListView lista1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
            Goles1.add("No hay goles");
            ArrayAdapter<String> Adaptador = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles1);
            lista1.setAdapter(Adaptador);

            ArrayList<String> Goles2 = new ArrayList<>();
            ListView lista2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
            ArrayAdapter<String> Adaptador2 = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles2);
            Goles2.add("No hay goles");
            lista2.setAdapter(Adaptador2);
        }else
        {
            TraerGoles Tarea = new TraerGoles();
            Tarea.execute();

            String Ruta = "https://upload.wikimedia.org/wikipedia/commons/c/c9/Boca_escudo.png";
            Picasso.get().load(Ruta).into(Foto1);
            Ruta = "https://logodownload.org/wp-content/uploads/2015/05/river-plate-logo-0.png";
            Picasso.get().load(Ruta).into(Foto2);
            Resultado.setText(Par.GolesLocal + " - "+ Par.GolesVisitante);
            jugado.setText("El partido se jugo el "+Par.FechaDeEncuentro.getDay()+"/"+Par.FechaDeEncuentro.getMonth()+" a las "+Par.FechaDeEncuentro.getHours()+" horas");

        }

        Jorn.setText("Jornada "+Par.Jornada);
        E1.setText(Par.NombreEquipoLocal);
        E2.setText(Par.NombreEquipoVisitante);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.IrAFixture(VistaADevolver);
            }

        });

        return VistaADevolver;
    }

    public void finds(View VistaADevolver)
    {
        Foto1 = VistaADevolver.findViewById(R.id.FotoE1);
        Foto2 = VistaADevolver.findViewById(R.id.FotoE2);
        Resultado = VistaADevolver.findViewById(R.id.Resultado);
        jugado = VistaADevolver.findViewById(R.id.jugado);
        Jorn = VistaADevolver.findViewById(R.id.Jornada);
        E1 = VistaADevolver.findViewById(R.id.Equipo1);
        E2 = VistaADevolver.findViewById(R.id.Equipo2);
        Volver = VistaADevolver.findViewById(R.id.Volver);

        lista1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
        lista2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
    }

    private class TraerGoles extends AsyncTask<Void,Void,ArrayList<GolesXUsuario>> {
        ArrayList<GolesXUsuario> listaGoles = new ArrayList<>();
        @Override
        protected ArrayList<GolesXUsuario> doInBackground(Void... voids) {
            try {
                String miURL = "http://10.0.2.2:55859/api/GetGolesXPartido/Partido/"+Par.IDPartido;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");

                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecPartidos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecPartidos.size(); i++) {
                        JsonElement Elemento = VecPartidos.get(i);
                        Gson gson = new Gson();
                        GolesXUsuario G = gson.fromJson(Elemento, GolesXUsuario.class);
                        listaGoles.add(G);
                        Log.d("conexion","Traje "+G.NombreUsuario+" con cantidad de goles "+G.Cantgoles);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listaGoles;
        }
        protected void onPostExecute(ArrayList<GolesXUsuario> lista)
        {
            ArrayList<String> Goles1 = new ArrayList<>();
            ArrayList<String> Goles2 = new ArrayList<>();
            Goles1.add(Par.NombreEquipoLocal);
            Goles2.add(Par.NombreEquipoVisitante);
            for(int i=0;i<lista.size();i++)
            {
                if (lista.get(i).Nombreequipo.equals(Par.NombreEquipoLocal))
                {
                    Goles1.add(lista.get(i).Cantgoles+" - "+ lista.get(i).NombreUsuario);
                }else if (lista.get(i).Nombreequipo.equals(Par.NombreEquipoVisitante))
                {
                    Goles2.add(lista.get(i).Cantgoles+" - "+ lista.get(i).NombreUsuario);
                }
            }

            ArrayAdapter<String> Adaptador = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles1);
            lista1.setAdapter(Adaptador);

            ArrayAdapter<String> Adaptador2 = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles2);
            lista2.setAdapter(Adaptador2);
        }
    }
}
