package com.example.ourtournament.Administracion.Usuario;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

public class Configuracion extends Fragment {
    FragmentManager AdminFragments;
    Button Volver;
    MainActivity Principal;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        final View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.admin_configuracion, GrupoDeLaVista, false);
        Volver = VistaADevolver.findViewById(R.id.Volver);
        AdminFragments=getFragmentManager();
        Principal = (MainActivity) getActivity();

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Principal.IrAAdministracion(VistaADevolver);
            }

        });
        return VistaADevolver;
    }

}
