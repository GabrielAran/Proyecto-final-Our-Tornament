package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Goleadores;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;
import com.example.ourtournament.TablaGoleadores.AdaptadorListaGoleadores;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Perfil extends Fragment {
    FragmentManager AdminFragments;
    TextView txt_Nombre, txt_Fecha, txt_Email, txt_Equipo;
    ImageView Foto;
    View VistaADevolver;
    Preferencias P;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        VistaADevolver = inflador.inflate(R.layout.admin_perfil, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        Referencias();
        MainActivity Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
        txt_Nombre.setText("Nombre de usuario: "+P.ObtenerString("NombreDeUsuario","Nombre"));
        txt_Fecha.setText("Fecha de nacimiento: "+P.ObtenerString("FechaDeNacimiento","Nacimiento"));
        txt_Email.setText("Email: "+P.ObtenerString("Email","Email"));
        txt_Equipo.setText("Equipo Favorito: "+P.ObtenerString("NombreEquipoFavorito","Equipo favorito"));
        return VistaADevolver;
    }

    private void Referencias() {
        txt_Nombre = VistaADevolver.findViewById(R.id.txt_Nombre);
        txt_Fecha = VistaADevolver.findViewById(R.id.txt_Fecha);
        txt_Email = VistaADevolver.findViewById(R.id.txt_Email);
        txt_Equipo = VistaADevolver.findViewById(R.id.txt_Equipo);
        Foto = VistaADevolver.findViewById(R.id.Foto);
    }

    /*
    gabi: usa este for para mostrar la contrasenia en forma de asteriscos
      String contra = "";
      for (int i = 0;i<U.Contrasenia.length();i++)
      {
          contra += "*";
      }
      Contrasenia.setText(String.valueOf(contra));
     */
}
