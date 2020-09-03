package com.example.ourtournament.Administracion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.ourtournament.R;

public class Perfil extends Fragment {
    FragmentManager AdminFragments;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.admin_perfil, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        return VistaADevolver;
    }
}
