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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.R;

public class CrearCuenta extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Button ConfirmarLogueo;
    SharedPreferences DatosGenerales;

    EditText contra;
    EditText confContra;

    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.crear_cuenta, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();

        ConfirmarLogueo = VistaADevolver.findViewById(R.id.button);
        contra = VistaADevolver.findViewById(R.id.contra);
        confContra = VistaADevolver.findViewById(R.id.confContra);

        ConfirmarLogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contraText = contra.getText().toString();
                String confContraText = confContra.getText().toString();
                Log.d("Contrasenia", "c1=" + contraText + " c2=" + confContraText);
                if (!contraText.equals(confContraText)){
                    Log.d("Contrasenia", "Las contrasenias no coinciden");
                }else {
                    final MainActivity Principal = (MainActivity) getActivity();
                    Principal.Entrar();
                }
            }
        });
        return VistaADevolver;
    }
}