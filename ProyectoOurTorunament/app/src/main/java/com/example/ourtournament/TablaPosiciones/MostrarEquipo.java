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

public class MostrarEquipo extends Fragment {
    TextView Puntos, PJugados, GolesAFavor, GolesEnContra;
    Button Volver;
    ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.un_equipo, GrupoDeLaVista, false);

        Puntos = VistaADevolver.findViewById(R.id.Puntos);
        PJugados = VistaADevolver.findViewById(R.id.PartidosJugados);
        GolesAFavor = VistaADevolver.findViewById(R.id.GolesAFavor);
        GolesEnContra = VistaADevolver.findViewById(R.id.GolesEnContra);
        Volver =  VistaADevolver.findViewById(R.id.Volver);

        Puntos.setText("15");
        PJugados.setText("10");
        GolesAFavor.setText("18");
        GolesEnContra.setText("13");

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