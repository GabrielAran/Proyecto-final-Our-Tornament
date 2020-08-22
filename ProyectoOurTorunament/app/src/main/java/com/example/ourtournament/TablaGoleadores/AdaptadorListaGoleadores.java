package com.example.ourtournament.TablaGoleadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class AdaptadorListaGoleadores extends ArrayAdapter<Usuario>
{
    private ArrayList<Usuario> _ListaGoleadores;
    private Context _Contexto;
    private int _Resource;

    public AdaptadorListaGoleadores(Context contexto,int Resource,ArrayList<Usuario> ListaGoleadores)
    {
        super(contexto,Resource,ListaGoleadores);
        this._ListaGoleadores = ListaGoleadores;
        this._Contexto = contexto;
        this._Resource = Resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(int pos, View VistaADevolver, ViewGroup GrupoActual)
    {
        LayoutInflater MiInflador;
        if(VistaADevolver == null)
        {
            MiInflador = LayoutInflater.from(this._Contexto);
            VistaADevolver = MiInflador.inflate(_Resource,null);
        }

        TextView pos1, jug1, eqp1, Goles;
        pos1 = VistaADevolver.findViewById(R.id.pos1);
        jug1 = VistaADevolver.findViewById(R.id.jug1);
        eqp1 = VistaADevolver.findViewById(R.id.eqp1);
        Goles = VistaADevolver.findViewById(R.id.Goles);

        Usuario U = getItem(pos);

        pos1.setText(String.valueOf(pos));
        jug1.setText(U.NombreUsuario);
        eqp1.setText(String.valueOf(U.IDEquipo));
        Goles.setText(String.valueOf(U.GolesEnTorneo));

        return  VistaADevolver;
    }
}
