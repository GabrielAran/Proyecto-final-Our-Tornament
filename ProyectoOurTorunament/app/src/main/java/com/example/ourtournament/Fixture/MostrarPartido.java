package com.example.ourtournament.Fixture;

import android.app.Fragment;
import android.content.SharedPreferences;
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
    TextView Jorn,E1,E2,jugado,Fecha,Resultado;
    Partido Par;
    Preferencias P;
    Button Volver;
    ImageView Foto1,Foto2;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
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
            //Fecha.setText("-/-/-");

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

            String Ruta = "https://upload.wikimedia.org/wikipedia/commons/c/c9/Boca_escudo.png";
            Picasso.get().load(Ruta).into(Foto1);
            Ruta = "https://logodownload.org/wp-content/uploads/2015/05/river-plate-logo-0.png";
            Picasso.get().load(Ruta).into(Foto2);
            Resultado.setText(Par.GolesLocal + " - "+ Par.GolesVisitante);
            jugado.setText("El partido se jugo el "+Par.FechaDeEncuentro.getDay()+"/"+Par.FechaDeEncuentro.getMonth()+" a las "+Par.FechaDeEncuentro.getHours()+" horas");
            //Fecha.setText(String.valueOf(Par.FechaDeEncuentro.getDay()+"/"+Par.FechaDeEncuentro.getMonth()));

            ArrayList<String> Goles1 = new ArrayList<>();
            ListView lista1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
            Goles1.add(Par.NombreEquipoLocal);
            for(int i=0;i<Par.GolesLocal;i++)
            {
                Goles1.add("Nombre");
            }
            ArrayAdapter<String> Adaptador = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles1);
            lista1.setAdapter(Adaptador);

            ArrayList<String> Goles2 = new ArrayList<>();
            ListView lista2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
            ArrayAdapter<String> Adaptador2 = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles2);
            Goles2.add(Par.NombreEquipoVisitante);
            for(int i=0;i<Par.GolesVisitante;i++)
            {
                Goles2.add("Nombre");
            }
            lista2.setAdapter(Adaptador2);
        }

        Jorn.setText("Jornada "+Par.Jornada);
        E1.setText(Par.NombreEquipoLocal);
        E2.setText(Par.NombreEquipoVisitante);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.Volver();
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
    }

    private class TraerGoles extends AsyncTask<Void,Void,ArrayList<Partido>> {
        @Override
        protected ArrayList<Partido> doInBackground(Void... voids) {
            try {
                int Jornada = P.ObtenerInt("JornadaElegida", -1);
                String miURL = "http://10.0.2.2:55859/api/GetPartidos/Jornada/"+Jornada+"/Torneo/"+ID;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                listaPartidos.removeAll(listaPartidos);
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecPartidos = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecPartidos.size(); i++) {
                        JsonElement Elemento = VecPartidos.get(i);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        Partido Part = gson.fromJson(Elemento, Partido.class);
                        listaPartidos.add(Part);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {

                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listaPartidos;
        }
        protected void onPostExecute(final ArrayList<Partido> lista)
        {
            Carga.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);
            MainActivity Principal = (MainActivity) getActivity();
            AdaptadorPartidos Adaptador = new AdaptadorPartidos(lista,R.layout.item_lista_partidos,Principal);
            P.GuardarListaPartidos("ListaPartidos",lista);
            ListView.setAdapter(Adaptador);
            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MainActivity Principal = (MainActivity) getActivity();
                    P.GuardarInt("PartidoElegido",i);
                    Principal.PartidoSeleccionado();
                }
            });

        }
    }
}
