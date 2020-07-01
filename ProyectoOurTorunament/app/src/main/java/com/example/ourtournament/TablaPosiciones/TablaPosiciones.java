package com.example.ourtournament.TablaPosiciones;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class TablaPosiciones extends Fragment {

    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    ListView listaposiciones;
    ArrayList<Equipo> VecPosiciones;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.tabla_de_posiciones, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        listaposiciones = VistaADevolver.findViewById(R.id.listaNoticias);
        VecPosiciones = new ArrayList<>();
        MainActivity Principal = (MainActivity) getActivity();
        VecPosiciones = Principal.getListaPosiciones();
        AdaptadorListaPosiciones Adaptador = new AdaptadorListaPosiciones(Principal,R.layout.item_lista_posiciones,VecPosiciones);
        listaposiciones.setAdapter(Adaptador);
        return VistaADevolver;
    }

}
