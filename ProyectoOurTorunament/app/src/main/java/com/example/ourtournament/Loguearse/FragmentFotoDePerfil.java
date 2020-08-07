package com.example.ourtournament.Loguearse;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;

public class FragmentFotoDePerfil extends Fragment{
    FragmentManager AdminFragments;
    Button Agregar,Quitar;
    int RequestCode = 23;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.armar_foto_perfil, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        Quitar = VistaADevolver.findViewById(R.id.Quitar);
        Agregar = VistaADevolver.findViewById(R.id.Agregar);
        final MainActivity Principal = (MainActivity) getActivity();

        /*
        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Principal,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestCode);
                }else {
                    Intent ObtenerFoto = new Intent(Intent.ACTION_GET_CONTENT);
                    ObtenerFoto.setType("image/*");
                    startActivityForResult(Intent.createChooser(ObtenerFoto,"Seleccione una foto"),CodeElegirFoto);
                }
            }
        });

         */
        return VistaADevolver;
    }
}


