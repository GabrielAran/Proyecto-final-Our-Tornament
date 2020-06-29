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

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

public class CrearCuenta extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Button ConfirmarLogueo;
    SharedPreferences DatosGenerales;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.crear_cuenta, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        ConfirmarLogueo = VistaADevolver.findViewById(R.id.button);
        ConfirmarLogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MainActivity Principal = (MainActivity) getActivity();
                Principal.Entrar();
            }
        });

        return VistaADevolver;
    }
}