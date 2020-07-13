package com.example.ourtournament.Inicio;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import com.example.ourtournament.MainActivity;
import com.example.ourtournament.Objetos.Torneo;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class BuscarTorneos extends Fragment {
    FragmentManager AdminFragments;
    FragmentTransaction TransaccionesDeFragment;

    ArrayList<Torneo> ListaTorneos = new ArrayList<>();
    EditText Buscador;
    ArrayList<Torneo> ListaTorneosSeleccionados = new ArrayList<>();
    ListView ListaAMostrar;
    @Override
    public View onCreateView(LayoutInflater inflador, @Nullable ViewGroup GrupoDeLaVista, Bundle savedInstanceState) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.buscar_torneos, GrupoDeLaVista, false);
        AdminFragments=getFragmentManager();
        LlenarLista();
        final MainActivity Principal = (MainActivity) getActivity();

        ListaAMostrar = VistaADevolver.findViewById(R.id.listaTorneos);
        AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal,R.layout.item_lista_torneos,ListaTorneos);
        ListaAMostrar.setAdapter(Adaptador);
        Buscador = VistaADevolver.findViewById(R.id.Buscar);
        Buscador.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal,R.layout.item_lista_torneos,ListaTorneos);
                ListaAMostrar.setAdapter(Adaptador);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                ListaTorneosSeleccionados.removeAll(ListaTorneosSeleccionados);
                for (int a=0;a<ListaTorneos.size();a++)
                {
                    if(ListaTorneos.get(a)._nombreTorneo.contains(s))
                    {
                        ListaTorneosSeleccionados.add(ListaTorneos.get(a));
                    }
                }
                AdaptadorListaTorneos Adaptador = new AdaptadorListaTorneos(Principal,R.layout.item_lista_torneos,ListaTorneosSeleccionados);
                ListaAMostrar.setAdapter(Adaptador);
            }
        });

        return VistaADevolver;
    }

    public void LlenarLista()
    {
        Torneo T = new Torneo(1,"Copa palermo","lacontra", "aaaaa",2);
        Torneo A = new Torneo(2,"Gambeta corta","lacontra", "aaaaa",2);
        ListaTorneos.add(T);
        ListaTorneos.add(A);
        ListaTorneos.add(T);
        ListaTorneos.add(A);
    }
}