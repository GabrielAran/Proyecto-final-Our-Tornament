package com.example.ourtournament.Administracion;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Perfil extends Fragment {
    FragmentManager AdminFragments;
    TextView Nombre, Edad, Email, Contrasenia, GolesEnTorneo;
    ImageView foto;
    Button Volver;
    View VistaADevolver;
    Preferencias P;
    private FragmentTransaction TransaccionesDeFragment;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        VistaADevolver = inflador.inflate(R.layout.un_usuario, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        Referencias();

        MainActivity Principal = (MainActivity) getActivity();
        P = Principal.CargarSharedPreferences();

        LlenarDatos();
        SetearListeners();

        return VistaADevolver;
    }

    private void Referencias() {
        Nombre = VistaADevolver.findViewById(R.id.Nombre);
        Edad = VistaADevolver.findViewById(R.id.Edad);
        Email = VistaADevolver.findViewById(R.id.Email);
        Contrasenia = VistaADevolver.findViewById(R.id.Contrasenia);
        GolesEnTorneo = VistaADevolver.findViewById(R.id.GolesEnTorneo);
        foto = VistaADevolver.findViewById(R.id.foto);
        Volver = VistaADevolver.findViewById(R.id.Volver);
    }

    private void SetearListeners(){
        Volver.setOnClickListener(Atras);
    }

    private View.OnClickListener Atras = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Administracion admin = new Administracion();
            IrAFragment(admin);
        }
    };//cambio base de datos

    private void LlenarDatos(){
        String contra = "";
        Gson gson = new Gson();
        String usuario = P.ObtenerString("InformacionUsuario", "");
        Usuario Usu = gson.fromJson(usuario, Usuario.class);

        for (int i = 0; i < Usu.Contrasenia.length() ;i++)
        {
            contra += "*";
        }
        Contrasenia.setText(contra);

        Nombre.setText(Usu.NombreUsuario);
        Edad.setText(String.valueOf(Usu.FechaDeNacimiento));
        Email.setText(Usu.Email);
        GolesEnTorneo.setText(String.valueOf(Usu.GolesEnTorneo));
        String Ruta = "https://image.freepik.com/vector-gratis/perfil-empresario-dibujos-animados_18591-58479.jpg";
        Picasso.get().load(Ruta).into(foto);
    }

    public void IrAFragment(Fragment fragment){
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fragment);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

}
