package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Noticia;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.Objetos.TorneoSeguido;
import com.example.ourtournament.R;
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

public class Inicio extends Fragment {
    FragmentManager AdminFragments;
    MainActivity Principal;
    Preferencias P;
    int IDUsuario,IDTorneo;
    View VistaADevolver;
    Button Noticias,Buscar;

    TextView renglon;
    ImageView Carga;

    ListView listanoticias;

    EditText Buscador;
    ArrayList<Torneo> ListaTorneos = new ArrayList<>();
    ListView listatorneos;
    String NombreABuscar ="()";

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        VistaADevolver = inflador.inflate(R.layout.inicio, GrupoDeLaVista, false);
        FindView(VistaADevolver);
        Rotacion(Carga);
        Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
        IDUsuario = P.ObtenerInt("IDUsuario",-1);
        IDTorneo = P.ObtenerInt("IDTorneo",-1);

        Noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IraNoticias();
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IraTorneos();
            }
        });

        IraNoticias();

        return VistaADevolver;
    }

    public void FindView(View VistaADevolver)
    {
        AdminFragments=getFragmentManager();
        Noticias = VistaADevolver.findViewById(R.id.Noticias);
        listanoticias = VistaADevolver.findViewById(R.id.lista);
        Carga = VistaADevolver.findViewById(R.id.Carga);
        Buscar = VistaADevolver.findViewById(R.id.Buscar);
        Buscador = VistaADevolver.findViewById(R.id.Buscador);
        renglon = VistaADevolver.findViewById(R.id.ren);
    }
    public void IraNoticias()
    {
        Carga.setVisibility(View.VISIBLE);
        Rotacion(Carga);

        TraerNoticias Tarea = new TraerNoticias();
        Tarea.execute();

        listanoticias = VistaADevolver.findViewById(R.id.lista);
        listanoticias.setVisibility(View.GONE);
        Buscador.setVisibility(View.GONE);
        Buscar.setTextColor(Color.rgb(255,255,255));
        Noticias.setTextColor(Color.rgb(60,188,128));
        Animacion(renglon,"X",-10);
    }
    public void IraTorneos()
    {
        Log.d("conexion","Voy a torneos");
        Carga.setVisibility(View.VISIBLE);
        Rotacion(Carga);

        LLenarListaTorneos Tarea = new LLenarListaTorneos();
        Tarea.execute();

        listatorneos = VistaADevolver.findViewById(R.id.lista);
        listatorneos.setVisibility(View.GONE);
        Noticias.setTextColor(Color.rgb(255,255,255));
        Buscar.setTextColor(Color.rgb(60,188,128));
        Buscador.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s){}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                listatorneos = VistaADevolver.findViewById(R.id.lista);
                ListaTorneos.removeAll(ListaTorneos);
                if(s.length() == 0)
                {
                    NombreABuscar = "()";
                }else
                {
                    NombreABuscar = String.valueOf(s);
                }
                LLenarListaTorneos Tarea = new LLenarListaTorneos();
                Tarea.execute();
            }
        });
        Buscador.setVisibility(View.VISIBLE);
        Animacion(renglon,"X",540);
    }
    public void Animacion(TextView objeto,String Nombre,int value)
    {
        ObjectAnimator Animacion = ObjectAnimator.ofFloat(objeto,Nombre,value);
        Animacion.setDuration(300);
        AnimatorSet SetDeAnimacion = new AnimatorSet();
        SetDeAnimacion.play(Animacion);
        SetDeAnimacion.start();
    }
    public void Rotacion(ImageView carga)
    {
        ObjectAnimator Animacion = ObjectAnimator.ofFloat(carga,"rotation",0,360);
        Animacion.setDuration(1200);
        AnimatorSet SetDeAnimacion = new AnimatorSet();
        SetDeAnimacion.play(Animacion);
        SetDeAnimacion.start();
    }
    private class LLenarListaTorneos extends AsyncTask<Void,Void,ArrayList<TorneoSeguido>> {
        @Override
        protected ArrayList<TorneoSeguido> doInBackground(Void... voids) {
            ArrayList<TorneoSeguido> listaTorneos = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetTorneosPorNombre/Nombre/" + NombreABuscar+"/Usuario/"+IDUsuario;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecTorneos = parseador.parse(lectorJSon).getAsJsonArray();
                    Gson gson = new Gson();
                    TorneoSeguido TS;
                    JsonElement Elemento;
                    for (int i = 0; i < VecTorneos.size(); i++) {
                        Elemento = VecTorneos.get(i);
                        TS = gson.fromJson(Elemento, TorneoSeguido.class);
                        listaTorneos.add(TS);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return listaTorneos;
        }

        protected void onPostExecute(ArrayList<TorneoSeguido> lista) {
            AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal, R.layout.item_lista_torneos, lista,IDTorneo,IDUsuario);
            listatorneos.setVisibility(View.VISIBLE);
            listatorneos.setAdapter(Adaptador);
            Carga.setVisibility(View.GONE);
        }
    }
    private class TraerNoticias extends AsyncTask<Void, Void, ArrayList<Noticia>> {
        @Override
        protected ArrayList<Noticia> doInBackground(Void... voids) {
            ArrayList<Noticia> ListaNoticias = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetNoticiasPorTorneo/Torneo/"+IDTorneo;
                Log.d("conexion", "estoy accediendo a la ruta " + miURL);
                URL miRuta = new URL(miURL);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("conexion", "me pude conectar perfectamente");
                    InputStream lector = miConexion.getInputStream();
                    InputStreamReader lectorJSon = new InputStreamReader(lector, "utf-8");
                    JsonParser parseador = new JsonParser();
                    JsonArray VecNoticias = parseador.parse(lectorJSon).getAsJsonArray();
                    for (int i = 0; i < VecNoticias.size(); i++) {
                        JsonElement Elemento = VecNoticias.get(i);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        Noticia N = gson.fromJson(Elemento, Noticia.class);
                        ListaNoticias.add(N);
                    }
                } else {
                    Log.d("Conexion", "Me pude conectar pero algo malo pasó");
                }
                miConexion.disconnect();
            } catch (Exception ErrorOcurrido) {
                Log.d("Conexion", "Al conectar o procesar ocurrió Error: " + ErrorOcurrido.getMessage());
            }
            return ListaNoticias;
        }

        protected void onPostExecute(ArrayList<Noticia> listaN) {
            AdaptadorListaNoticias Adaptador = new AdaptadorListaNoticias(Principal,R.layout.item_lista_noticias, listaN);
            listanoticias.setAdapter(Adaptador);
            listanoticias.setVisibility(View.VISIBLE);
            Carga.setVisibility(View.GONE);
        }
    }
}
