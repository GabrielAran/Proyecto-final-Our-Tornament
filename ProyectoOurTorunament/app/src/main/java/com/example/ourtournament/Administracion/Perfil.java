package com.example.ourtournament.Administracion;

import android.annotation.SuppressLint;
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
    TextView Nombre, Edad, Email, Contrasenia, EquipoFavorito;
    ImageView foto;
    View VistaADevolver;
    Preferencias P;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        VistaADevolver = inflador.inflate(R.layout.un_usuario, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        Referencias();

        MainActivity Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();
        Nombre.setText("Juan");
        /*
        Nombre.setText("Nombre de usuario: "+ P.ObtenerString("NombreDeUsuario","Nombre"));
        Edad.setText("Fecha de nacimiento: "+ P.ObtenerString("FechaDeNacimiento","Nacimiento"));
        Email.setText("Email: "+ P.ObtenerString("Email","Email"));
        Contrasenia.setText("Contraseña: "+ P.ObtenerString("Contraseña","Contraseña"));
        EquipoFavorito.setText("Equipo Favorito: "+ P.ObtenerString("NombreEquipoFavorito","Equipo favorito"));*/
        return VistaADevolver;
    }

    private void Referencias() {
        Nombre = VistaADevolver.findViewById(R.id.Nombre);
        Edad = VistaADevolver.findViewById(R.id.Edad);
        Email = VistaADevolver.findViewById(R.id.Email);
        Contrasenia = VistaADevolver.findViewById(R.id.Contrasenia);
        EquipoFavorito = VistaADevolver.findViewById(R.id.EquipoFavorito);
        foto = VistaADevolver.findViewById(R.id.foto);
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
