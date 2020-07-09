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
import com.example.ourtournament.R;

import java.util.ArrayList;

public class AdaptadorListaPosiciones extends ArrayAdapter<Equipo>
{
    private ArrayList<Equipo> _ListaEquipos;
    private Context _Contexto;
    private int _Resource;

    public AdaptadorListaPosiciones(Context contexto,int Resource,ArrayList<Equipo> ListaEquipos)
    {
        super(contexto,Resource,ListaEquipos);
        Log.d("conexion", String.valueOf(ListaEquipos.size()));
        Log.d("conexion", String.valueOf(ListaEquipos.get(0)._Nombre));
        Log.d("conexion", String.valueOf(ListaEquipos.get(1)._Nombre));
        Log.d("conexion", String.valueOf(ListaEquipos.get(2)._Nombre));
        Log.d("conexion", String.valueOf(ListaEquipos.get(3)._Nombre));
        Log.d("conexion", String.valueOf(ListaEquipos.get(4)._Nombre));
        this._ListaEquipos = ListaEquipos;
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

        TextView Posicion,Equipo,Puntos,Goles,PartidosJugados;
        Posicion = VistaADevolver.findViewById(R.id.posicion);
        Equipo = VistaADevolver.findViewById(R.id.Equipo);
        Puntos = VistaADevolver.findViewById(R.id.Puntos);
        Goles = VistaADevolver.findViewById(R.id.Goles);
        PartidosJugados = VistaADevolver.findViewById(R.id.PartidosJugados);

        Equipo E = getItem(pos);

        Posicion.setText(String.valueOf(pos));
        Equipo.setText(E._Nombre);
        Puntos.setText(String.valueOf(E._Puntos));
        Goles.setText(String.valueOf(E._GolesAFavor));
        PartidosJugados.setText(String.valueOf(E._PartidosJugados));

        return  VistaADevolver;
    }
}
