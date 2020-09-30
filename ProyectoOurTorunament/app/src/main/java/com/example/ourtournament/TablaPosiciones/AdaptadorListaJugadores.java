package com.example.ourtournament.TablaPosiciones;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ourtournament.Objetos.Equipo;
import com.example.ourtournament.Objetos.Usuario;
import com.example.ourtournament.R;

import java.util.ArrayList;

public class AdaptadorListaJugadores extends ArrayAdapter<Usuario>
{
    private ArrayList<Usuario> _ListaJugadores;
    private Context _Contexto;
    private int _Resource;

    public AdaptadorListaJugadores(Context contexto,int Resource,ArrayList<Usuario> ListaJugadores)
    {
        super(contexto,Resource,ListaJugadores);
        this._ListaJugadores = ListaJugadores;
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

        TextView Nombre,Goles;
        Nombre = VistaADevolver.findViewById(R.id.Nombre);
        Goles = VistaADevolver.findViewById(R.id.Goles);

        Usuario U = getItem(pos);
        Goles.setText(String.valueOf(U.GolesEnTorneo));
        Nombre.setText(U.NombreUsuario);

        return  VistaADevolver;
    }
}
