package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class BuscarTorneos extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    ArrayList<Torneo> ListaTorneos = new ArrayList<>();
    EditText Buscador;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {

        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.buscar_torneos, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        LlenarLista();
        Buscador = VistaADevolver.findViewById(R.id.Buscar);
        Buscador.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(textView.getText().toString() == "")
                {

                }
                return false;
            }
        });


        return VistaADevolver;
    }

    public void LlenarLista()
    {
        Torneo T = new Torneo(1,"Copa palermo","lacontra", "aaaaa",2);
        ListaTorneos.add(T);
        ListaTorneos.add(T);
        ListaTorneos.add(T);
        ListaTorneos.add(T);
    }
}