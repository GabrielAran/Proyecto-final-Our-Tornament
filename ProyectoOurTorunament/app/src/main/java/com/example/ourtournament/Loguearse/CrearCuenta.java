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
import com.example.ourtournament.Objetos.Preferencias;
import com.example.ourtournament.R;

public class CrearCuenta extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;
    Button ConfirmarLogueo;
    EditText Nombre,Email,contra,confContra;
    Preferencias P = new Preferencias();
    boolean Finalizar = false;
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
                if(contra.getText().toString() != "")
                {
                    if (!contraText.equals(confContraText)){
                        Log.d("Contrasenia", "Las contrasenias no coinciden");
                    }else {
                        FragmentFotoDePerfil CrearFoto = new FragmentFotoDePerfil();
                        TransaccionesDeFragment=AdminFragments.beginTransaction();
                        TransaccionesDeFragment.replace(R.id.inputs, CrearFoto);
                        TransaccionesDeFragment.commit();
                        TransaccionesDeFragment.addToBackStack(null);
                        if(Finalizar)
                        {
                            final MainActivity Principal = (MainActivity) getActivity();
                            P = Principal.CargarSharedPreferences();
                            P.GuardarString("contrasenia",contra.getText().toString());
                            P.GuardarInt("IDTorneo",1);
                            Intent Llamada = new Intent(Principal,MainActivity.class);
                            startActivity(Llamada);
                        }
                        Finalizar = true;
                    }
                }

            }
        });
        return VistaADevolver;
    }
}