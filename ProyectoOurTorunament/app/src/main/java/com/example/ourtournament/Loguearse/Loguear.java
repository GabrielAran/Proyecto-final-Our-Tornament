package com.example.ourtournament.Loguearse;

import androidx.annotation.Nullable;
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

import com.example.ourtournament.Fixture.FragmentoParaListaPartidos;
import com.example.ourtournament.R;

public class Loguear extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.loguear, GrupoDeLaVista, false);

        AdminFragments=getFragmentManager();

        Mostrar();




        return VistaADevolver;
    }
    public void Mostrar()
    {
        Log.d("conexion", "entre");
        IniciarSesion cuenta = new IniciarSesion();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.fragmentiniciosesion, cuenta);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }
}