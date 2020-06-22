package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourtournament.Fixture.Fixture;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class Inicio extends Fragment {
    Button Noticias,Buscar;
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.inicio, GrupoDeLaVista, false);
        Noticias = VistaADevolver.findViewById(R.id.Noticias);
        Buscar = VistaADevolver.findViewById(R.id.Buscar);
        AdminFragments=getFragmentManager();
        Noticias N = new Noticias();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.FrameInicio,N);
        TransaccionesDeFragment.commit();

        Noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Noticias.setBackgroundResource(R.drawable.secciones_inicio_izquierda);
                Buscar.setBackgroundResource(R.drawable.secciones_inicio_neutro);
                Noticias N = new Noticias();
                TransaccionesDeFragment=AdminFragments.beginTransaction();
                TransaccionesDeFragment.replace(R.id.FrameInicio,N);
                TransaccionesDeFragment.commit();
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Noticias.setBackgroundResource(R.drawable.secciones_inicio_neutro);
                Buscar.setBackgroundResource(R.drawable.secciones_inicio_izquierda);
            }
        });

        return VistaADevolver;
    }
}
