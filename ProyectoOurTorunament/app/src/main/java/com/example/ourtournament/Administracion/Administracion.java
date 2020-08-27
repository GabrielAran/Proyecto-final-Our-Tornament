package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class Administracion extends Fragment {
    FragmentManager AdminFragments;
    Button btn_Perfil, btn_Config;
    View VistaADevolver;
    private FragmentTransaction TransaccionesDeFragment;
    ArrayList<String> TorneosSeguidos = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView ListaDeAdministracion;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        VistaADevolver = inflador.inflate(R.layout.admin_principal, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        Referencias();
        SetearListeners();
        HardcodearLista();

        return VistaADevolver;
    }

    private void SetearListeners() {
        btn_Perfil.setOnClickListener(clickP);
        btn_Config.setOnClickListener(clickF);
    }

    private void Referencias() {
        ListaDeAdministracion = VistaADevolver.findViewById(R.id.ListaDeAdministracion);
        btn_Perfil = VistaADevolver.findViewById(R.id.btn_Perfil);
        btn_Config = VistaADevolver.findViewById(R.id.btn_Config);
    }

    private View.OnClickListener clickP = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Perfil perf = new Perfil();
            IrAFragment(perf);
        }
    };

    private View.OnClickListener clickF = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Configuracion conf = new Configuracion();
            IrAFragment(conf);
        }
    };

    public void IrAFragment(Fragment fragment){
        TransaccionesDeFragment=AdminFragments.beginTransaction();
        TransaccionesDeFragment.replace(R.id.Frame,fragment);
        TransaccionesDeFragment.commit();
        TransaccionesDeFragment.addToBackStack(null);
    }

    private void HardcodearLista() {
        TorneosSeguidos.add("Liga BBVA");
        TorneosSeguidos.add("Premier");
        TorneosSeguidos.add("Superliga");

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, TorneosSeguidos);
        ListaDeAdministracion.setAdapter(adapter);
    }
}
