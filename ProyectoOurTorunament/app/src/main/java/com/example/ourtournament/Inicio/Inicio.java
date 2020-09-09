package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import java.util.PropertyResourceBundle;

public class Inicio extends Fragment {
    Button Noticias,Buscar;
    TextView renglon;
    ImageView Carga;
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    ListView listanoticias;
    ArrayList<Noticia> ListaNoticias;

    EditText Buscador;
    ArrayList<Torneo> ListaTorneosSeleccionados = new ArrayList<>();
    ListView listatorneos;
    String NombreABuscar ="()";
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.inicio, GrupoDeLaVista, false);

        FindView(VistaADevolver);

        Rotacion(Carga);
        final MainActivity Principal = (MainActivity) getActivity();

        ListaNoticias = Principal.getNoticias();
        AdaptadorListaNoticias Adaptador = new AdaptadorListaNoticias(ListaNoticias, Principal);
        listanoticias.setAdapter(Adaptador);
        Carga.setVisibility(View.GONE);

        Noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carga.setVisibility(View.VISIBLE);
                Rotacion(Carga);

                ListaNoticias = Principal.getNoticias();
                AdaptadorListaNoticias Adaptador = new AdaptadorListaNoticias(ListaNoticias, Principal);
                listanoticias.setAdapter(Adaptador);

                Buscador.setVisibility(View.GONE);
                listanoticias = VistaADevolver.findViewById(R.id.lista);
                Buscar.setTextColor(Color.rgb(255,255,255));
                Noticias.setTextColor(Color.rgb(60,188,128));
                Animacion(renglon,"X",-10);
                Carga.setVisibility(View.GONE);
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        ListaTorneosSeleccionados.removeAll(ListaTorneosSeleccionados);
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
        });

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
        ObjectAnimator Animacion = ObjectAnimator.ofFloat(carga,"rotation",0,8000);
        Animacion.setDuration(7000);
        AnimatorSet SetDeAnimacion = new AnimatorSet();
        SetDeAnimacion.play(Animacion);
        SetDeAnimacion.start();
    }

    private class LLenarListaTorneos extends AsyncTask<Void,Void,ArrayList<Torneo>> {
        @Override
        protected ArrayList<Torneo> doInBackground(Void... voids) {
            ArrayList<Torneo> listaTorneos = new ArrayList<>();
            try {
                String miURL = "http://10.0.2.2:55859/api/GetTorneosPorNombre/Nombre/" + NombreABuscar;
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
                    for (int i = 0; i < VecTorneos.size(); i++) {

                        JsonElement Elemento = VecTorneos.get(i);
                        Gson gson = new Gson();
                        Torneo T = gson.fromJson(Elemento, Torneo.class);
                        listaTorneos.add(T);
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

        protected void onPostExecute(ArrayList<Torneo> lista) {
            MainActivity Principal = (MainActivity) getActivity();
            Preferencias P = Principal.CargarSharedPreferences();
            int IDTorneo = P.ObtenerInt("IDTorneo",-1);
            int IDUsuario = P.ObtenerInt("IDUsuario",-1);
            listatorneos.setVisibility(View.VISIBLE);
            AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal, R.layout.item_lista_torneos, lista,IDTorneo,IDUsuario);
            listatorneos.setAdapter(Adaptador);
            Carga.setVisibility(View.GONE);
        }
    }
}
