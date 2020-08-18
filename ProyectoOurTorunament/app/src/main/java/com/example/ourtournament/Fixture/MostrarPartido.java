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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MostrarPartido extends Fragment {
    TextView Jorn,E1,E2;

    ArrayList<Partido> ListaPartidos;
    ListView listaMostrar;
    ArrayAdapter<String> Adaptador;
    Preferencias P;
    Button Volver;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_partido, GrupoDeLaVista, false);
        Jorn = VistaADevolver.findViewById(R.id.Jornada);
        E1 = VistaADevolver.findViewById(R.id.Equipo1);
        E2 = VistaADevolver.findViewById(R.id.Equipo2);
        Volver = VistaADevolver.findViewById(R.id.Volver);
        ListaPartidos = new ArrayList<>();

        MainActivity Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
        String JSON = P.ObtenerString("ListaPartidos","...");
        int PartidoElegido = P.ObtenerInt("PartidoElegido",-1);

        if (JSON != "...")
        {
            try {
                JsonParser parseador = new JsonParser();
                JsonArray VecPartidos = parseador.parse(JSON).getAsJsonArray();
                Log.d("conexion",String.valueOf(VecPartidos.size()));
                for (int i = 0; i < VecPartidos.size(); i++) {

                    JsonObject Partido = (JsonObject) VecPartidos.get(i);

                    int IDPartido = Partido.get("IDPartido").getAsInt();
                    String NombreEquipoLocal = Partido.get("NombreEquipoLocal").getAsString();
                    String NombreEquipoVisitante = Partido.get("NombreEquipoVisitante").getAsString();
                    int GolesLocal= Partido.get("GolesLocal").getAsInt();
                    int GolesVisitante= Partido.get("GolesVisitante").getAsInt();
                    int Jorn = Partido.get("Jornada").getAsInt();

                    Log.d("conexion",IDPartido+" "+NombreEquipoLocal+" "+ NombreEquipoVisitante);
                    Partido P = new Partido(IDPartido,null,NombreEquipoLocal,NombreEquipoVisitante,GolesLocal,GolesVisitante,Jorn);
                    ListaPartidos.add(P);
                }

            } catch (Exception e) {
                Log.d("conexion","Hubo un error:"+e);
            }

        }

        if(PartidoElegido != -1)
        {
            Log.d("conexion", "La lista tiene :"+ListaPartidos.size()+" y se eligio el partido: "+PartidoElegido);
            Partido P = ListaPartidos.get(PartidoElegido);
            Jorn.setText(P._Jornada);
            E1.setText(P._NombreELocal);
            E2.setText(P._NombreEVisitante);
        }
/*


        Json



        Goles1 = new ArrayList<>();
        LosGoles1 = VistaADevolver.findViewById(R.id.ListaGolesE1);
        Adaptador = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles1);
        Goles1.add("No hay goles");
        LosGoles1.setAdapter(Adaptador);

        Goles2 = new ArrayList<>();
        LosGoles2 = VistaADevolver.findViewById(R.id.ListaGolesE2);
        Adaptador2 = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_lista_centrada, Goles2);
        Goles2.add("No hay goles");
        LosGoles2.setAdapter(Adaptador2);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity Principal = (MainActivity) getActivity();
                Principal.Volver();
            }

        });

 */
        return VistaADevolver;
    }
}
