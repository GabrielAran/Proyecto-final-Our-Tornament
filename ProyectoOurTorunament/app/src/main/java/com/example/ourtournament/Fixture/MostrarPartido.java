package com.example.ourtournament.Fixture;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MostrarPartido extends Fragment {
    TextView Jorn,E1,E2,jugado,Fecha,Resultado;
    Partido Par;
    Preferencias P;
    Button Volver;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_partido, GrupoDeLaVista, false);
        Resultado = VistaADevolver.findViewById(R.id.Resultado);
        //Fecha = VistaADevolver.findViewById(R.id.Fecha);
        jugado = VistaADevolver.findViewById(R.id.jugado);
        Jorn = VistaADevolver.findViewById(R.id.Jornada);
        E1 = VistaADevolver.findViewById(R.id.Equipo1);
        E2 = VistaADevolver.findViewById(R.id.Equipo2);
        Volver = VistaADevolver.findViewById(R.id.Volver);

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
}
