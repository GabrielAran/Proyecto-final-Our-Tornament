package com.example.ourtournament.Loguearse;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;

import static android.content.Context.MODE_PRIVATE;

public class IniciarSesion extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Button ConfirmarLogueo;
    TextView Nombre,Contrasenia;
    Preferencias P;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.iniciar_sesion, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        ConfirmarLogueo = VistaADevolver.findViewById(R.id.button);
        Nombre=VistaADevolver.findViewById(R.id.Nombre);
        Contrasenia=VistaADevolver.findViewById(R.id.Contrasenia);
        ConfirmarLogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MainActivity Principal = (MainActivity) getActivity();
                P = Principal.CargarSharedPreferences();
                P.GuardarString("NombreDeUsuario",Nombre.getText().toString());
                P.GuardarString("Contrasenia",Contrasenia.getText().toString());
                P.GuardarInt("IDUsuario",1);
                P.GuardarInt("IDTorneo", 1);
                Principal.Entrar();
            }
        });

        return VistaADevolver;
    }
}