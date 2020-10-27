package com.example.ourtournament.Fixture;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MostrarPartido extends Fragment {
    TextView Jorn,E1,E2,jugado,Resultado,NombreE1,NombreE2;
    Partido Par;
    Button Volver;
    ImageView Foto1,Foto2;
    ListView lista1,lista2;
    View VistaADevolver;
    ArrayList<GolesXUsuario> Goles1;
    ArrayList<GolesXUsuario> Goles2;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        if(VistaADevolver == null)
        {
            VistaADevolver = inflador.inflate(R.layout.un_partido, GrupoDeLaVista, false);
            finds(VistaADevolver);
        }

        Goles1 = new ArrayList<>();
        Goles2 = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Par.FechaDeEncuentro);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int horas = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);

        if (Par.GolesLocal == -1)
        {
            jugado.setText("El partido se jugara el "+day+"/"+month+" a las "+horas+":"+minutos+" horas");
            Resultado.setText("-:-");

            GolesXUsuario GU = new GolesXUsuario(-1,-1,"No hay goles",-1,"");
            Goles1.add(GU);

            ListView lista1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
            AdaptadorGolesXPartido Adaptador = new AdaptadorGolesXPartido(Goles1, R.layout.item_goles_por_usuario, getActivity().getApplicationContext());
            lista1.setAdapter(Adaptador);

            ListView lista2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
            AdaptadorGolesXPartido Adaptador2 = new AdaptadorGolesXPartido(Goles1, R.layout.item_goles_por_usuario, getActivity().getApplicationContext());
            lista2.setAdapter(Adaptador2);
        }else
        {
            TraerGoles Tarea = new TraerGoles();
            Tarea.execute();
            Resultado.setText(Par.GolesLocal + " - "+ Par.GolesVisitante);
            jugado.setText("El partido se jugo el "+day+"/"+month+" a las "+horas+":"+minutos+" horas");

        }

        String Ruta = "http://10.0.2.2:55859/Imagenes/Equipos/ID"+Par.IDEquipoLocal+"_Escudo.PNG";
        Picasso.get().load(Ruta).into(Foto1);
        Ruta = "http://10.0.2.2:55859/Imagenes/Equipos/ID"+Par.IDEquipoVisitante+"_Escudo.PNG";
        Picasso.get().load(Ruta).into(Foto2);

        Jorn.setText("Jornada "+Par.Jornada);
        E1.setText(Par.NombreEquipoLocal);
        E2.setText(Par.NombreEquipoVisitante);
        NombreE1.setText(Par.NombreEquipoLocal);
        NombreE2.setText(Par.NombreEquipoVisitante);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.IrAFixture(VistaADevolver);
            }

        });

        return VistaADevolver;
    }

    public void SetPartidoElegido(Partido partido)
    {
        Par = partido;
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

        NombreE1 = VistaADevolver.findViewById(R.id.NombreE1);
        NombreE2 = VistaADevolver.findViewById(R.id.NombreE2);

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
            Log.d("conexion",String.valueOf(lista.size()));
            /*
            GolesXUsuario GU1 = new GolesXUsuario(-1,-1, Par.NombreEquipoLocal,-1, Par.NombreEquipoLocal);
            GolesXUsuario GU2 = new GolesXUsuario(-1,-1, Par.NombreEquipoVisitante,-1, Par.NombreEquipoVisitante);
            Goles1.add(GU1);
            Goles2.add(GU2);

             */
            for(int i=0;i<lista.size();i++)
            {
                if (lista.get(i).Nombreequipo.equals(Par.NombreEquipoLocal))
                {
                    Goles1.add(lista.get(i));
                }else if (lista.get(i).Nombreequipo.equals(Par.NombreEquipoVisitante))
                {
                    Goles2.add(lista.get(i));
                }
            }

            AdaptadorGolesXPartido Adaptador = new AdaptadorGolesXPartido(Goles1, R.layout.item_goles_por_usuario, getActivity().getBaseContext());
            lista1.setAdapter(Adaptador);

            AdaptadorGolesXPartido Adaptador2 = new AdaptadorGolesXPartido(Goles2, R.layout.item_goles_por_usuario, getActivity().getApplicationContext());
            lista2.setAdapter(Adaptador2);
        }
    }
}
