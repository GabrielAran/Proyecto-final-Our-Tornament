package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
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
        final BuscarTorneos BT = new BuscarTorneos();
        final Noticias N = new Noticias();
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.FrameInicio,N);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);

        Noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Noticias.setBackgroundResource(R.drawable.secciones_inicio_izquierda);
                Buscar.setBackgroundResource(R.drawable.secciones_inicio_neutro);
                Buscar.setTextColor(Color.rgb(255,255,255));
                Noticias.setTextColor(Color.rgb(60,188,128));
                TransaccionesDeFragment=AdminFragments.beginTransaction();
                TransaccionesDeFragment.replace(R.id.FrameInicio,N);
                TransaccionesDeFragment.commit();
                TransaccionesDeFragment.addToBackStack(null);
                ObjectAnimator Animacion = ObjectAnimator.ofFloat(R.id.FrameInicio,"X",200);
                Animacion.setDuration(5000);
                AnimatorSet SetDeAnimacion = new AnimatorSet();
                SetDeAnimacion.play(Animacion);
                SetDeAnimacion.start();
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Noticias.setBackgroundResource(R.drawable.secciones_inicio_neutro);
                Buscar.setBackgroundResource(R.drawable.secciones_inicio_izquierda);
                Noticias.setTextColor(Color.rgb(255,255,255));
                Buscar.setTextColor(Color.rgb(60,188,128));
                TransaccionesDeFragment=AdminFragments.beginTransaction();
                TransaccionesDeFragment.replace(R.id.FrameInicio,BT);
                TransaccionesDeFragment.commit();
                TransaccionesDeFragment.addToBackStack(null);
                ObjectAnimator Animacion = ObjectAnimator.ofFloat(R.id.FrameInicio,"X",200);
                Animacion.setDuration(5000);
                AnimatorSet SetDeAnimacion = new AnimatorSet();
                SetDeAnimacion.play(Animacion);
                SetDeAnimacion.start();

            }
        });

        return VistaADevolver;
    }
}
