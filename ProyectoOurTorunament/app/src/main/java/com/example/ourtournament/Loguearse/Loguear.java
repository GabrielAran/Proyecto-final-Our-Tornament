package com.example.ourtournament.Loguearse;

import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ourtournament.Fixture.FragmentoParaListaPartidos;
import com.example.ourtournament.R;

public class Loguear extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    TextView Texto;
    CrearCuenta cuentacrear;
    IniciarSesion cuentaentrar;
    boolean cambio;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.loguear, GrupoDeLaVista, false);
        Texto = VistaADevolver.findViewById(R.id.cambiar);
        AdminFragments=getFragmentManager();
        cambio = false;
        cuentacrear = new CrearCuenta();
        cuentaentrar = new IniciarSesion();
        Mostrar();

        Texto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(cambio == true)
                {
                    cambio = false;
                    Texto.setText("Crear cuenta");
                    TransaccionesDeFragment=AdminFragments.beginTransaction();
                    TransaccionesDeFragment.replace(R.id.fragmentdelogueo, cuentaentrar);
                    TransaccionesDeFragment.commit();
                    TransaccionesDeFragment.addToBackStack(null);
                }else
                {
                    cambio = true;
                    Texto.setText("Iniciar sesion");
                    TransaccionesDeFragment=AdminFragments.beginTransaction();
                    TransaccionesDeFragment.replace(R.id.fragmentdelogueo, cuentacrear);
                    TransaccionesDeFragment.commit();
                    TransaccionesDeFragment.addToBackStack(null);

                }

            }
        });





        return VistaADevolver;
    }
    public void Mostrar()
    {
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.fragmentdelogueo, cuentaentrar);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

}